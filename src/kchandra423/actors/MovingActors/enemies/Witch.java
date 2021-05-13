package kchandra423.actors.MovingActors.enemies;

import kchandra423.graphics.textures.KImage;
import kchandra423.utility.AssetLoader;

/**
 * A Enemy with the textures of a Witch
 * @author Spencer Ye
 * Last Revised: 5/12/2021
 */
public class Witch extends Enemy
{

	public Witch(float maxV, float accel) 
	{
		super(new KImage[] {AssetLoader.getImage(AssetLoader.WITCH_IDLE), 
				AssetLoader.getImage(AssetLoader.WITCH_ATTACK), 
				AssetLoader.getImage(AssetLoader.WITCH_MOVING), 
				AssetLoader.getImage(AssetLoader.WITCH_DEATH)}, maxV, accel);
	}

}
