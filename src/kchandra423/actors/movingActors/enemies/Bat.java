package kchandra423.actors.movingActors.enemies;

import kchandra423.actors.movingActors.MovingActor;
import kchandra423.graphics.textures.KImage;
import kchandra423.utility.AssetLoader;

/**
 * A Enemy with the textures of a Bat
 *
 * @author Spencer Ye Last Revised: 5/12/2021
 */
public class Bat extends Enemy {

    public Bat(float x, float y) {
        super(new KImage[]{AssetLoader.getImage(AssetLoader.Sprite.BAT_IDLE),
                AssetLoader.getImage(AssetLoader.Sprite.BAT_MOVING),
                AssetLoader.getImage(AssetLoader.Sprite.BAT_ATTACK),
                AssetLoader.getImage(AssetLoader.Sprite.BAT_DEATH)}, 10, 2, MovingActor.createStates(1,1,1.5f,0.5f, 0.25f, 0.75f),30, 6*100L, 5);

        image.moveTo(x, y);
    }

}
