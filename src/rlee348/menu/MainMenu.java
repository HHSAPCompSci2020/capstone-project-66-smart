package rlee348.menu;

import kchandra423.actors.movingActors.players.Knight;
import kchandra423.actors.movingActors.players.Mage;
import kchandra423.actors.movingActors.players.Rogue;
import kchandra423.utility.AssetLoader;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
//import processing.sound.*;

import javax.swing.JFrame;

import g4p_controls.*;
import jay.jaysound.JayLayer;

import kchandra423.graphics.DrawingSurface;

/**
 * Draws the menu screen for the program, Displays credits 
 * and instructions to the game, has working start button.
 *
 * @author Ryan Lee
 */

public class MainMenu extends PApplet {

//	int rectX, rectY; // Position of square button
//	int circleX, circleY; // Position of circle button
//	int rectSize = 90; // Diameter of rect
//	int circleSize = 93; // Diameter of circle
//	int circleColor, baseColor, rectColor, rectHighlight, circleHighlight, currentColor;
//	boolean rectOver = false;
//	boolean circleOver = false;
	private PImage img;
	private GButton settings;
	private GButton mage;
	private GButton knight;
	private GButton rogue;
	private GLabel lblOut;
	private JayLayer music;
	private GCustomSlider volume;
	private float volumeLevel;
	
	/**
	 * Creates a MainMenu object
	 */
	public MainMenu() {

	}

	/**
	 * Runs on setup, initializes any fields and other objects needed.
	 */
		public void setup() {
		Thread t = new Thread(){
			@Override
			public void run(){
				AssetLoader.getImage(AssetLoader.Sprite.BAT_ATTACK);
			}
		};
		t.start();
		String[] songs = new String[]{"MainMenuTheme.mp3"};
		
		
	//	System.out.println(height);
	//	System.out.println(width);
		
		
		img = loadImage("res/Images/Backgrounds/MenuBackground.png");
		img.resize(1024,768);
		
	//	System.out.println(img.height);
	//	System.out.println(img.width);
		
		background(img);
//		music = new JayLayer("res/","res/",false);
//		music.addSoundEffect("Sounds/MainMenuTheme.mp3");
		//System.out.println(music.getNumberOfSoundEffect());
//		music.playSoundEffect(0);

		music=new JayLayer("res/Sounds/","res/Sounds/",false);
		music.addPlayList();
		music.addSongs(0,songs);
		music.changePlayList(0);
		music.nextSong();



		
	/*	rectColor = color(0);
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
    */
	//	start = new GButton(this, 5*width/11, height - height/5 - height/15, 100, 40, "Start");
	//	settings = new GButton(this, 5*width/11 , height - height/5, 100, 40, "Settings");
		mage = new GButton(this, 5*width/11, height - height/5 - height/15, 100, 40, "mage");
		knight = new GButton(this, 3*width/11, height - height/5 - height/15, 100, 40, "knight");
		rogue = new GButton(this, 7*width/11, height - height/5 - height/15, 100, 40, "rogue");
		
		volume = new GCustomSlider(this, 4*width/12, 5*height/8, 4*width/12, 5*height/8, "blue18px");
		// show          opaque  ticks value limits
		volume.setShowDecor(false, true, true, true);
		volume.setNbrTicks(5);
		volume.setNumberFormat(G4P.DECIMAL, 3);
		volume.setLimits(0, -20, 20);
		/*lblOut = new GLabel(this, 10, 190, 560, 20, "Dungeons and Magnums");
		lblOut.setTextAlign(GAlign.CENTER, null);
		lblOut.setText("Dungeons and Magnums");
		*/
		
	}

	/**
	 * Draws the MainMenu along with the instructions and credits text.
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
		text("Instructions: Use wasd to move your character. \n "
				+ "Use mouse button 2 to fire your weapon. \n "
				+ "The goal of the game is to clear each level by \n "
				+ "killing all the enemies in each room. \n"
				+ "Try to avoid dying, as you will lose the game if you do. \n"
				+ "You can choose your character from 3 classes:\n"
				+ "knight, mage, or rogue.", 2*width/7, 3*height/8);
		text("Volume", 7*width/15, 7*height/8);
		popStyle();
		
		
	}

	/**
	 * Sets the initial size of the PApplet
	 */
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

	/*	if (overCircle(circleX, circleY, circleSize)) {
			circleOver = true;
			rectOver = false;
		} else if (overRect(rectX, rectY, rectSize, rectSize)) {
			rectOver = true;
			circleOver = false;
		} else {
			circleOver = rectOver = false;
		}
     */
	}
	

	/**
	 * Changes the color of the background if the mouse button is used to press the
	 * button.
	 */
	public void mousePressed() {

	/*	if (circleOver) {
			currentColor = circleColor;
		}

		if (rectOver) {
			currentColor = rectColor;
		}
     */
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
	
	
	/**This handles any events that have to do with the buttons.
	 * It takes into account mouse clicks on a specific button. 
	 * The mage button will load you into the game as a mage.
	 * The knight button will load you into the game as a knight.
	 * The rogue button will load you into the game as a rogue.
	 * 
	 * @param button Button used in interaction
	 * @param event the event that happens.
	 */
	public void handleButtonEvents(GButton button, GEvent event) {
		// Create the control window?
		if (button == mage && event == GEvent.CLICKED) {
			music.stopSong();
			
			DrawingSurface drawing = new DrawingSurface( new Mage(700,500), volumeLevel, 60, PConstants.P2D);
			PApplet.runSketch(new String[]{""}, drawing);
			PSurfaceAWT surf = (PSurfaceAWT) this.getSurface();
			PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
			JFrame window = (JFrame)canvas.getFrame();
			window.setVisible(false);
		} 
		
		else if (button == knight && event == GEvent.CLICKED) {
			
			music.stopSong();
			
			DrawingSurface drawing = new DrawingSurface(new Knight(700,500), volumeLevel, 60, PConstants.P2D);
			PApplet.runSketch(new String[]{""}, drawing);
			PSurfaceAWT surf = (PSurfaceAWT) this.getSurface();
			PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
			JFrame window = (JFrame)canvas.getFrame();
			window.setVisible(false);
		}
		
		else if (button == rogue && event == GEvent.CLICKED) {
			
			music.stopSong();
			
			DrawingSurface drawing = new DrawingSurface(new Rogue(700,500), volumeLevel, 60, PConstants.P2D);
			PApplet.runSketch(new String[]{""}, drawing);

			PSurfaceAWT surf = (PSurfaceAWT) this.getSurface();
			PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
			JFrame window = (JFrame)canvas.getFrame();
			window.setVisible(false);
		}
		
		else if (button == settings && event == GEvent.CLICKED) {
			
			music.stopSong();

		}
	}
	
	public void handleSliderEvents(GValueControl slider, GEvent event) { 
		
		music.stopSong();
		volumeLevel = slider.getValueF();
		music.setVolume(volumeLevel);
		music.nextSong();
		
	}
}
