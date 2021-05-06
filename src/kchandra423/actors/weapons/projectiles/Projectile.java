package kchandra423.actors.weapons.projectiles;

import kchandra423.actors.Actor;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.actors.players.enemies.Enemy;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;
import kchandra423.utility.Calculator;

import java.awt.*;
import java.util.ArrayList;

/**
 * Represents any sort of projectile that moves in a specified direction every frame
 *
 * @see kchandra423.actors.Actor
 * @author Kumar Chandra
 */
public class Projectile extends Actor {
    private boolean ally;
    protected float v;
    protected final float initialAngle;


    /**
     * Creates a new Projectile with the specified image, and initial velocity and angle
     * @param image The specified image
     * @param v The initial velocity of this projectile
     * @param angle The initial angle of this projectile
     */
    public Projectile(KImage image, float v, float angle) {
        super(image);
        ally = true;
        initialAngle = angle;
        image.setAngle(angle);
        this.v = v;
    }

    /**
     * Moves this projectile and does collision detection and handling
     * @param d The drawing surface to be acted upon
     * @param room The room the actor is currently in
     */
    public void act(DrawingSurface d, Room room) {
        if (active) {


//            Enemy closest = r.getClosestEnemy(image.getX(), image.getY());
//            if (closest != null) {
//                Rectangle ours = image.getBounds();
//                Rectangle other = closest.getImage().getBounds();
//                float thisx = (float) ours.getCenterX(), thisy = (float) ours.getCenterY();
//                float otherx = (float) other.getCenterX(), othery = (float) other.getCenterY();
//                float target = (float) Calculator.calculateAngle(thisx, thisy, otherx, othery);
//                float currentAngle = image.getAngle();
//                System.out.println(currentAngle);
//                if ((initialAngle < Math.PI/2 && initialAngle > -Math.PI/2) || initialAngle > 3*Math.PI/2 || initialAngle < -3*Math.PI/2) {
//                    if (target < 0) {
//                        target += Math.PI * 2;
//                    }
//                }
//                float diff = target - currentAngle;
//                image.rotate(diff / 4);
//            }
            image.translate((float) (v * Math.cos(image.getAngle())), (float) (v * Math.sin(image.getAngle())));
            if (!room.inBounds(image)) {
                active = false;
                return;
            }
            if (ally) {


                ArrayList<Enemy> enemies = room.getEnemies();
                for (Enemy e : enemies) {
                    if (intersects(e)) {
                        active = false;
//                        e.setActive(false);
                        return;
                    }
                }
                ArrayList<Obstacle> obstacles = room.getObstacles();
                for (Obstacle o : obstacles) {
                    if (intersects(o)) {
                        active = false;
//                        e.setActive(false);
                        return;
                    }
                }
            } else {

            }
        }
    }



}
