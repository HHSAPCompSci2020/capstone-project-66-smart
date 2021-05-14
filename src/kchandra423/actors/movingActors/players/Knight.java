package kchandra423.actors.movingActors.players;

import kchandra423.actors.movingActors.MovingActor;
import kchandra423.actors.weapons.MeleeWeapon;
import kchandra423.graphics.textures.KImage;
import kchandra423.utility.AssetLoader;

public class Knight extends Player{

    public Knight(float x, float y) {
        super(AssetLoader.getImage(AssetLoader.Sprite.KNIGHT_IDLE),
                AssetLoader.getImage(AssetLoader.Sprite.KNIGHT_IDLE),
                7, 0.7f,
                MovingActor.createStates(0.8f,0.5f,2f,2f, 1f, 3f),
                150,
                new MeleeWeapon(AssetLoader.getImage(AssetLoader.Sprite.SWORD),1f)
                );
        image.moveTo(x,y);
    }
}
