package kchandra423.graphics;

import kchandra423.actors.movingActors.players.Rogue;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Has the main method
 * @author Kumar Chandra
 *
 */
public class DungeonsAndMagnums {


	/**
	 * Starts the program
	 * @param args The arguments passed in when called initially 
	 */
	public static void main(String args[]) {
		DrawingSurface drawing = new DrawingSurface(new Rogue(700,500),100, 60, PConstants.P2D);
		PApplet.runSketch(new String[]{""}, drawing);
	}
	
	
	
	
	
	
	
}
