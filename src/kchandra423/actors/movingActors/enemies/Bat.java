package kchandra423.actors.movingActors.enemies;

import kchandra423.graphics.textures.KImage;
import kchandra423.utility.AssetLoader;

/**
 * A Enemy with the textures of a Bat
 * 
 * @author Spencer Ye Last Revised: 5/12/2021
 */
public class Bat extends Enemy {

	public Bat(float x, float y) {
		super(new KImage[] { AssetLoader.getImage(AssetLoader.BAT_IDLE), AssetLoader.getImage(AssetLoader.BAT_MOVING),
				AssetLoader.getImage(AssetLoader.BAT_ATTACK), AssetLoader.getImage(AssetLoader.BAT_DEATH) }, 7, 1);

		image.moveTo(x, y);
	}

}
