package kchandra423.actors.MovingActors.players;

import kchandra423.actors.MovingActors.MovingActor;
import kchandra423.actors.weapons.MeleeWeapon;
import kchandra423.utility.AssetLoader;

public class Knight extends Player {
    private static final float[] stats =
            MovingActor.createStates(0.8f, 0.5f, 2f, 2f, 1f, 3f);

    public Knight(float x, float y) {
        super(AssetLoader.getImage(AssetLoader.Sprite.KNIGHT_IDLE),
                AssetLoader.getImage(AssetLoader.Sprite.KNIGHT_IDLE),
                7, 0.7f, stats,
                150,
                new MeleeWeapon(AssetLoader.getImage(AssetLoader.Sprite.SWORD))
        );
        image.moveTo(x, y);
    }
}
