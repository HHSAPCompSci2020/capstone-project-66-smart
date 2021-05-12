package kchandra423.actors.MovingActors;

import kchandra423.actors.Actor;
import kchandra423.actors.Damage;
import kchandra423.actors.MovingActors.constants.ActorState;
import kchandra423.actors.MovingActors.constants.Stat;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;
import kchandra423.utility.Calculator;

/**
 * Moving actors differ from actors in that they have acceleration based physics already built into them.
 * They have a maximum velocity, and an x and y velocity, and an acceleration. All movement is done separately on the x and y axis.
 * This is to ensure that a collision created from the x direction will not affect the y velocity.
 * It is recommended to move in the x direction, check for intersection (if it collides with anything, bounce back in the x direction), and then do the same thing in the y direction
 *
 * @author Kumar Chandra
 * @see kchandra423.actors.Actor
 */
public abstract class MovingActor extends Actor {
    private ActorState state;
    protected float vx, vy;
    protected float maxV;
    protected float accel;
    protected final float[] statMultipliers;
    protected int health;
    protected final int maxHealth;
    protected int shield;
    protected final int maxShield;

    /**
     * Creates a new Moving actor with the specified image, acceleration and maximum velocity
     *
     * @param image The specified image
     * @param maxV  This actors maximum velocity
     * @param accel This actors acceleration
     */
    protected MovingActor(KImage image, float maxV, float accel) {
        super(image);
        vx = 0;
        vy = 0;
        this.maxV = maxV;
        this.accel = accel;
        state = ActorState.IDLE;
        maxHealth = 100;
        health = maxHealth;
        maxShield = 100;
        shield = maxShield;
        statMultipliers = new float[]{1, 1, 1, 1, 1, 1};
    }

    /**
     * Acts based on a few conditions. If this actors health is less than 0, it sets itself as inactive.
     * Then it moves in the x direction. If it collides with an obstacle, it calls the onObstacleCollision method. If it is out
     * of bounds, it immediately moves back into the room. This process is repeated in the y direction. Finally, if this actor
     * interacts with its opponent, it calls the onOpponentInteraction method.
     * @param d The drawing surface to be acted upon
     * @param room The room the actor is currently in
     */
    @Override
    public void act(DrawingSurface d, Room room) {
        if (health <= 0) {
            setActive(false);
        }
        makeMoveX(d, room);
        Obstacle collision = collidesWObstacle(room);
        if (collision != null) {
            onObstacleCollision(true, collision);
        }
        if (!room.inBounds(image)) {
            bounceBackX();
        }
        makeMoveY(d, room);
        collision = collidesWObstacle(room);
        if (collision != null) {
            onObstacleCollision(false, collision);
        }
        if (!room.inBounds(image)) {
            bounceBackY();
        }
        MovingActor opponent = interactsWOppponent(room);
        if (opponent != null) {
            onOpponentInteraction(opponent);
        }

    }

    protected abstract void makeMoveX(DrawingSurface drawingSurface, Room room);

    protected abstract void makeMoveY(DrawingSurface drawingSurface, Room room);

    private Obstacle collidesWObstacle(Room room) {
        for (Obstacle o :
                room.getObstacles()) {
            if (intersects(o)) {
                return o;
            }
        }
        return null;

    }

    protected abstract MovingActor interactsWOppponent(Room room);

    protected void onObstacleCollision(boolean isX, Obstacle obstacle) {
        if (isX) {
            bounceBackX();
            if (obstacle.getImage().getX() > image.getX()) {
                vx -= 3;
            } else {
                vx += 3;
            }
        } else {
            bounceBackY();
            if (obstacle.getImage().getY() > image.getY()) {
                vy -= 3;
            } else {
                vy += 3;
            }
        }
    }

    protected abstract void onOpponentInteraction(MovingActor opponent);


    /**
     * Bounces this actor back, moves it to its previous location and decreases its velocity in the opposite direction to 1/3 of its original
     */
    protected void bounceBackX() {

        image.translate(-vx, 0);
    }

    /**
     * Bounces this actor back, moves it to its previous location and decreases its velocity in the opposite direction to 1/3 of its original
     */
    protected void bounceBackY() {

        image.translate(0, -vy);
    }


