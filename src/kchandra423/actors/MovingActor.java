package kchandra423.actors;

import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;

/**
 * Moving actors differ from actors in that they have acceleration based physics already built into them.
 * They have a maximum velocity, and an x and y velocity, and an acceleration. All movement is done seperatley on the x and y axis.
 * This is to ensure that a collision created from the x direction will not affect the y velocity.
 * It is recommended to move in the x direction, check for intersection (if it collides with anything, bounce back in the x direction), and then do the same thing in the y direction
 *
 * @see kchandra423.actors.Actor
 * @author Kumar Chandra
 */
public abstract class MovingActor extends Actor {
    protected float vx, vy;
    protected float maxV;
    protected float accel;

    /**
     * Creates a new Moving actor with the specified image, acceleration and maximum velocity
     * @param image The specified image
     * @param maxV This actors maximum velocity
     * @param accel This actors acceleration
     */
    protected MovingActor(KImage image, float maxV, float accel) {
        super(image);
        vx = 0;
        vy = 0;
        this.maxV = maxV;
        this.accel = accel;
    }

    /**
     * Bounces this actor back, moves it to its previous location and decreases its velocity in the opposite direction to 1/3 of its original
     */
    protected void bounceBackX() {
        image.translate(-vx, 0);
        vx *= -0.3f;
    }
    /**
     * Bounces this actor back, moves it to its previous location and decreases its velocity in the opposite direction to 1/3 of its original
     */
    protected void bounceBackY() {
        image.translate(0, -vy);
        vy *= -0.3f;
    }

    /**
     * Accelerates this actor in the x direction of the specified angle
     * @param angle The specified angle
     */
    protected void moveX(float angle){
        float newVx = (float) (vx + Math.cos(angle) * accel);
        if (Math.abs(newVx) < maxV) {
            vx = newVx;
        } else if (newVx < 0) {
            vx = -maxV;
        } else {
            vx = maxV;
        }
        vx *= 0.9f;

        image.translate(vx, 0);
    }

    /**
     * Accelerates this actor in the y direction of the specified angle
     * @param angle The specified angle
     */
    protected void moveY(float angle){
        float newVy = (float) (vy + Math.sin(angle) * accel);
        if (Math.abs(newVy) < maxV) {
            vy = newVy;
        } else if (newVy < 0) {
            vy = -maxV;
        } else {
            vy = maxV;
        }
        vy *= 0.9f;

        image.translate(0, vy);
    }

    /**
     * Accelerates this object in the x direction from a boolean array with two values,
     * first of which is whether or not to move left and the second of which is whether or not to move right.
     * If both or neither of these values are false, there will be no movement in the x direction
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

        vx *= 0.9f;
        image.translate(vx, 0);
    }
    /**
     * Accelerates this object in the y direction from a boolean array with two values,
     * first of which is whether or not to move up and the second of which is whether or not to move down.
     * If both or neither of these values are false, there will be no movement in the y direction
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

        image.translate(0, vy);
    }
}
