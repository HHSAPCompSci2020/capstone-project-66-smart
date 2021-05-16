package sye348.levels;

import kchandra423.actors.MovingActors.enemies.Enemy;
import kchandra423.actors.MovingActors.enemies.Goblin;
import kchandra423.actors.MovingActors.enemies.Witch;
import kchandra423.actors.MovingActors.players.Player;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.graphics.textures.Texture;
import kchandra423.levels.Room;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Spencer Ye
 * @version 1.1.0
 * Last Revised: 5/13/2021
 */
public class LevelTwo extends Level {

//    private String player;

    public LevelTwo(Player player) {
        super(getRooms(player));
//        player = character;
    }

    private static ArrayList<Room> getRooms(Player player) {
        ArrayList<Room> arr = new ArrayList<Room>();
        Rectangle bounds = new Rectangle(1500, 1000);

        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(new Goblin((float) (Math.random() * (bounds.width - 200) + bounds.x), (float) (Math.random() * (bounds.height - 200) + bounds.y)));
        enemies.add(new Goblin((float) (Math.random() * (bounds.width - 200) + bounds.x), (float) (Math.random() * (bounds.height - 200) + bounds.y)));
        enemies.add(new Goblin((float) (Math.random() * (bounds.width - 200) + bounds.x), (float) (Math.random() * (bounds.height - 200) + bounds.y)));
        enemies.add(new Goblin((float) (Math.random() * (bounds.width - 200) + bounds.x), (float) (Math.random() * (bounds.height - 200) + bounds.y)));
        enemies.add(new Goblin((float) (Math.random() * (bounds.width - 200) + bounds.x), (float) (Math.random() * (bounds.height - 200) + bounds.y)));

        enemies.add(new Witch((float) (Math.random() * (bounds.width - 200) + bounds.x), (float) (Math.random() * (bounds.height - 200) + bounds.y)));

        Room r = new Room(Texture.TextureBuilder.getTexture("res/Images/Backgrounds/tiles(manually resized).jpg"),
                new ArrayList<Obstacle>(),
                enemies,
                player,
                bounds);
        arr.add(r);


        return arr;
    }

//	private static Obstacle getTeleporter()
//	{
////		KImage image = new KImage(100, 100, false, false, TextureBuilder.getTexture("res/Images/Obstacles/Teleporter.png"));
//		KImage image = AssetLoader.getImage(AssetLoader.TELEPORTER);
//		image.moveTo(200,200);
//		return new Obstacle(image);
//	}

//	public Level getNextLevel()
//	{
//		return new LevelThree(player);
//	}
}
