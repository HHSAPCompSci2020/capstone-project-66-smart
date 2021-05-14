package kchandra423.graphics;

import kchandra423.actors.movingActors.players.Knight;
import kchandra423.actors.movingActors.players.Mage;
import kchandra423.actors.movingActors.players.Player;
import kchandra423.actors.movingActors.players.Rogue;
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
//	private String classType;
	private int currentLevel = 1;
    
    /**
     * Creates a new Drawing surface, and initializes the key array
     * Creates each level and the bonus level
     */
    public DrawingSurface(Player p) {
        keys = new boolean[128];
        room = new Room();

        levelOne = new LevelOne(p);
		levelTwo = new LevelTwo(p);
		levelThree = new LevelThree(p);
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
     * Draws the current room and everything in it
     */
    public void draw() {
    	
        background(255);
        pushMatrix();
        int halfx = width / 2;
        int halfy = height / 2;
        
        if (currentLevel == 1) {
        	translate(-levelOne.getCurrentRoom().getPlayer().getImage().getX() + halfx - levelOne.getCurrentRoom().getPlayer().getImage().getWidth() / 2.0f, -levelOne.getCurrentRoom().getPlayer().getImage().getY() + halfy - levelOne.getCurrentRoom().getPlayer().getImage().getHeight() / 2.0f);
        	levelOne.getCurrentRoom().draw(this);
        }
        
        else if (currentLevel == 2) {
        	translate(-levelTwo.getCurrentRoom().getPlayer().getImage().getX() + halfx - levelTwo.getCurrentRoom().getPlayer().getImage().getWidth() / 2.0f, -levelTwo.getCurrentRoom().getPlayer().getImage().getY() + halfy - levelTwo.getCurrentRoom().getPlayer().getImage().getHeight() / 2.0f);
        	levelTwo.getCurrentRoom().draw(this);
        }
        
        else if (currentLevel == 3) {
        	translate(-levelThree.getCurrentRoom().getPlayer().getImage().getX() + halfx - levelThree.getCurrentRoom().getPlayer().getImage().getWidth() / 2.0f, -levelThree.getCurrentRoom().getPlayer().getImage().getY() + halfy - levelThree.getCurrentRoom().getPlayer().getImage().getHeight() / 2.0f);
        	levelThree.getCurrentRoom().draw(this);
        }
        
        else if (currentLevel == 4) {
        	translate(-room.getPlayer().getImage().getX() + halfx - room.getPlayer().getImage().getWidth() / 2.0f, -room.getPlayer().getImage().getY() + halfy - room.getPlayer().getImage().getHeight() / 2.0f);
        	room.draw(this);
        }
        
        popMatrix();
        fill(0);
        text((frameRate) + " : fps", width - 100, height - 100);
        
        if (levelOne.isCompleted()) {
//        	levelOne.getNextLevel();
//        	levelOneDone = false;
        	currentLevel = 2;
        }
        
        if (levelTwo.isCompleted()) {
//        	levelTwo.getNextLevel();
//        	levelTwoDone = false;
        	currentLevel = 3;
        }
        
        if (levelThree.isCompleted()) {
//        	levelThree.getNextLevel();
//        	levelTwoDone = false;
        	currentLevel = 4;
        }
        

        
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










