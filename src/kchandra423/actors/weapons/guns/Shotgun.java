package kchandra423.actors.weapons.guns;

import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.weapons.projectiles.PiercingProjectile;
import kchandra423.utility.AssetLoader;

/**
 * Represents a shotgun that shoots multiple projectiles
 */
public class Shotgun extends Gun {
    /**
     * Creates a new shotgun with the given stats
     * @param stats The given stats
     */
    public Shotgun(float[] stats) {
        super(AssetLoader.getImage(AssetLoader.Sprite.SHOTGUN), 0.5f,
                (float) Math.PI / 6,
                new PiercingProjectile(AssetLoader.getImage(AssetLoader.Sprite.BULLET),
                        20f, 0, true, stats,
                        DamageTypes.MAGIC, 5,2),
                5, 0.75f, 6);
    }
}
