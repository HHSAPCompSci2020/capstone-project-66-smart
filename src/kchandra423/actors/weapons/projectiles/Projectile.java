package kchandra423.actors.weapons.projectiles;

import kchandra423.actors.Actor;
import kchandra423.levels.Room;
import kchandra423.actors.players.enemies.Enemy;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;

import java.awt.*;
import java.util.ArrayList;

public class Projectile extends Actor {
    private boolean ally;
    protected float v;
    protected float angle;


    public Projectile(KImage image, float v, float angle) {
        super(image);
        ally = true;
        image.setAngle(angle);
        this.v = v;
        this.angle = angle;
    }
    public Rectangle getBounds(){
        return image.getBounds();
    }
    public void act(DrawingSurface d, Room r) {
        image.translate((float) (v * Math.cos(angle)), (float) (v * Math.sin(angle)));
        if (!r.inBounds(image)){
            active = false;
            return;
        }
        if(ally){

            ArrayList<Enemy> enemies = r.getEnemies();
            for (Enemy e : enemies) {
                if (intersects(e)) {
                    active = false;
                    e.setActive(false);

                }
            }
        }else{

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
