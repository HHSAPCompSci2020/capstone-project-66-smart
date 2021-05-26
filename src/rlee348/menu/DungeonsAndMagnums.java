package rlee348.menu;

import kchandra423.actors.movingActors.players.Mage;
import kchandra423.actors.movingActors.players.Rogue;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

import javax.swing.*;

import kchandra423.graphics.DrawingSurface;

import java.awt.*;

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
		DrawingSurface drawing = new DrawingSurface(new Rogue(700,700));
		PApplet.runSketch(new String[]{""}, drawing);

	}

}
