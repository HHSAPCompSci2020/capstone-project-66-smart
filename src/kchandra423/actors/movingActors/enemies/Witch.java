package kchandra423.actors.movingActors.enemies;

import kchandra423.graphics.textures.KImage;
import kchandra423.utility.AssetLoader;

/**
 * A Enemy with the textures of a Witch
 * @author Spencer Ye
 * Last Revised: 5/12/2021
 */
public class Witch extends RangedEnemy
{

	public Witch(float x, float y) 
	{
		super(new KImage[] {AssetLoader.getImage(AssetLoader.WITCH_IDLE),
				AssetLoader.getImage(AssetLoader.WITCH_MOVING),
				AssetLoader.getImage(AssetLoader.WITCH_ATTACK),
				AssetLoader.getImage(AssetLoader.WITCH_DEATH)}, 4f, 0.5f);
		image.moveTo(x, y);
	}

}
