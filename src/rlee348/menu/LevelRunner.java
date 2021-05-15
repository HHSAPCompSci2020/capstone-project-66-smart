//package rlee348.menu;
//
//import kchandra423.actors.movingActors.players.Rogue;
//import processing.awt.PSurfaceAWT;
//import processing.core.PApplet;
//import processing.core.PImage;
//import sye348.levels.LevelOne;
//import sye348.levels.LevelThree;
//import sye348.levels.LevelTwo;
//
//import java.awt.Dimension;
//
//import javax.swing.JFrame;
//
//import g4p_controls.*;
//import kchandra423.graphics.DrawingSurface;
//import kchandra423.levels.Room;
//
//
///**
// * Draws the character select box
// * you can choose from 3 different classes
// *
// * @author Ryan Lee
// */
//public class LevelRunner extends DrawingSurface {
//
//	private String classType;
//    private static boolean[] keys;
//    private LevelOne levelOne;
//    private LevelTwo levelTwo;
//    private LevelThree levelThree;
//    private DrawingSurface drawer;
//    private Room room;
//
//	/**
//	 * Creates a LevelRunner object
//	 */
//
//    public LevelRunner() {
//    	super(new Rogue(700,500));
//    	keys = new boolean[128];
//		room = new Room();
//    }
//
//	public LevelRunner(String classType) {
//
//		super(new Rogue(700,500));
//		this.classType = classType;
//		keys = new boolean[128];
//		room = new Room();
//
//	}
//
//	/**
//	 * Runs on setup, initializes any fields needed.
//	 */
//	public void setup() {
//
//		frameRate(60);
//        surface.setTitle("Dungeons and Magnums");
//        surface.setResizable(true);
//
//	}
//
//
//	/**
//	 * Draws the MainMenu.
//	 */
//	public void draw() {
//
//
//        background(255);
//        pushMatrix();
//        int halfx = width / 2;
//        int halfy = height / 2;
//        translate(-room.getPlayer().getImage().getX() + halfx - room.getPlayer().getImage().getWidth() / 2.0f, -room.getPlayer().getImage().getY() + halfy - room.getPlayer().getImage().getHeight() / 2.0f);
//        room.draw(this);
//        popMatrix();
//        fill(0);
//        text((frameRate) + " : fps", width - 100, height - 100);
//
//	}
//
//
//	public void settings() {
//        size(1500, 1000, P2D);
//	}
//
//    /**
//     * Sets the key that was pressed in the key array to true
//     */
//    public void keyPressed() {
//        if (keyCode < 128) {
//            keys[keyCode] = true;
//        }
//
//
//    }
//
//    /**
//     * Sets the key that was pressed in the key array to false
//     */
//    public void keyReleased() {
//
//        if (keyCode < 128) {
//            keys[keyCode] = false;
//        }
//
//
//    }
//
//    /**
//     * Returns the values of the first 128 keys
//     *
//     * @return The array of all valid keys pressed
//     */
//    public static boolean[] getKeys() {
//        return keys;
//    }
//
//    /**
//     * Returns whether or not a certain key is pressed
//     *
//     * @param keyCode The keycode of the given key to be checked
//     * @return Whether or not that key was pressed
//     * @pre keyCode must be less than 128
//     */
//    public static boolean getKey(int keyCode) {
//        return keys[keyCode];
//
//    }
//
//
//
//}
