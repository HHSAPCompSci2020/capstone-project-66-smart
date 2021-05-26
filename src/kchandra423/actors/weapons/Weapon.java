package kchandra423.actors.weapons;

import kchandra423.actors.Actor;
import kchandra423.graphics.textures.KImage;

import java.util.TimerTask;

/**
 * Weapons used by the player to defeat actors
 * @author Kumar Chandra
 */
public abstract class Weapon extends Actor {
    /**
     * Creates an actor with the specified image
     *
     * @param image The specified image
     */
    public Weapon(KImage image) {
        super(image);
    }

    /**
     * Fires this weapon if it can
     */
    public abstract void fire();

    /**
     * Reloads this weapon
     */
    public abstract void reload();

    /**
     * Gives the current magazine
     * @return the current amount in the magazine
     */
    public abstract int getMagazine();

    /**
     * Gives the maximum magazine size
     * @return The magazine size
     */
    public abstract int getMagazineSize();

    /**
     * Returns the amount of time since this weapon reloaded or NAN if it is not reloading
     * @return The amount of time since this weapon reloaded
     */
    public abstract float getTimeSinceReloaded();

    /**
     * The reload time in seconds
     * @return The reload time
     */
    public abstract float getReloadTime();
}
