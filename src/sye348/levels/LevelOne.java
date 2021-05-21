package sye348.levels;

import kchandra423.actors.movingActors.enemies.Enemy;
import kchandra423.actors.movingActors.enemies.Goblin;
import kchandra423.actors.movingActors.players.Player;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.graphics.textures.Texture;
import kchandra423.levels.Room;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Spencer Ye
 * @version 1.2.0
 * Last Revised: 5/20/2021
 */
public class LevelOne extends Level {



    public LevelOne(Player p) {
        super(getRooms(p));
    }

    private static ArrayList<Room> getRooms(Player player) {
        ArrayList<Room> arr = new ArrayList<>();
        Rectangle bounds = new Rectangle(1500, 1000);
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(new Goblin(getRandXCoord(bounds, 200), getRandYCoord(bounds, 200)));


        Room r = new Room(Texture.TextureBuilder.getTexture("res/Images/Backgrounds/tiles(manually resized).jpg"),
                new ArrayList<Obstacle>(),
                enemies,
                player,
                bounds);
        arr.add(r);


        return arr;
    }


    @Override
    public Level getNextLevel() {
        return new LevelTwo(getCurrentRoom().getPlayer());
    }
}
