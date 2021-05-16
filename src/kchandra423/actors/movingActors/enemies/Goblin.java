package kchandra423.actors.MovingActors.enemies;

import kchandra423.actors.MovingActors.MovingActor;
import kchandra423.graphics.textures.KImage;
import kchandra423.utility.AssetLoader;

/**
 * A Enemy with the textures of a Goblin
 * @author Spencer Ye
 * Last Revised: 5/12/2021
 */
public class Goblin extends Enemy
{

	public Goblin(float x, float y) 
	{
		super(new KImage[] {AssetLoader.getImage(AssetLoader.Sprite.GOBLIN_IDLE),
				AssetLoader.getImage(AssetLoader.Sprite.GOBLIN_MOVING),
				AssetLoader.getImage(AssetLoader.Sprite.GOBLIN_ATTACK),
				AssetLoader.getImage(AssetLoader.Sprite.GOBLIN_DEATH)}, 5, 0.75f, MovingActor.createStates(1,1,1.5f,0.75f, 0.5f, 1.5f),75, 18 * 100L);
		image.moveTo(x, y);
	}

}
