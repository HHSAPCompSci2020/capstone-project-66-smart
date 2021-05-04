package kchandra423.actors;

import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;

import java.awt.geom.Area;
import java.util.ArrayList;

public abstract class Actor {
    protected boolean active;
    protected KImage image;
    public Actor(KImage image){
        this.image = image;
        active = true;
    }
    public abstract void act(DrawingSurface d, Room room);

    public KImage getImage() {
        return image;
    }

    public void draw(DrawingSurface d){
        if(active) {
            image.draw(d);
        }
    }


    public boolean intersects(Actor other) {
        return image.intersects(other.image);
    }



    public void setActive(boolean active) {
        this.active = active;
    }
}
