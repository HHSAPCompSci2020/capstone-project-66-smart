package sye348.levels;

import java.awt.Rectangle;
import java.util.ArrayList;

import kchandra423.actors.movingActors.enemies.Bat;
import kchandra423.actors.movingActors.enemies.Enemy;
import kchandra423.actors.movingActors.enemies.Goblin;
import kchandra423.actors.movingActors.enemies.Witch;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.graphics.textures.KImage;
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
	
	public LevelThree(String character)
	{
		super(getRooms(character), getTeleporter());
		//player = character;
	}
	
	private static ArrayList<Room> getRooms(String player)
	{
		ArrayList<Room> arr = new ArrayList<Room>();
		
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		enemies.add(new Goblin(5f, 0.5f));
		enemies.add(new Goblin(5f, 0.5f));
		enemies.add(new Goblin(5f, 0.5f));
		enemies.add(new Goblin(5f, 0.5f));
		enemies.add(new Goblin(5f, 0.5f));
		enemies.add(new Bat(5f, 0.5f));
		enemies.add(new Witch(5f, 0.5f));
		
		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
		Obstacle o = new Obstacle(AssetLoader.getImage(AssetLoader.BOX));
		o.getImage().moveTo(600,600);
		obstacles.add(o);
		
		Room r = new Room (Texture.TextureBuilder.getTexture("res/Images/Backgrounds/tiles(manually resized).jpg"), 
				obstacles,
				enemies, 
				getPlayerType(player), 
				new Rectangle(1500, 1000));
		arr.add(r);
		
		
		return arr;
	}
	
	private static Obstacle getTeleporter()
	{
//		KImage image = new KImage(800, 800, false, false, TextureBuilder.getTexture("res/Images/Obstacles/Teleporter.png"));
		KImage image = AssetLoader.getImage(AssetLoader.TELEPORTER);
		image.moveTo(800,800);
		return new Obstacle(image);
	}

	public Level getNextLevel() 
	{
		return null;
	}
}
