package kchandra423.actors.movingActors.players;

import kchandra423.actors.movingActors.MovingActor;
import kchandra423.graphics.textures.KImage;
import kchandra423.utility.AssetLoader;

public class Rogue extends Player{
    public Rogue(float x, float y) {
        super(AssetLoader.getImage(AssetLoader.Sprite.ROGUE_IDLE), AssetLoader.getImage(AssetLoader.Sprite.ROGUE_IDLE), 13.5f, 1.5f, MovingActor.createStates(2f,1f,1.25f,1.5f, 1f, 1f),100);
        image.moveTo(x,y);
    }
}
