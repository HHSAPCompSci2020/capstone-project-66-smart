package kchandra423.graphics;

import kchandra423.levels.Room;
import processing.core.PApplet;

/**
 * Represents a drawing surface, which is a type of PApplet
 *
 * @author Kumar Chandra
 */
public class DrawingSurface extends PApplet {
    private static boolean[] keys;
    private Room room;

    /**
     * Creates a new Drawing surface, and initializes the key array
     */
    public DrawingSurface() {
        keys = new boolean[128];
        room = new Room();

    }

    /**
     * Sets the size of the screen, as well as the renderer
     */
    public void settings() {
        size(1500, 1000, P2D);
        fullScreen();
    }

    /**
     * Sets the framerate, the title of the applet, the icon of the applet, and makes the applet resizable
     */
    public void setup() {
        frameRate(60);
        surface.setTitle("Dungeons and Magnums");
        surface.setResizable(true);
    }


    /**
     * Draws the room and everything in it
     */
    public void draw() {
        background(255);
        pushMatrix();
        int halfx = width / 2;
        int halfy = height / 2;
        translate(-room.getPlayer().getImage().getX() + halfx - room.getPlayer().getImage().getWidth() / 2.0f, -room.getPlayer().getImage().getY() + halfy - room.getPlayer().getImage().getHeight() / 2.0f);
        room.draw(this);
        popMatrix();
        fill(0);
        text((frameRate) + " : fps", width - 100, height - 100);
    }

    /**
     * Sets the key that was pressed in the key array to true
     */
    public void keyPressed() {
        if (keyCode < 128) {
            keys[keyCode] = true;
        }


    }

    /**
     * Sets the key that was pressed in the key array to false
     */
    public void keyReleased() {

        if (keyCode < 128) {
            keys[keyCode] = false;
        }


    }

    /**
     * Returns the values of the first 128 keys
     *
     * @return The array of all valid keys pressed
     */
    public static boolean[] getKeys() {
        return keys;
    }

    /**
     * Returns whether or not a certain key is pressed
     *
     * @param keyCode The keycode of the given key to be checked
     * @return Whether or not that key was pressed
     * @pre keyCode must be less than 128
     */
    public static boolean getKey(int keyCode) {
        return keys[keyCode];

    }

}










