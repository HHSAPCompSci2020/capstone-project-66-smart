package kchandra423.actors.MovingActors;

import kchandra423.actors.MovingActors.enemies.Enemy;
import kchandra423.actors.weapons.Gun;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;
import kchandra423.utility.Calculator;
import processing.core.PApplet;

import java.awt.event.KeyEvent;

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
        super.act(d, r);
        float angle = (float) Calculator.calculateAngle(d.width / 2.0f, d.height / 2.0f,
                d.mouseX, d.mouseY);
        if (angle < 0) {
            angle += Math.PI * 2;
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
        if (Math.abs(vx) < 0.1f && Math.abs(vy) < 0.1f) {
            updateState(ActorState.IDLE);
        } else {
            updateState(ActorState.MOVING);
        }
        weapon.act(d, r);
        weapon.getImage().moveTo(image.getX() + image.getWidth() / 2.0f, image.getY() + image.getHeight() / 2.0f);

    }

    @Override
    protected void makeMoveX(DrawingSurface drawingSurface, Room room) {
        boolean left = DrawingSurface.getKey(KeyEvent.VK_A);
        boolean right = DrawingSurface.getKey(KeyEvent.VK_D);
        moveX(new boolean[]{left, right});
    }

    @Override
    protected void makeMoveY(DrawingSurface drawingSurface, Room room) {
        boolean up = DrawingSurface.getKey(KeyEvent.VK_W);
        boolean down = DrawingSurface.getKey(KeyEvent.VK_S);
        moveY(new boolean[]{up, down});
    }

    @Override
    protected MovingActor collidesWOppponent(Room room) {
        for (Enemy e :
                room.getEnemies()) {
            if (intersects(e)) {
                return e;
            }
        }
        return null;
    }

    @Override
    protected void onOpponentCollision(MovingActor opponent) {
        //pretend something thatll happen, idk it depends how we design it
    }


    @Override
    protected void updateState(ActorState newState) {
        super.updateState(newState);
        switch (newState) {
            case IDLE:
                idleImage.setAngle(image.getAngle());
                idleImage.setReflected(image.isReflected());
                idleImage.setReversed(image.isReversed());
                idleImage.moveTo(image.getX(), image.getY());
                image = idleImage;
                break;
            case MOVING:
                activeImage.setAngle(image.getAngle());
                activeImage.setReflected(image.isReflected());
                activeImage.setReversed(image.isReversed());
                activeImage.moveTo(image.getX(), image.getY());
                image = activeImage;
                break;
        }
    }

    @Override
    public void setActive(boolean b) {
        //idk how this will work for now, but itll trigger so end of the game
    }

}