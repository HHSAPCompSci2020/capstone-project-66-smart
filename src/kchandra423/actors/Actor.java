package kchandra423.actors;

import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;

/**
 * An actor represents any thing that can be drawn and repeatedly acts each frame.
 *
 * @author Kumar Chandra
 */
public abstract class Actor {
    protected boolean active;
    protected KImage image;

    /**
     * Creates an actor with the specified image
     * @param image The specified image
     */
    public Actor(KImage image){
        this.image = image;
        active = true;
    }

    /**
     * Indicates what this actor will do every frame besides drawing
     * @param d
     * @param room
     */
    public abstract void act(DrawingSurface d, Room room);

    public boolean isActive() {
        return active;
    }

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
