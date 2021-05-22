package kchandra423.graphics;

import javax.swing.JFrame;

import jay.jaysound.JayLayer;
import kchandra423.actors.movingActors.players.Mage;
import kchandra423.actors.movingActors.players.Player;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PConstants;
import rlee348.menu.MainMenu;
import sye348.levels.Level;
import sye348.levels.LevelOne;

/**
 * Represents a drawing surface, which is a type of PApplet
 *
 * @author Kumar Chandra
 * @see processing.core.PApplet
 */
public class DrawingSurface extends PApplet {
    private static boolean[] keys;
    private Level level;
    private JayLayer sounds;
    private JayLayer effects;
    private final HUD hud;
    private float volume, fps;
    private String[] soundEffects;
    private int frameRate;
    private String renderer;

    /**
     * Creates a new Drawing surface, and initializes the key array
     * Creates each level and the bonus level
     */
    public DrawingSurface(Player p, float volume, int frameRate, String renderer) {
        this.frameRate = frameRate;
        this.renderer = renderer;
        keys = new boolean[128];
        this.volume = volume;
        level = new LevelOne(p);
        hud = new HUD(p);
    }

    /**
     * Sets the size of the screen, as well as the renderer
     */
    public void settings() {
        size(1500, 1000, renderer);
//        fullScreen();
    }

    /**
     * Sets the framerate, the title of the applet, the icon of the applet, and makes the applet resizable
     */
    public void setup() {
        frameRate(frameRate);
        surface.setTitle("Dungeons and Magnums");
        surface.setResizable(true);

        soundEffects = new String[]{"SwordAttack.mp3"};

        String[] songs = new String[]{"LevelTheme.mp3"};
        sounds = new JayLayer("res/Sounds/", "res/Sounds/", false);
        sounds.addPlayList();
        sounds.addSongs(0, songs);
        sounds.addSoundEffects(soundEffects);
        sounds.changePlayList(0);
        sounds.setVolume(volume);
        sounds.nextSong();

        effects = new JayLayer("res/Sounds/", "res/Sounds/", false);
        effects.addPlayList();
        effects.addSongs(0, soundEffects);
        effects.changePlayList(0);
        effects.setVolume(volume);

       
        
        
    }


    /**
     * Draws the current room and everything in it
     */
    public void draw() {

        background(255);
        pushMatrix();
        int halfx = width / 2;
        int halfy = height / 2;

        if (level.isCompleted()) {
            level = level.getNextLevel();
        }
        if (level == null) {
            this.surface.stopThread();
            System.exit(0);
        }
        translate(-level.getCurrentRoom().getPlayer().getImage().getX() + halfx - level.getCurrentRoom().getPlayer().getImage().getWidth() / 2.0f, -level.getCurrentRoom().getPlayer().getImage().getY() + halfy - level.getCurrentRoom().getPlayer().getImage().getHeight() / 2.0f);
        level.draw(this);
        popMatrix();
        hud.draw(this);
        fill(0);
        text((frameRate) + " : fps", width - 100, height - 100);
        
        if (hud.getPlayer().getHealth() < 0) {
	
        	background(0);
        	textSize(100);
        	fill(255);
        	text("gg go next \n time to exit program kiddo", width/3, height/2);
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

    public void mousePressed() {
        effects.nextSong();
    }

    public void mouseReleased() {
        effects.stopSong();
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










