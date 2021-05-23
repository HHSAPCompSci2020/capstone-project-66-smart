package sye348.levels;

import java.awt.Rectangle;
import java.util.ArrayList;

import kchandra423.actors.movingActors.enemies.Bat;
import kchandra423.actors.movingActors.enemies.Enemy;
import kchandra423.actors.movingActors.enemies.Goblin;
import kchandra423.actors.movingActors.enemies.Witch;
import kchandra423.actors.movingActors.players.Player;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.graphics.textures.Texture;
import kchandra423.levels.Room;

/**
 * 
 * @author Spencer Ye
 * @version 1.0.0
 * Last Revised: 5/22/2021
 */
public class LevelSix extends Level
{
	
	public LevelSix (Player player)
	{
		super(getRooms(player));
	}
	
	private static ArrayList<Room> getRooms(Player player)
	{
		ArrayList<Room> arr = new ArrayList<Room>();
		
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		Rectangle bounds = new Rectangle(1500, 1500);

        for (int i = 0; i < 0; i++)
        	enemies.add(new Goblin(getRandXCoord(bounds, 200), getRandYCoord(bounds, 200)));
        for (int i = 0; i < 15; i++)
        	enemies.add(new Bat(getRandXCoord(bounds, 200), getRandYCoord(bounds, 200)));
        for (int i = 0; i < 0; i++)
        	enemies.add(new Witch(getRandXCoord(bounds, 200), getRandYCoord(bounds, 200)));
		
		
        
		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	
		
		Room r = new Room (Texture.TextureBuilder.getTexture("res/Images/Backgrounds/tiles(manually resized).jpg"), 
				obstacles,
				enemies, 
				player,
				bounds);
		arr.add(r);
		
		
		return arr;
	}

	public Level getNextLevel()
	{
		return new LevelSeven(getCurrentRoom().getPlayer());
	}
}
