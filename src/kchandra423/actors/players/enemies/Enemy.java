package kchandra423.actors.players.enemies;

import kchandra423.actors.MovingActor;
import kchandra423.levels.Room;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;

public class Enemy extends MovingActor
{
    private EnemyAI ai;
    public Enemy(float x, float y){
        this(new KImage(Texture.TextureBuilder.getTexture("res/Images/Enemies/Goblin.gif"),x,y),5,0.7f);
    }
    protected Enemy(KImage image, float maxV, float accel) {
        super(image, maxV, accel);
        ai = new EnemyAI(this);

    }

    @Override
    public void act(DrawingSurface d, Room r) {
        boolean[] decision = ai.makeDecision(r);
        moveX(new boolean[]{decision[0],decision[1]});
        if (!r.inBounds(image)) {
            bounceBackX();
        }
        moveY(new boolean[]{decision[2], decision[3]});
        if (!r.inBounds(image)) {
            bounceBackY();
        }
    }
}
