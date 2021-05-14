package kchandra423.actors.weapons;

import kchandra423.actors.Actor;
import kchandra423.graphics.textures.KImage;

public abstract class Weapon extends Actor {
    /**
     * Creates an actor with the specified image
     *
     * @param image The specified image
     */
    public Weapon(KImage image) {
        super(image);
    }

    public abstract void fire();
}
