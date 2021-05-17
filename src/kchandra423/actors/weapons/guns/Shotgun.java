package kchandra423.actors.weapons.guns;

import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.utility.AssetLoader;

public class Shotgun extends Gun {
    public Shotgun(float[] stats) {
        super(AssetLoader.getImage(AssetLoader.Sprite.SHOTGUN), 0.5f, (float)Math.PI/5, AssetLoader.Sprite.BULLET, 20f, stats, DamageTypes.RANGED, 5, 10);
    }
}
