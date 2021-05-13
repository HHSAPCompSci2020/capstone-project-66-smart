package rlee348.menu;

import kchandra423.utility.AssetLoader;
import processing.core.PApplet;
import processing.core.PImage;
import g4p_controls.*;

/**
 * Draws the menu screen for the program, along with some of the other menu
 * features like settings, and potentially pause scren.
 *
 * @author Ryan Lee
 */

public class Gui extends PApplet {

	int rectX, rectY; // Position of square button
	int circleX, circleY; // Position of circle button
	int rectSize = 90; // Diameter of rect
	int circleSize = 93; // Diameter of circle
	int circleColor, baseColor, rectColor, rectHighlight, circleHighlight, currentColor;
	boolean rectOver = false;
	boolean circleOver = false;
	PImage img;
	GButton start;
	GButton settings;
	GLabel lblOut;
	
	/**
	 * Creates a Gui object
	 */
	public Gui() {

	}

	/**
	 * Runs on setup, initializes any fields needed.
	 */

	public void setup() {
		
		
		AssetLoader.getImage(0);
		
		img = loadImage("res/Images/Backgrounds/MenuBackground.png");
		img.resize(1024,768);

		
		background(img);
		
		rectColor = color(0);
		rectHighlight = color(51);
		circleColor = color(255);
		circleHighlight = color(204);
		baseColor = color(102);
		currentColor = baseColor;
		circleX = width / 2 + circleSize / 2 + 10;
		circleY = height / 2;
		rectX = width / 2 - rectSize - 10;
		rectY = height / 2 - rectSize / 2;
		ellipseMode(CENTER);

		start = new GButton(this, 5*width/11, height - height/5 - height/15, 100, 40, "Start");
		settings = new GButton(this, 5*width/11 , height - height/5, 100, 40, "Settings");
		
		/*lblOut = new GLabel(this, 10, 190, 560, 20, "Dungeons and Magnums");
		lblOut.setTextAlign(GAlign.CENTER, null);
		lblOut.setText("Dungeons and Magnums");
		*/
		
	}

	/**
	 * Draws the Gui.
	 */
	public void draw() {

//		img.resize(width,height);
		background(img);
		
		
	/*	update(mouseX, mouseY);
	//	background(currentColor);

		if (rectOver) {
			fill(rectHighlight);
		} 
		else {
			fill(rectColor);
		}
		stroke(255);
		rect(rectX, rectY, rectSize, rectSize);

		if (circleOver) {
			fill(circleHighlight);
		} 
		else {
			fill(circleColor);
		}
		stroke(0);
		ellipse(circleX, circleY, circleSize, circleSize);
   
   	*/
		pushStyle();
		
		fill(255);
		textSize(60);
		text("Dungeons and Magnums", width/6, height/5);
		textSize(20);
		text("By Kumar Chandra, Spencer Ye, and Ryan Lee", 2*width/7, 2*height/7);
		
		popStyle();
		
   
	}

	public void settings() {
		size(1024,768);
	}
	
	/**
	 * Executes the code using the specific point of the mouse, changes the color of
	 * the button depending on the mouse position.
	 * 
	 * @param x x coordinate of the mouse.
	 * @param y y coordinate of the mouse.
	 */
	public void update(int x, int y) {

		if (overCircle(circleX, circleY, circleSize)) {
			circleOver = true;
			rectOver = false;
		} else if (overRect(rectX, rectY, rectSize, rectSize)) {
			rectOver = true;
			circleOver = false;
		} else {
			circleOver = rectOver = false;
		}

	}

	/**
	 * Changes the color of the background if the mouse button is used to press the
	 * button.
	 */
	public void mousePressed() {

		if (circleOver) {
			currentColor = circleColor;
		}

		if (rectOver) {
			currentColor = rectColor;
		}

	}

	/**
	 * 
	 * @param x  x coordinate of the top left of rectangle.
	 * @param y  y coordinate of the top left of rectangle.
	 * @param width width of the rectangle.
	 * @param height height of the rectangle.
	 * @return true or false depending on if the given mouse coordinates are within the are of the rectangle.
	 */
	boolean overRect(int x, int y, int width, int height) {

		if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
			return true;
		}

		else {
			return false;
		}
	}

	/**
	 * 
	 * @param x x coordinate of the center of the circle.
	 * @param y y coordinate of the center of the circle.
	 * @param diameter diameter of the circle.
	 * @return true or false depending on if the coordinates of the mouse are within the area of the circle
	 */
	boolean overCircle(int x, int y, int diameter) {

		float disX = x - mouseX;
		float disY = y - mouseY;
		if (sqrt(sq(disX) + sq(disY)) < diameter / 2) {
			return true;
		} 
		else {
			return false;
		}
	}
	public void handleButtonEvents(GButton button, GEvent event) {
		// Create the control window?
		if (button == start && event == GEvent.CLICKED) {
//			lblOut.setText("Not Ready Yet");
			System.out.println("deez");
		} 
		
		else if (button == settings && event == GEvent.CLICKED) {
		//	lblOut.setText("Not Ready Yet");
			

		}

	}
}
