package kchandra423.actors.players.enemies;

import kchandra423.actors.MovingActor;
import kchandra423.levels.Room;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;

public class Enemy extends MovingActor
{
    public Enemy(float x, float y){
        this(new KImage(Texture.TextureBuilder.getTexture("res/Images/Enemies/Goblin.gif"),x,y),5,0.7f);
    }
    protected Enemy(KImage image, float maxV, float accel) {
        super(image, maxV, accel);

    }

    @Override
    public void act(DrawingSurface d, Room r) {

    }
}