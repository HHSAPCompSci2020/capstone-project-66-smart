package kchandra423.levels;

import kchandra423.actors.obstacles.Obstacle;
import kchandra423.actors.players.Player;
import kchandra423.actors.players.enemies.Enemy;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;
import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

public class Room {
    private ArrayList<Enemy> enemies;
    private ArrayList<Obstacle> obstacles;
    private final Player player;
    private final Rectangle bounds;
    private final Texture background;

    public Room() {

        background = Texture.TextureBuilder.getTexture("res/Images/Backgrounds/froggy.jpg");
        obstacles = new ArrayList<>();
        Texture obstacle = Texture.TextureBuilder.getTexture("res/Images/Obstacles/Box.png");
        Obstacle o = new Obstacle(new KImage(obstacle, 800, 800));
        obstacles.add(o);
        enemies = new ArrayList<>();
        bounds = new Rectangle(50, 50, 2000, 2000);
        enemies.add(new Enemy(bounds.x + 10, bounds.y + 10));
        enemies.add(new Enemy(bounds.x + 500, bounds.y + 500));


        enemies.add(new Enemy(bounds.x + 10, bounds.y + 500));
        enemies.add(new Enemy(bounds.x + 500, bounds.y + 10));
        player = new Player(new KImage(bounds.x + 50, bounds.y + 50, false, false, Texture.TextureBuilder.getTexture("res/Images/Players/MageIdle.gif")), null);
        background.resize(bounds.width, bounds.height);
    }


    public void draw(DrawingSurface d) {
        background.draw(d, bounds.x, bounds.y);
        for (Enemy e :
                enemies) {
            e.act(d, this);
            e.draw(d);
        }
        for (Obstacle o :
                obstacles) {
            o.act(d, this);
            o.draw(d);
        }
        player.act(d, this);
        player.draw(d);
        for (int i = 0; i < enemies.size(); i++) {
            if (!enemies.get(i).isActive()) {
                enemies.remove(i);
                i--;
            }
        }
    }

    public boolean inBounds(KImage shape) {
        return bounds.contains(shape.getBounds());
    }

    public boolean inBounds(float x, float y) {
        return bounds.contains(x, y);
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public Enemy getClosestEnemy(float x, float y) {
        Enemy min = null;
        float currentLowest = Float.POSITIVE_INFINITY;
        for (Enemy e :
                enemies) {
            float dist = PApplet.dist(x, y, e.getImage().getX(), e.getImage().getY());
            if (dist < currentLowest) {
                min = e;
                currentLowest = dist;
            }
        }
        return min;
    }
}
