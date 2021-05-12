package kchandra423.actors.MovingActors;

import kchandra423.actors.MovingActors.constants.ActorState;
import kchandra423.actors.MovingActors.enemies.Enemy;
import kchandra423.actors.weapons.Gun;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;
import kchandra423.utility.Calculator;
import processing.core.PApplet;

import java.awt.event.KeyEvent;

/**
 * A player represents the users Character avatar that moves and fights the enemies
 * @author Kumar Chandra
 * @see kchandra423.actors.Actor
 * @see kchandra423.actors.MovingActors.MovingActor
 * @see kchandra423.actors.MovingActors.enemies.Enemy
 */
public class Player extends MovingActor {
    private final KImage idleImage;
    private final KImage activeImage;
    private final Gun weapon;

    /**
     * Creates a player with the specified idle and active animations
     * @param idle The idle sprite of this player
     * @param active The active sprite of this player
     */
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
    protected MovingActor interactsWOppponent(Room room) {
        for (Enemy e :
                room.getEnemies()) {
            if (intersects(e)) {
                return e;
            }
        }
        return null;
    }

    @Override
    protected void onOpponentInteraction(MovingActor opponent) {
        //pretend something thatll happen, idk it depends how we design it
    }


    @Override
    protected void updateState(ActorState newState) {
        super.updateState(newState);
        switch (newState) {
            case IDLE:
                idleImage.copyInformation(image);
                image = idleImage;
                break;
            case MOVING:
                activeImage.copyInformation(image);
                image = activeImage;
                break;
        }
    }

    @Override
    public void setActive(boolean b) {
        //idk how this will work for now, but itll trigger so end of the game
    }

}
