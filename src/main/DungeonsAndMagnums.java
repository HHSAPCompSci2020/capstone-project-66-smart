package main;

import processing.core.PApplet;

import kchandra423.graphics.screens.DrawingSurface;

/**
 * Main method that tests the gui
 * @author Ryan Lee
 *
 */
public class DungeonsAndMagnums {
	/**
	 * Runs the program
	 * @param args The arguments to be passed
	 */
	public static void main(String[] args) {
		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);

	}

}
