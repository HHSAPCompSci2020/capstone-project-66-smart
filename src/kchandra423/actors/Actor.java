package kchandra423.actors;

import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;

import java.awt.geom.Area;
import java.util.ArrayList;

public abstract class Actor implements Collideable{
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
        }else{
//            System.out.println("tried to draw while inactive");
        }
    }

    @Override
    public Area getTransformedArea() {
        return image.getTransformedArea();
    }

    @Override
    public boolean intersects(Collideable other) {
        Area overLap = getTransformedArea();
        overLap.intersect(other.getTransformedArea());
        return !overLap.isEmpty();
    }

    @Override
    public Collideable intersects(ArrayList<Collideable> others) {
        Area original = getTransformedArea();
        for (Collideable other :
                others) {
            Area copy = (Area) original.clone();
            copy.intersect(other.getTransformedArea());
            if (!copy.isEmpty()) {
                return other;
            }
        }
        return null;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