    /**
     * Accelerates this actor in the x direction of the specified angle
     *
     * @param angle The specified angle
     */
    protected void moveX(float angle) {
        float newVx = (float) (vx + Math.cos(angle) * accel);
        if (Math.abs(newVx) < maxV) {
            vx = newVx;
        } else if (newVx < 0) {
            vx = -maxV;
        } else {
            vx = maxV;
        }
        vx *= 0.9f;
        if (Math.abs(vx) < 0.1) {
            vx = 0;
        }

        image.translate(vx, 0);
    }

    /**
     * Accelerates this actor in the y direction of the specified angle
     *
     * @param angle The specified angle
     */
    protected void moveY(float angle) {
        float newVy = (float) (vy + Math.sin(angle) * accel);
        if (Math.abs(newVy) < maxV) {
            vy = newVy;
        } else if (newVy < 0) {
            vy = -maxV;
        } else {
            vy = maxV;
        }
        vy *= 0.9f;
        if (Math.abs(vy) < 0.1) {
            vy = 0;
        }
        image.translate(0, vy);
    }

    /**
     * Accelerates this object in the x direction from a boolean array with two values,
     * first of which is whether or not to move left and the second of which is whether or not to move right.
     * If both or neither of these values are false, there will be no movement in the x direction
     *
     * @param directions A boolean array where the first values specifies whether not to move to the immediate left
     *                   and the second specifies whether or not to move to the immediate right
     */
    protected void moveX(boolean[] directions) {
        int left = directions[0] ? -1 : 0;
        int right = directions[1] ? 1 : 0;
        int netX = left + right;

        float newVx = vx + netX * accel;

        if (Math.abs(newVx) < maxV) {
            vx = newVx;
        } else if (newVx < 0) {
            vx = -maxV;
        } else {
            vx = maxV;
        }
        if (Math.abs(vx) < 0.1) {
            vx = 0;
        }

        vx *= 0.9f;
        image.translate(vx, 0);
    }

    /**
     * Accelerates this object in the y direction from a boolean array with two values,
     * first of which is whether or not to move up and the second of which is whether or not to move down.
     * If both or neither of these values are false, there will be no movement in the y direction
     *
     * @param directions A boolean array where the first values specifies whether not to move directly up
     *                   and the second specifies whether or not to move directly down
     */
    protected void moveY(boolean[] directions) {

        int up = directions[0] ? -1 : 0;
        int down = directions[1] ? 1 : 0;
        int netY = up + down;
        float newVy = vy + netY * accel;
        if (Math.abs(newVy) < maxV) {
            vy = newVy;
        } else if (newVy < 0) {
            vy = -maxV;
        } else {
            vy = maxV;
        }
        vy *= 0.9f;
        if (Math.abs(vy) < 0.1) {
            vy = 0;
        }

        image.translate(0, vy);
    }


    protected void updateState(ActorState newState) {
        state = newState;
    }

    protected ActorState getState() {
        return state;
    }

    /**
     * Takes damage from the effects of the specific damage.
     * @param damage The given damage
     */
    public void interceptHitBox(Damage damage) {
        health -= Calculator.calculateDamage(damage, statMultipliers);
    }


    protected void incrementAllAttackStats(float buff) {
        for (int i = 0; i < 3; i++) {
            statMultipliers[i] += buff;
        }
    }

    protected void incrementAllDefenseStats(float buff) {
        for (int i = 3; i < 6; i++) {
            statMultipliers[i] += buff;
        }
    }

    protected void incrementAllStats(float buff) {
        for (int i = 0; i < statMultipliers.length; i++) {
            statMultipliers[i] += buff;
        }
    }

    protected void incrementStat(Stat stat, float buff) {
        statMultipliers[stat.getValue()] += buff;
    }

    protected void setStat(Stat stat, float value) {
        statMultipliers[stat.getValue()] = value;
    }

    protected float getStat(Stat stat) {
        return statMultipliers[stat.getValue()];
    }

    /**
     * Returns the health of this current actor
     * @return The health of this actor
     */
    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    protected int getMaxHealth() {
        return maxHealth;
    }

    protected int getShield() {
        return shield;
    }

    protected void setShield(int shield) {
        this.shield = shield;
    }

    protected int getMaxShield() {
        return maxShield;
    }
}
