package kchandra423.actors.players.enemies;

import kchandra423.actors.MovingActor;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.levels.Room;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;

import java.util.ArrayList;

/**
 * Represents a generic enemy type that attempts to fight the player.
 *
 * @see kchandra423.actors.Actor
 * @see kchandra423.actors.MovingActor
 * @see EnemyAI
 * @author Kumar Chandra
 */
public class Enemy extends MovingActor
{
    private final EnemyAI ai;
    public Enemy(float x, float y){
        this(new KImage(Texture.TextureBuilder.getTexture("res/Images/Enemies/Goblin.gif"),x,y),5,0.7f);
    }
    protected Enemy(KImage image, float maxV, float accel) {
        super(image, maxV, accel);
        ai = new EnemyAI(this);

    }

    @Override
    public void act(DrawingSurface d, Room r) {
        float decision = ai.makeMovementDecision(r);
//        moveX(new boolean[]{decision[0],decision[1]});
        ArrayList<Obstacle> obstacles = r.getObstacles();
        moveX(decision);
        if (!r.inBounds(image)) {
            bounceBackX();
        }
        for (Obstacle o :
                obstacles) {
            if (intersects(o)) {
                bounceBackX();
            }
        }
//        moveY(new boolean[]{decision[2], decision[3]});
        moveY(decision);
        if (!r.inBounds(image)) {
            bounceBackY();
        }
        for (Obstacle o :
                obstacles) {
            if (intersects(o)) {
                bounceBackY();
            }
        }
    }
}
