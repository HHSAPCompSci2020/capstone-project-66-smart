package kchandra423.actors.movingActors.enemies;

import kchandra423.actors.Damage;
import kchandra423.actors.movingActors.constants.ActorState;
import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.movingActors.MovingActor;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;

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
public abstract class Enemy extends MovingActor {
    private final KImage idle;
    private final KImage moving;
    private final KImage attack;
    private final KImage death;
    private final EnemyAI ai;
    private final long deathTime;
    protected Timer timer;
    private final int damage;

    protected Enemy(KImage[] images, float maxV, float accel, float[] stats, int health, long deathTime, int damage) {
        super(images[0], maxV, accel, stats, health);
        this.deathTime = deathTime;
        ai = new EnemyAI(this);
        idle = images[0];
        moving = images[1];
        attack = images[2];
        death = images[3];
        timer = new Timer();
        this.damage = damage;
    }

    @Override
    public void act(DrawingSurface d, Room r) {
//        System.out.println(health);
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
            opponent.interceptHitBox(new Damage(damage, statMultipliers, DamageTypes.MELEE));
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
            }, deathTime);
        }
    }

}
