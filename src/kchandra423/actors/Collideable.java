package kchandra423.actors;

import java.awt.geom.Area;
import java.util.ArrayList;

public interface Collideable {
    public Area getTransformedArea();
    public boolean intersects(Collideable other);
    public Collideable intersects(ArrayList<Collideable> others);
}
