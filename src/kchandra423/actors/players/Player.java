package kchandra423.actors.players;

import kchandra423.actors.obstacles.Obstacle;
import kchandra423.actors.players.enemies.Enemy;
import kchandra423.actors.weapons.Gun;
import kchandra423.actors.MovingActor;
import kchandra423.levels.Room;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.utility.Calculator;
import processing.core.PApplet;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends MovingActor {
    private final KImage idleImage;
    private final KImage activeImage;
    private final Gun weapon;

    public Player(KImage idle, KImage active) {
        super(idle, 10, 1f);
        this.idleImage = idle;
        this.activeImage = active;
        weapon = new Gun(image.getWidth() / 2.0f, image.getHeight() / 2.0f);
    }


    @Override
    public void draw(DrawingSurface d) {
        super.draw(d);
        weapon.draw(d);
    }

    @Override
    public void act(DrawingSurface d, Room r) {
        float angle = (float) Calculator.calculateAngle(d.width / 2.0f, d.height / 2.0f,
                d.mouseX, d.mouseY);
                if(angle<0){
            angle+=Math.PI*2;
        }
        if (!Float.isNaN(angle)) {
            weapon.getImage().setAngle(angle);
        }
        if (d.mousePressed) {
            if (d.mouseButton == PApplet.LEFT) {
                weapon.fire();
            }
        }
        if (angle > Math.PI / 2 && angle < 3 * Math.PI / 2) {
            image.setReflected(true);
            weapon.getImage().setReflected(true);
        } else {
            image.setReflected(false);
            weapon.getImage().setReflected(false);
        }

        weapon.act(d, r);
        boolean up = DrawingSurface.getKey(KeyEvent.VK_W);
        boolean left = DrawingSurface.getKey(KeyEvent.VK_A);
        boolean down = DrawingSurface.getKey(KeyEvent.VK_S);
        boolean right = DrawingSurface.getKey(KeyEvent.VK_D);

        moveX(new boolean[]{left, right});
        ArrayList<Obstacle> obstacles = r.getObstacles();
        for (Obstacle o : obstacles) {
            if (intersects(o)) {
                bounceBackX();
            }
        }
        if (!r.inBounds(image)) {
            bounceBackX();
        }
        moveY(new boolean[]{up, down});
        for (Obstacle o : obstacles) {
            if (intersects(o)) {
                bounceBackY();
            }
        }
        if (!r.inBounds(image)) {
            bounceBackY();
        }


        weapon.getImage().moveTo(image.getX() + image.getWidth() / 2.0f, image.getY() + image.getHeight() / 2.0f);
        ArrayList<Enemy> enemies = r.getEnemies();
        for (Enemy e : enemies) {
            if (intersects(e)) {
//                System.out.println("you wouldve taken damage");
                return;
            }
        }

    }


    @Override
    protected void updateState() {

    }
}
