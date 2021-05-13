package kchandra423.graphics;

import kchandra423.levels.Room;
import processing.core.PApplet;
import sye348.levels.LevelOne;
import sye348.levels.LevelThree;
import sye348.levels.LevelTwo;

/**
 * Represents a drawing surface, which is a type of PApplet
 *
 * @see processing.core.PApplet
 * @author Kumar Chandra
 */
public class DrawingSurface extends PApplet {
    private static boolean[] keys;
    private Room room;
    private LevelOne levelOne;
    private LevelTwo levelTwo;
    private LevelThree levelThree;
	private String classType;
    
    /**
     * Creates a new Drawing surface, and initializes the key array
     */
    public DrawingSurface(String classType) {
        keys = new boolean[128];
        room = new Room();
        levelOne = new LevelOne(classType);
		levelTwo = new LevelTwo(classType);
		levelThree = new LevelThree(classType);
    }

    /**
     * Sets the size of the screen, as well as the renderer
     */
    public void settings() {
        size(1500, 1000, P2D);
//        fullScreen();
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
        translate(-levelOne.getRoom().getPlayer().getImage().getX() + halfx - levelOne.getRoom().getPlayer().getImage().getWidth() / 2.0f, -levelOne.getRoom().getPlayer().getImage().getY() + halfy - levelOne.getRoom().getPlayer().getImage().getHeight() / 2.0f);
        levelOne.getRoom().draw(this);
        popMatrix();
        fill(0);
        text((frameRate) + " : fps", width - 100, height - 100);
        
        //if (levelOne.isCompleted()) {
        	levelOne.displayTeleporter(this);
       // }
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










