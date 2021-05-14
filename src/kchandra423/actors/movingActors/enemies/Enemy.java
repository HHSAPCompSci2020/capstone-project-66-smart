package kchandra423.actors.movingActors.enemies;

import kchandra423.actors.Damage;
import kchandra423.actors.movingActors.constants.ActorState;
import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.movingActors.MovingActor;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;
import kchandra423.utility.AssetLoader;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a generic enemy type that attempts to fight the player.
 *
 * @author Kumar Chandra
 * @see kchandra423.actors.Actor
 * @see MovingActor
 * @see EnemyAI
 */
public class Enemy extends MovingActor {
    private final KImage idle;
    private final KImage moving;
    private final KImage attack;
    private final KImage death;
    private final EnemyAI ai;
    protected Timer timer;

    protected Enemy(KImage[] images, float maxV, float accel) {
        super(images[0], maxV, accel);
        ai = new EnemyAI(this);
        idle = images[0];
        moving = images[1];
        attack = images[2];
        death = images[3];
        timer = new Timer();
    }

    @Override
    public void act(DrawingSurface d, Room r) {
        if (getState() != ActorState.ATTACKING && getState() != ActorState.DEAD) {
            super.act(d, r);
            if (getState() != ActorState.ATTACKING && getState() != ActorState.DEAD) {
                if (Math.abs(vx) < 0.1f && Math.abs(vy) < 0.1f) {
                    updateState(ActorState.IDLE);
                } else {
                    updateState(ActorState.MOVING);
                }
            }
        }
        if (getState() != ActorState.DEAD) {
            float decision = ai.makeMovementDecision(r);
            image.setReflected(Math.abs(decision) > Math.PI / 2);
        }
    }

    @Override
    protected void makeMoveX(DrawingSurface drawingSurface, Room room) {
        if (getState() != ActorState.ATTACKING) {
            float decision = ai.makeMovementDecision(room);
            moveX(decision);
        }
    }

    @Override
    protected void makeMoveY(DrawingSurface drawingSurface, Room room) {
        if (getState() != ActorState.ATTACKING) {
            float decision = ai.makeMovementDecision(room);
            moveY(decision);
        }
    }

    @Override
    protected MovingActor interactsWOppponent(Room room) {
        return intersects(room.getPlayer()) ? room.getPlayer() : null;
    }

    @Override
    protected void onOpponentInteraction(MovingActor opponent) {
        if (getState() != ActorState.DEAD) {
            updateState(ActorState.ATTACKING);
            opponent.interceptHitBox(new Damage(40, statMultipliers, DamageTypes.MELEE));
            vx = 0;
            vy = 0;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    updateState(ActorState.IDLE);
                }
            }, 500L);
        }
    }


    /**
     * Creates a random enemy at a given location. Currently just for testing
     *
     * @param x The x coord of the given location
     * @param y The y coord of the given location
     * @return A new Enemy at that location (currently only a bat, goblin, or witch)
     */
    public static Enemy createEnemy(float x, float y) {
        KImage idle;
        KImage moving;
        KImage attacking;
        KImage death;
        double rand = Math.random();
        if (rand >0.66) {
            idle = AssetLoader.getImage(AssetLoader.GOBLIN_IDLE);
            attacking = AssetLoader.getImage(AssetLoader.GOBLIN_ATTACK);
            moving = AssetLoader.getImage(AssetLoader.GOBLIN_MOVING);
            death = AssetLoader.getImage(AssetLoader.GOBLIN_DEATH);
            idle.moveTo(x, y);
        } else if (rand > 0.33) {
            idle = AssetLoader.getImage(AssetLoader.WITCH_IDLE);
            attacking = AssetLoader.getImage(AssetLoader.WITCH_ATTACK);
            moving = AssetLoader.getImage(AssetLoader.WITCH_MOVING);
            death = AssetLoader.getImage(AssetLoader.WITCH_DEATH);
            idle.moveTo(x, y);
            return new RangedEnemy(new KImage[]{idle, moving, attacking, death}, 5, 0.7f);
        } else {
            idle = AssetLoader.getImage(AssetLoader.BAT_IDLE);
            attacking = AssetLoader.getImage(AssetLoader.BAT_ATTACK);
            moving = AssetLoader.getImage(AssetLoader.BAT_MOVING);
            death = AssetLoader.getImage(AssetLoader.BAT_DEATH);
            idle.moveTo(x, y);
        }
        return new Enemy(new KImage[]{idle, moving, attacking, death}, 5, 0.7f);
    }

    @Override
    protected void updateState(ActorState newState) {
        super.updateState(newState);
        switch (newState) {
            case IDLE:
                idle.setAngle(image.getAngle());
                idle.setReflected(image.isReflected());
                idle.setReversed(image.isReversed());
                idle.moveTo(image.getX(), image.getY());
                image = idle;
                break;
            case MOVING:
                moving.setAngle(image.getAngle());
                moving.setReflected(image.isReflected());
                moving.setReversed(image.isReversed());
                moving.moveTo(image.getX(), image.getY());
                image = moving;
                break;
            case ATTACKING:
                attack.setAngle(image.getAngle());
                attack.setReflected(image.isReflected());
                attack.setReversed(image.isReversed());
                attack.moveTo(image.getX(), image.getY());
                image = attack;
                break;
            case DEAD:
                death.setAngle(image.getAngle());
                death.setReflected(image.isReflected());
                death.setReversed(image.isReversed());
                death.moveTo(image.getX(), image.getY());
                image = death;
                break;
        }

    }

    @Override
    public void setActive(boolean active) {
        if (!active) {
            updateState(ActorState.DEAD);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Enemy.super.setActive(false);
                }
            }, 18 * 100L);
        }
    }

}
