package kchandra423.actors.MovingActors.enemies;

import kchandra423.graphics.textures.KImage;
import kchandra423.utility.AssetLoader;

/**
 * A Enemy with the textures of a Goblin
 * @author Spencer Ye
 * Last Revised: 5/12/2021
 */
public class Goblin extends Enemy
{

	public Goblin(float maxV, float accel) 
	{
		super(new KImage[] {AssetLoader.getImage(AssetLoader.GOBLIN_IDLE),
				AssetLoader.getImage(AssetLoader.GOBLIN_MOVING),
				AssetLoader.getImage(AssetLoader.GOBLIN_ATTACK),
				AssetLoader.getImage(AssetLoader.GOBLIN_DEATH)}, maxV, accel);
	}

}
