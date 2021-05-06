package rlee348.menu;

import processing.core.PApplet;

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

	/**
	 * Creates a Gui object
	 */
	public Gui() {

	}

	/**
	 * Runs on setup, initializes any fields needed.
	 */

	public void setup() {

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

	}

	/**
	 * Draws the Gui.
	 */
	public void draw() {

		update(mouseX, mouseY);
		background(currentColor);

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

}
