package kchandra423.graphics.textures;

import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;

public class KImage {
    private final Texture image;
    private final Area area;
    private Area mostRecentArea;
    private float x;
    private float y;
    private float angle;
    private boolean reflected;
    private boolean reversed;
    private boolean upToDate;

    public KImage(Texture t) {
        this(0, 0, false, false, t);
    }

    public KImage(Texture t, float x, float y) {
        this(x, y, false, false, t);
    }

    public KImage(float x, float y, boolean reflected, boolean reversed, Texture t) {
        this.x = x;
        this.reflected = reflected;
        this.reversed = reversed;
        this.y = y;
        angle = 0;
        image = t;
        area = KImage.loadArea(image);
        update();
        upToDate = true;
    }

    public KImage(float x, float y, boolean reflected, boolean reversed, Texture t, Area area) {
        this.x = x;
        this.reflected = reflected;
        this.reversed = reversed;
        this.y = y;
        angle = 0;
        image = t;
        this.area = area;
    }

    public static Area loadArea(Texture texture) {
        Area area = new Area();
        PImage img = texture.getImage();
        for (int x = 0; x < img.width; x++) {
            for (int y = 0; y < img.height; y++) {
                if (img.pixels[y * img.width + x] != 0) {
                    area.add(new Area(new Rectangle(x, y, img.pixelDensity, img.pixelDensity)));
                }
            }

        }
        return area;

    }

    public boolean isUpToDate() {
        return upToDate;
    }

//    public Area getMostRecentArea() {
//        return mostRecentArea;
//    }

    public void draw(PApplet p) {
        if (!reflected) {
            p.pushMatrix();
            p.translate(x, y);
            p.rotate(angle);
            image.draw(p, 0, 0);
            p.popMatrix();
        } else {
            p.pushMatrix();
            p.scale(-1, 1);

            if (reversed) {
                p.translate(-x, y);
                p.rotate((float) (Math.PI - angle));
            } else {
                p.translate(-(x + image.getWidth()), y);
                p.rotate(angle);
            }
            image.draw(p, 0, 0);
            p.popMatrix();
        }
    }


    public void translate(float delx, float dely) {
        if (delx != 0 || dely != 0) {
            x += delx;
            y += dely;
            upToDate = false;
        }
    }

    public void rotate(float angle) {
        if (angle != 0) {
            this.angle += angle;
            upToDate = false;
        }
    }

    public void setReflected(boolean reflected) {
        if (this.reflected != reflected) {
            this.reflected = reflected;
            upToDate = false;
        }
    }


    public boolean isReflected() {
        return reflected;
    }

    public void setAngle(float angle) {
        if (this.angle!= angle) {
            this.angle = angle;
            upToDate = false;
        }
    }

    public void moveTo(float x, float y) {
        if(this.x!= x || this.y!=y) {
            this.x = x;
            this.y = y;
            upToDate = false;
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getAngle() {
        return angle;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public Texture getTexture() {
        return image;
    }

    public void setReversed(boolean reversed) {
        if(this.reversed!=reversed) {
            this.reversed = reversed;
            upToDate = false;
        }
    }

    public boolean isReversed() {
        return reversed;
    }

//    public boolean intersects(ArrayList<KImage> images) {
//        Area orginial = getTransformedArea();
//        for (KImage kImage :
//                images) {
//            Area copy = (Area) orginial.clone();
//            copy.intersect(kImage.getTransformedArea());
//            if (!copy.isEmpty()) {
//                return true;
//            }
//        }
//        return false;
//    }

    public boolean intersects(KImage other) {
        if(!upToDate){
            update();
        }
        Area myArea = (Area) mostRecentArea.clone();
        if(!other.upToDate){
            other.update();
        }
        Area otherArea = other.mostRecentArea;
        myArea.intersect(otherArea);
        return !myArea.isEmpty();
    }


    public Rectangle getBounds() {
        if(!upToDate){
            update();
        }
        return mostRecentArea.getBounds();
    }

    private void update() {
        upToDate = true;
        if (!reflected) {
            AffineTransform transform = new AffineTransform();
            transform.translate(x, y);
            transform.rotate(angle);
            mostRecentArea = area.createTransformedArea(transform);
        } else {
            AffineTransform transform = new AffineTransform();
            transform.scale(-1, 1);
            if (reversed) {
                transform.translate(-x, y);
                transform.rotate(Math.PI - angle);
            } else {
                transform.translate(-(x + image.getWidth()), y);
                transform.rotate(angle);
            }
            mostRecentArea = area.createTransformedArea(transform);
        }
    }
}

