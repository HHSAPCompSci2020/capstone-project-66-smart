package kchandra423.graphics.textures;

import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

/**
 * The KImage class represent an Image that supports several transformations as well as intersection between other KImages with these transformations.
 * The texture class is used to represent the graphical aspect of the image. An area object is used to represent the "hitbox" of this KImage.
 * Upon being created, an Area will be generated from the texture if not already done so. ****THIS IS EXTREMELY SLOW****.
 * Area is accurate at a pixel level, and because of this, please refrain from calling it excessively. Areas are generated by taking all
 * nontransparent pixels and creating an area out of it. Areas objects can also be disjointed, so even non transparent
 * shapes separated from a "main" shape will be part of the hitbox. Although Texture supports gifs, this class does not entirely support gifs
 * Although things will render without issue, the area will be based on the first frame of the gif, not any others.
 * All transformations transform both the area and image. Because of the slow time to transform an area, areas will only be transformed
 * if they are needed (for intersection or getting bounds). It should be noted that rotations from the angle happen
 * about the corner of the image. The same is true for reflections, which reflect about the vertical line that intersects
 * the x coordinate of the current location, which is also in the top left. The final transformation, "reversed" simply states
 * whether or not when the object is reflected, does its angle reverse to look in the "right" direction, by drawing the angle at Pi- its actual value.
 * However a restriction of this is that unexpected behavior when something is reflected, but not reversed, and it is rotated by a non zero amount.
 *
 * @author Kumar Chandra
 */
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

    /**
     * Creates a new KImage with the specified texture.
     * Position starts at 0,0, with reflected and reversed being false
     *
     * @param t A texture that represents what this KImage looks like
     */
    public KImage(Texture t) {
        this(0, 0, false, false, t);
    }

    /**
     * Creates a new Kimage with the specified texture and location
     * Reflected and reversed are set to false
     *
     * @param t The Texture used by this KImage
     * @param x The initial x position
     * @param y The initial y position
     */
    public KImage(Texture t, float x, float y) {
        this(x, y, false, false, t);
    }

    /**
     * Creates a new Kimage with all the specified fields
     *
     * @param x         The initial x position
     * @param y         The initial y position
     * @param reflected Whether or not the image is reflected
     * @param reversed  Whether or not the image is reversed
     * @param t         The Texture used by this KImage
     */
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

    /**
     * Creates a new Kimage with all the specified fields. However the area is also specified,
     * which can be useful if you are creating multiple KImages and already know what your area will be.
     * This will make it so you will only need to load the area once (which can be very slow), and pass in that area
     * to all new KImages. Another scenario in which you may want to specify the area is if the hitbox of this KImage
     * doesnt necessarily correspond to the way it looks, so you want to be able to set it yourself.
     *
     * @param x         The initial x position
     * @param y         The initial y position
     * @param reflected Whether or not the image is reflected
     * @param reversed  Whether or not the image is reversed
     * @param t         The Texture used by this KImage
     * @param area      The specified area to use as a hitbox
     */
    public KImage(float x, float y, boolean reflected, boolean reversed, Texture t, Area area) {
        this.x = x;
        this.reflected = reflected;
        this.reversed = reversed;
        this.y = y;
        angle = 0;
        image = t;
        this.area = area;
        update();
        upToDate = true;
    }

    /**
     * Loads an area from a Texture, and returns an area that is composed of all of the pixels that are not transparent.
     * THIS CALL IS EXTREMELY SLOW. ATTEMPT TO USE IT SPARINGLY.
     *
     * @param texture The texture you want to create an area out of
     * @return An area that fits onto all nontransparent pixels of the texture.
     */
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


    /**
     * Draws the Texture with all necessary transformations specified by other parameters.
     *
     * @param p The Papplet to be drawn onto.
     */
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


    /**
     * Translates the KImage by the specified amounts
     *
     * @param delx The amount to translate in the x direction
     * @param dely The amount to translate in the y direction
     * @post This image's area is no longer up to date
     */
    public void translate(float delx, float dely) {
        if (delx != 0 || dely != 0) {
            x += delx;
            y += dely;
            upToDate = false;
        }
    }

    /**
     * Rotates the shape by the specified amount. (Added onto current angle)
     *
     * @param angle The angle in radians to be rotated by
     * @post This image's area is no longer up to date
     */
    public void rotate(float angle) {
        if (angle != 0) {
            this.angle += angle;
            this.angle %= Math.PI * 2;
            upToDate = false;
        }
    }

    /**
     * Sets whether or not the KImage is reflected about the vertical line that passes through its x coordinate.
     * Reflections happen about the top left coordinate of the image.
     *
     * @param reflected Whether or not this image is reflected
     * @post This image's area is no longer up to date
     */
    public void setReflected(boolean reflected) {
        if (this.reflected != reflected) {
            this.reflected = reflected;
            upToDate = false;
        }
    }

    /**
     * Returns whether or not the shape is being reflected
     *
     * @return Whether or not the shape is reflected
     */
    public boolean isReflected() {
        return reflected;
    }

    /**
     * Sets the current angle to the specified angle
     *
     * @param angle The specified angle
     * @post This image's area is no longer up to date
     */
    public void setAngle(float angle) {
        if (this.angle != angle) {
            this.angle = angle;
            this.angle %= Math.PI * 2;
            upToDate = false;
        }
    }

    /**
     * Moves the image to the specified x and y coordinates.
     *
     * @param x The new x position
     * @param y The new y position
     * @post This image's area is no longer up to date
     */
    public void moveTo(float x, float y) {
        if (this.x != x || this.y != y) {
            this.x = x;
            this.y = y;
            upToDate = false;
        }
    }

    /**
     * Return the current x position
     *
     * @return The current x position
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the current y position
     *
     * @return The current y position
     */
    public float getY() {
        return y;
    }

    /**
     * Returns the current angle
     *
     * @return the current angle
     */
    public float getAngle() {
        return angle;
    }

    /**
     * Returns the width of the current image
     *
     * @return The width of the texture
     */
    public int getWidth() {
        return image.getWidth();
    }

    /**
     * Returns the current height of the image
     *
     * @return The height of the texture
     */
    public int getHeight() {
        return image.getHeight();
    }

//    public Texture getTexture() {
//        return image;
//    }

    /**
     * Sets if this image is reversed
     *
     * @param reversed Whether or not the imagee is reversed
     * @post This image's area is no longer up to date
     */
    public void setReversed(boolean reversed) {
        if (this.reversed != reversed) {
            this.reversed = reversed;
            upToDate = false;
        }
    }

    /**
     * Returns whether or not this image is reversed
     *
     * @return Whether or not this image is reversed
     */
    public boolean isReversed() {
        return reversed;
    }


    /**
     * Returns whether or not this shape's area intersects the other shapes area.
     *
     * @param other The other KImage
     * @return Whether or not the shapes intersect
     * @post Both image's areas will be up to date
     */
    public boolean intersects(KImage other) {
        if (!upToDate) {
            update();
        }
        Area myArea = (Area) mostRecentArea.clone();
        if (!other.upToDate) {
            other.update();
        }
        Area otherArea = other.mostRecentArea;
        myArea.intersect(otherArea);
        return !myArea.isEmpty();
    }

    /**
     * Returns a rectangle representing the boundaries of this area as the most tight rectangle possible
     *
     * @return The most tighlty bounding rectangle possible
     * @post This image's area will be up to date
     */
    public Rectangle getBounds() {
        if (!upToDate) {
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

