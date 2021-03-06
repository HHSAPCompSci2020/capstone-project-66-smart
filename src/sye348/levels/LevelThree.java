package sye348.levels;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import kchandra423.actors.movingActors.enemies.Bat;
import kchandra423.actors.movingActors.enemies.Enemy;
import kchandra423.actors.movingActors.enemies.Goblin;
import kchandra423.actors.movingActors.enemies.Witch;
import kchandra423.actors.movingActors.players.Player;
import kchandra423.actors.obstacles.Crate;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.graphics.textures.Texture;
import kchandra423.levels.Room;
import kchandra423.utility.AssetLoader;

/**
 * Level 3
 * @author Spencer Ye
 * @version 1.2.1
 * Last Revised: 5/22/2021
 */
public class LevelThree extends Level
{
	
	public LevelThree(Player player)
	{
		super(getRooms(player));
	}
	
	private static ArrayList<Room> getRooms(Player player)
	{
		ArrayList<Room> arr = new ArrayList<>();
		Texture t = Texture.TextureBuilder.getTexture("res/Images/Backgrounds/Lava.png");
		arr.add(Level.getRoom(20, 0, 0, 0, 0, new ArrayList<Obstacle>(Arrays.asList(new Crate(900, 900))), player,t));
		arr.add(Level.getRoom(4, 4, 7, 4, 1, new ArrayList<Obstacle>(Arrays.asList(new Crate(400, 1000))), player,t));
		arr.add(Level.getRoom(6, 5, 1, 2, 2, new ArrayList<Obstacle>(Arrays.asList(new Crate(1000, 1000), new Crate(400, 400))), player,t));
		arr.add(Level.getRoom(5, 5, 10, 5, 5, new ArrayList<Obstacle>(Arrays.asList(new Crate(300, 300), new Crate(1000,300), new Crate(1000, 1000))), player,t));
		return arr;
	}
	@Override
	public Level getNextLevel()
	{
		return null;
	}
	@Override
	public String toString() {
		return "3 - " + getRoomNumber();
	}
}
