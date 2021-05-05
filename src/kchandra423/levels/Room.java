package kchandra423.levels;

import kchandra423.actors.players.enemies.Enemy;
import kchandra423.actors.players.Player;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;

import java.awt.*;
import java.util.ArrayList;

public class Room {
    private ArrayList<Enemy> enemies;
    private ArrayList obstacles;
    private final Player player;
    private final Rectangle bounds;
    private final Texture background;

    public Room() {

        background = Texture.TextureBuilder.getTexture("res/Images/Backgrounds/froggy.jpg");
        enemies = new ArrayList<>();
        bounds = new Rectangle(50, 50, 1000, 1000);
        enemies.add(new Enemy(bounds.x + 10, bounds.y + 10));
        enemies.add(new Enemy(bounds.x + 500, bounds.y + 500));


        enemies.add(new Enemy(bounds.x + 10, bounds.y + 500));
        enemies.add(new Enemy(bounds.x + 500, bounds.y + 10));
        player = new Player(new KImage(bounds.x + 50, bounds.y + 50, false, false, Texture.TextureBuilder.getTexture("res/Images/Players/MageIdle.gif")), null);
        background.resize(1000, 1000);
    }


    public void draw(DrawingSurface d) {
//        d.stroke(0);
        background.draw(d, bounds.x, bounds.y);
        for (Enemy e :
                enemies) {
            e.draw(d);
        }
        player.act(d, this);
        player.draw(d);
        for (int i = 0; i < enemies.size(); i++) {
            if(!enemies.get(i).isActive()){
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
}
