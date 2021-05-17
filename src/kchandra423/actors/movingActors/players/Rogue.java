package kchandra423.actors.MovingActors.players;

import kchandra423.actors.MovingActors.MovingActor;
import kchandra423.actors.weapons.guns.SMG;
import kchandra423.utility.AssetLoader;

public class Rogue extends Player {
    private static final float[] stats = MovingActor.createStates(2f, 1f, 1.25f, 1.5f, 1f, 1f);

    public Rogue(float x, float y) {
        super(AssetLoader.getImage(AssetLoader.Sprite.ROGUE_IDLE),
                AssetLoader.getImage(AssetLoader.Sprite.ROGUE_IDLE),
                13.5f, 1.5f, stats,
                100
                , new SMG(stats));
        image.moveTo(x, y);
    }
}
