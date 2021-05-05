package kchandra423.actors.weapons.projectiles;

import kchandra423.actors.Actor;
import kchandra423.actors.players.enemies.Enemy;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;
import kchandra423.utility.Calculator;

import java.awt.*;
import java.util.ArrayList;

public class Projectile extends Actor {
    private boolean ally;
    protected float v;
//    protected float angle;


    public Projectile(KImage image, float v, float angle) {
        super(image);
        ally = true;
        image.setAngle(angle);
        this.v = v;
        image.setAngle(angle);
    }

    public Rectangle getBounds() {
        return image.getBounds();
    }

    public void act(DrawingSurface d, Room r) {
        if (active) {

            image.translate((float) (v * Math.cos(image.getAngle())), (float) (v * Math.sin(image.getAngle())));
            if (!r.inBounds(image)) {
                active = false;
                return;
            }
            if (ally) {
                Enemy closest = r.getClosestEnemy(image.getX(), image.getY());
                if(closest!=null) {
                    float diff = (float) Calculator.calculateAngle(image.getX(), image.getY(), closest.getImage().getX(), closest.getImage().getY()) - image.getAngle();
                    image.rotate(diff/30);
                }
                ArrayList<Enemy> enemies = r.getEnemies();
                for (Enemy e : enemies) {
                    if (intersects(e)) {
                        active = false;
                        e.setActive(false);
                        return;
                    }
                }
            } else {

            }
        }
    }

    public boolean isActive() {
        return active;
    }

    public void draw(DrawingSurface d) {
        if (active)
            super.draw(d);
        else
            System.out.println("Attempted to draw bullet while inactive");
    }


}
