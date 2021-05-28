package kchandra423.graphics;

import kchandra423.actors.movingActors.players.Rogue;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Represents a drawing surface, which is a type of PApplet
 *
 * @author Kumar Chandra
 * @see processing.core.PApplet
 */
public class DrawingSurface extends PApplet {
    private static boolean[] keys;
    public static int goalFrameRate;
    private final String renderer;
    private BattleScreen battle;

    /**
     * Creates a new Drawing surface, and initializes the key array
     * Creates each level and the bonus level
     */
    public DrawingSurface() {
        DrawingSurface.goalFrameRate = 60;
        this.renderer = PConstants.P2D;
        keys = new boolean[128];
        battle = new BattleScreen(new Rogue( 700,700));
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
        frameRate(DrawingSurface.goalFrameRate);
        surface.setTitle("Dungeons and Magnums");
        surface.setResizable(true);



    }




    /**
     * Sets the key that was pressed in the key array to true
     */
    public void keyPressed() {
        if (keyCode < 128) {
            keys[keyCode] = true;
        }


    }
    @Override
    public void draw(){
        battle.draw(this);
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










