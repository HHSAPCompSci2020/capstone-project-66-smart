package kchandra423.actors.movingActors.players;

import kchandra423.actors.movingActors.MovingActor;
import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.weapons.Gun;
import kchandra423.utility.AssetLoader;

public class Rogue extends Player{
    public Rogue(float x, float y) {
        super(AssetLoader.getImage(AssetLoader.Sprite.ROGUE_IDLE),
                AssetLoader.getImage(AssetLoader.Sprite.ROGUE_IDLE),
                13.5f, 1.5f,
                MovingActor.createStates(2f,1f,1.25f,1.5f, 1f, 1f),
                100
        , new Gun(AssetLoader.getImage(AssetLoader.Sprite.SHOTGUN),
                        0.5f, (float) (Math.PI / 3),
                        AssetLoader.Sprite.BULLET, 20,
                        MovingActor.createStates(2f,1f,1.25f,1.5f, 1f, 1f),
                        DamageTypes.RANGED, 5));
        image.moveTo(x,y);
    }
}
