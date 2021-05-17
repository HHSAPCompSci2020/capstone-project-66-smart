package kchandra423.actors.weapons.guns;

import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.utility.AssetLoader;

public class SMG extends Gun {
    public SMG(float[] stats) {
        super(AssetLoader.getImage(AssetLoader.Sprite.SMG), 0.1f, (float) Math.PI/8, AssetLoader.Sprite.BULLET, 20f, stats, DamageTypes.RANGED, 1, 10);
    }
}
