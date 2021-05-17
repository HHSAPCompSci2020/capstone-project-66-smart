package kchandra423.graphics;

import kchandra423.actors.MovingActors.players.Rogue;
import processing.core.PApplet;
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
		DrawingSurface drawing = new DrawingSurface(new Rogue(700,500),0);
		PApplet.runSketch(new String[]{""}, drawing);
	}
	
	
	
	
	
	
	
}
