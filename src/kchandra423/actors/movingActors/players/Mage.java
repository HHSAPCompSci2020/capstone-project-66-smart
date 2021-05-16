package kchandra423.actors.MovingActors.players;

import kchandra423.actors.MovingActors.MovingActor;
import kchandra423.actors.weapons.guns.MagicStaff;
import kchandra423.utility.AssetLoader;

public class Mage extends Player {
    private static final float[] stats = MovingActor.createStates(1.25f, 3f, 0.5f, 1f, 3f, 0.3f);
    public Mage(float x, float y) {
        super(AssetLoader.getImage(AssetLoader.Sprite.MAGE_IDLE), AssetLoader.getImage(AssetLoader.Sprite.MAGE_ACTIVE),
                9, 1f,stats,
                75,
                new MagicStaff(stats));
        image.moveTo(x, y);
    }
}
