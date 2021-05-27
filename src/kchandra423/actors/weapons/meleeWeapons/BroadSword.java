package kchandra423.actors.weapons.meleeWeapons;

import kchandra423.utility.AssetLoader;

public class BroadSword extends MeleeWeapon{
    /**
     * Creates an actor with the specified image
     *
     * @param stats The stats given to the broadsword when it is created
     */
    public BroadSword( float[] stats) {
        super(AssetLoader.getImage(AssetLoader.Sprite.SWORD), 40, stats, (float) (Math.PI/4),0.5f);
    }
}
