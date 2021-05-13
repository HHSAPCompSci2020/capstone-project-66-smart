package sye348.levels;

import java.awt.Rectangle;
import java.util.ArrayList;

import kchandra423.actors.MovingActors.enemies.Enemy;
import kchandra423.actors.MovingActors.enemies.Goblin;
import kchandra423.actors.MovingActors.enemies.Witch;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;
import kchandra423.graphics.textures.Texture.TextureBuilder;
import kchandra423.levels.Room;

/**
 * 
 * @author Spencer Ye
 * @version 1.1.0
 * Last Revised: 5/13/2021
 */
public class LevelTwo extends Level
{
	
	private String player;
	
	public LevelTwo(String character)
	{
		super(getRooms(character), getTeleporter());
		player = character;
	}
	
	private static ArrayList<Room> getRooms(String player)
	{
		ArrayList<Room> arr = new ArrayList<Room>();
		
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		enemies.add(new Goblin(0.5f, 0.5f));
		enemies.add(new Goblin(0.5f, 0.5f));
		enemies.add(new Goblin(0.5f, 0.5f));
		enemies.add(new Goblin(0.5f, 0.5f));
		enemies.add(new Goblin(0.5f, 0.5f));
		enemies.add(new Witch(0.5f, 0.5f));
		
		Room r = new Room (Texture.TextureBuilder.getTexture("res/Images/Backgrounds/tiles(manually resized).jpg"), 
				new ArrayList<Obstacle>(),
				enemies, 
				getPlayerType(player), 
				new Rectangle(1500, 1000));
		arr.add(r);
		
		
		return arr;
	}
	
	private static Obstacle getTeleporter()
	{
		KImage image = new KImage(TextureBuilder.getTexture("res/Images/Obstacles/teleporter.png"));
		
		image.moveTo(100, 100);
		
		return new Obstacle(image);
	}

	public Level getNextLevel() 
	{
		return new LevelThree(player);
	}
}
