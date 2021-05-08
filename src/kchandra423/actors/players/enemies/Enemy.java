package kchandra423.actors.players.enemies;

import kchandra423.actors.MovingActor;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.actors.players.ActorState;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;
import kchandra423.levels.Room;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a generic enemy type that attempts to fight the player.
 *
 * @author Kumar Chandra
 * @see kchandra423.actors.Actor
 * @see kchandra423.actors.MovingActor
 * @see EnemyAI
 */
public class Enemy extends MovingActor {

    private final KImage idle;
    private final KImage moving;
    private final KImage attack;
    private final KImage death;
    private final EnemyAI ai;
    private Timer timer;

    //    public Enemy(KImage[] images){
//        this(images[0],5,0.7f);
//    }
    private Enemy(KImage[] images, float maxV, float accel) {
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
        float decision = ai.makeMovementDecision(r);


        if (state!=ActorState.ATTACKING) {
            if (intersects(r.getPlayer())) {
                state = ActorState.ATTACKING;
                vx =0;
                vy =0;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        state = ActorState.IDLE;
                    }
                }, 500L);
                updateState();
                return;
            }
            ArrayList<Obstacle> obstacles = r.getObstacles();
            moveX(decision);
            for (Obstacle o : obstacles) {
                if (intersects(o)) {
//                bounceBackX();
                    if (o.getImage().getX() > image.getX()) {
                        vx -= 2;
                    } else {
                        vx += 2;
                    }
                }
            }
            if (!r.inBounds(image)) {
                bounceBackX();
            }
            moveY(decision);
            for (Obstacle o : obstacles) {
                if (intersects(o)) {
//                bounceBackY();
                    if (o.getImage().getY() > image.getY()) {
                        vy -= 2;
                    } else {
                        vy += 2;
                    }
                }
            }
            if (!r.inBounds(image)) {
                bounceBackY();
            }
            if (Math.abs(vx) < 0.1f && Math.abs(vy) < 0.1f) {
                state = ActorState.IDLE;
            } else {
                state = ActorState.MOVING;
            }


        }
        if (Math.abs(decision) > Math.PI / 2) {
            image.setReflected(true);
        } else {
            image.setReflected(false);

        }

        updateState();
    }

    /**
     * Creates a random enemy at a given location. Currently just for testing
     *
     * @param x The x coord of the given location
     * @param y The y coord of the given location
     * @return A new Enemy at that location (currently only a bat or goblin)
     */
    public static Enemy createEnemy(float x, float y) {
        KImage idle;
        KImage moving;
        KImage attacking;
        KImage death;
        if (Math.random() < 1) {
            Area a = KImage.loadArea(Texture.TextureBuilder.getTexture("res/Images/Enemies/Moving/Goblin.gif"));
            idle = new KImage(x, y, Texture.TextureBuilder.getTexture("res/Images/Enemies/Idle/Goblin.gif"), (Area) a.clone());
            attacking = new KImage(x, y,Texture.TextureBuilder.getTexture("res/Images/Enemies/Attack/Goblin.gif"), (Area) a.clone());
            moving = new KImage(x,y ,Texture.TextureBuilder.getTexture("res/Images/Enemies/Moving/Goblin.gif"), (Area) a.clone());
            death = new KImage(x,y,Texture.TextureBuilder.getTexture("res/Images/Enemies/Idle/Goblin.gif"), (Area) a.clone());
        } else {
            idle = new KImage(Texture.TextureBuilder.getTexture("res/Images/Enemies/Idle/Bat.gif"), x, y);
            attacking = new KImage(Texture.TextureBuilder.getTexture("res/Images/Enemies/Attack/Bat.gif"), x, y);
            moving = new KImage(Texture.TextureBuilder.getTexture("res/Images/Enemies/Moving/Bat.gif"), x, y);
            death = new KImage(Texture.TextureBuilder.getTexture("res/Images/Enemies/Idle/Bat.gif"), x, y);
        }
        return new Enemy(new KImage[]{idle, moving, attacking, death}, 5, 0.7f);
    }

    @Override
    protected void updateState() {
        switch (state) {
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
}
