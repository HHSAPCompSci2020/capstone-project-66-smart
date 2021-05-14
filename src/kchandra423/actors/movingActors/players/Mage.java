package kchandra423.actors.movingActors.players;

import kchandra423.actors.movingActors.MovingActor;
import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.weapons.Gun;
import kchandra423.utility.AssetLoader;

public class Mage extends Player {

    public Mage(float x, float y) {
        super(AssetLoader.getImage(AssetLoader.Sprite.MAGE_IDLE), AssetLoader.getImage(AssetLoader.Sprite.MAGE_ACTIVE),
                9, 1f,
                MovingActor.createStates(1.25f, 3f, 0.5f, 1f, 3f, 0.3f),
                75,
                new Gun(AssetLoader.getImage(AssetLoader.Sprite.MAGIC_STAFF), 0.3f, (float) (Math.PI / 8), AssetLoader.Sprite.MAGIC_BALL, 7, MovingActor.createStates(1.25f, 3f, 0.5f, 1f, 3f, 0.3f), DamageTypes.MAGIC, 1));
        image.moveTo(x, y);
    }
}
