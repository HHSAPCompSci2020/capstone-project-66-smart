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

/**
 * A room contains all the enemies and obstacles and boundaries that a player is currently in
 * @see kchandra423.actors.Actor
 * @author Kumar Chandra
 */
public class Room {
    private ArrayList<Enemy> enemies;
    private ArrayList<Obstacle> obstacles;
    private final Player player;
    private final Rectangle bounds;
    private final Texture background;

    /**
     * Currently just creates a room with some random default enemies and obstacles
     */
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

    /**
     * Draws everything in this room onto the given drawing surface. Also cause the act method of all actors
     * @param d The drawing surface that actors will be drawn onto
     */
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

    /**
     * Returns whether or not a KImage is within the boundaries of this room.
     * Somewhat expensive call, although not as much as intersection
     * @param shape The Kimage which will have its boundaries checked
     * @return Returns false if the KImage is not within the bounds of this room, using the rectangles class' definition of insides. Returns true otherwise
     */
    public boolean inBounds(KImage shape) {
        return bounds.contains(shape.getBounds());
    }

    /**
     * Returns whether or not a point is within the boundaries of this room
     * Inexpensive call
     * @param x The x value of the point
     * @param y The y value of the point
     * @return Returns whether or not the x,y point is within the bounds of this room using the rectangle class' definition of insideness
     */
    public boolean inBounds(float x, float y) {
        return bounds.contains(x, y);
    }

    /**
     * Returns the player for this room
     * @return this room's player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns all the enemies within this room
     * @return All enemies contained by this room
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Returns all obstacles within this room
     * @return All obstaacles contained in the room
     */
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * Returns the closest enemy from a given point
     * @param x The x coordinate of the given point
     * @param y The y coordinate of the given point
     * @return The closest enemy from the given point
     */
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
