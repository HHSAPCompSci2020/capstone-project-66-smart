package sye348.levels;

import java.awt.Rectangle;
import java.util.ArrayList;

import kchandra423.actors.MovingActors.enemies.Bat;
import kchandra423.actors.MovingActors.enemies.Enemy;
import kchandra423.actors.MovingActors.enemies.Goblin;
import kchandra423.actors.MovingActors.enemies.Witch;
import kchandra423.actors.MovingActors.players.Player;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.graphics.textures.Texture;
import kchandra423.levels.Room;
import kchandra423.utility.AssetLoader;

/**
 * 
 * @author Spencer Ye
 * @version 1.1.0
 * Last Revised: 5/13/2021
 */
public class LevelThree extends Level
{
	
	//private String player;
	
	public LevelThree(Player player)
	{
		super(getRooms(player));
		//player = character;
	}
	
	private static ArrayList<Room> getRooms(Player player)
	{
		ArrayList<Room> arr = new ArrayList<Room>();
		
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		Rectangle bounds = new Rectangle(1500, 1500);

		enemies.add(new Goblin((float) (Math.random() * (bounds.width - 200) + bounds.x), (float) (Math.random() * (bounds.height - 200) + bounds.y)));
		enemies.add(new Goblin((float) (Math.random() * (bounds.width - 200) + bounds.x), (float) (Math.random() * (bounds.height - 200) + bounds.y)));
		enemies.add(new Goblin((float) (Math.random() * (bounds.width - 200) + bounds.x), (float) (Math.random() * (bounds.height - 200) + bounds.y)));
		enemies.add(new Goblin((float) (Math.random() * (bounds.width - 200) + bounds.x), (float) (Math.random() * (bounds.height - 200) + bounds.y)));
		enemies.add(new Bat((float) (Math.random() * (bounds.width - 200) + bounds.x), (float) (Math.random() * (bounds.height - 200) + bounds.y)));
		enemies.add(new Witch((float) (Math.random() * (bounds.width - 200) + bounds.x), (float) (Math.random() * (bounds.height - 200) + bounds.y)));
		
		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
		Obstacle o = new Obstacle(AssetLoader.getImage(AssetLoader.Sprite.BOX));
		o.getImage().moveTo(300,600);
		obstacles.add(o);
		
		Room r = new Room (Texture.TextureBuilder.getTexture("res/Images/Backgrounds/tiles(manually resized).jpg"), 
				obstacles,
				enemies, 
				player,
				bounds);
		arr.add(r);
		
		
		return arr;
	}
	
//	private static Obstacle getTeleporter()
//	{
////		KImage image = new KImage(800, 800, false, false, TextureBuilder.getTexture("res/Images/Obstacles/Teleporter.png"));
//		KImage image = AssetLoader.getImage(AssetLoader.TELEPORTER);
//		image.moveTo(800,800);
//		return new Obstacle(image);
//	}

//	public Level getNextLevel()
//	{
//		return null;
//	}
}
