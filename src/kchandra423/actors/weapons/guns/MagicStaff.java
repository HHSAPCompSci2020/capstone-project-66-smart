package kchandra423.actors.weapons.guns;

import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.utility.AssetLoader;

public class MagicStaff extends Gun {
    public MagicStaff(float[] stats) {
        super(AssetLoader.getImage(AssetLoader.Sprite.MAGIC_STAFF), 0.3f, (float) (Math.PI / 8), AssetLoader.Sprite.MAGIC_BALL, 7, stats, DamageTypes.MAGIC, 1,30);
    }
}
