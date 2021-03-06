package kchandra423.actors.weapons.guns;

import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.weapons.projectiles.Projectile;
import kchandra423.utility.AssetLoader;

/**
 * Represents a gun that shoots projectiles
 */
public class SMG extends Gun {
    /**
     * Creates a new SMG with the given stats
     *
     * @param stats The given stats
     */
    public SMG(float[] stats) {
        super(AssetLoader.getImage(AssetLoader.Sprite.SMG), 0.1f,
                (float) Math.PI / 8,
                new Projectile(AssetLoader.getImage(AssetLoader.Sprite.BULLET),
                        20f, 0, true, stats,
                        DamageTypes.RANGED, 5), 1,
                0.5f, 30);
    }
}
