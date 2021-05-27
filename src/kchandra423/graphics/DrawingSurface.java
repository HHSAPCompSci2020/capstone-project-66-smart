package kchandra423.graphics;

import javax.swing.*;

import java.io.*;
import java.util.Scanner;

import javax.print.DocFlavor.URL;
import javax.sound.sampled.*;
import jay.jaysound.JayLayer;
import kchandra423.actors.movingActors.players.Mage;
import kchandra423.actors.movingActors.players.Player;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PConstants;
import sye348.levels.Level;
import sye348.levels.LevelOne;
import sye348.levels.LevelThree;
import sye348.levels.LevelTwo;

/**
 * Represents a drawing surface, which is a type of PApplet
 *
 * @author Kumar Chandra
 * @see processing.core.PApplet
 */
public class DrawingSurface extends PApplet {
    private static boolean[] keys;
    private Level level;
    private final HUD hud;
    public static int frameRate;
    private final String renderer;

    /**
     * Creates a new Drawing surface, and initializes the key array
     * Creates each level and the bonus level
     */
    public DrawingSurface(Player p) {
        DrawingSurface.frameRate = 60;
        this.renderer = PConstants.P2D;
        keys = new boolean[128];
        level = new LevelOne(p);
        hud = new HUD();
    }

    /**
     * Sets the size of the screen, as well as the renderer
     */
    public void settings() {
        size(displayWidth/2, displayHeight/2, renderer);
//        fullScreen();
    }

    /**
     * Sets the framerate, the title of the applet, the icon of the applet, and makes the applet resizable
     */
    public void setup() {
        frameRate(DrawingSurface.frameRate);
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
        if (level == null) {
            background(0);
            textSize(50);
            fill(255);
            text("Congrats \n You won!", width/3, height/2);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            surface.stopThread();
                            System.exit(0);
                        }
                    },
                    5000
            );
            return;
        }
        if (level.isCompleted()) {
            level = level.getNextLevel();
        }

        translate(-level.getCurrentRoom().getPlayer().getImage().getX() + halfx - level.getCurrentRoom().getPlayer().getImage().getTexture().getWidth() / 2.0f, -level.getCurrentRoom().getPlayer().getImage().getY() + halfy - level.getCurrentRoom().getPlayer().getImage().getTexture().getHeight() / 2.0f);
        level.draw(this);
        popMatrix();
        hud.draw(this, level);
        fill(0);
        text(super.frameRate + " : fps", width - 100, height - 100);

        if (!level.getCurrentRoom().getPlayer().isActive()) {

        	background(0);
        	textSize(50);
        	fill(255);
        	text("Game Over \n You Lost!", width/3, height/2);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            surface.stopThread();
                            System.exit(0);
                        }
                    },
                    5000
            );
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










