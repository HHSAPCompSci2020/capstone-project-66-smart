package kchandra423.actors.weapons;

import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;

public class MeleeWeapon extends Weapon{
    /**
     * Creates an actor with the specified image
     *
     * @param image The specified image
     */
    public MeleeWeapon(KImage image, float swingTime) {
        super(image);
    }

    @Override
    public void act(DrawingSurface d, Room room) {

    }

    @Override
    public void fire() {

    }
}
