package kchandra423.graphics.screens;

import g4p_controls.G4P;
import g4p_controls.GAbstractControl;
import g4p_controls.GCScheme;
import processing.core.PApplet;

/**
 * Represents a drawing surface, which is a type of PApplet
 *
 * @author Kumar Chandra
 * @see processing.core.PApplet
 */
public class DrawingSurface extends PApplet {
//    static float fontMultiplier;
    private static boolean[] keys;
    private boolean alreadySetup = false;
    static int goalFrameRate;
    private static Screen[] screens = new Screen[]{new HomeScreen(), new BattleScreen(), new PerformanceScreen(), new LoadingScreen(), new LoadOutScreen()};
    private static int current = 0;

    /**
     * Creates a new Drawing surface, and initializes the key array
     * Creates each level and the bonus level
     */
    public DrawingSurface() {
        DrawingSurface.goalFrameRate = 60;
        keys = new boolean[128];
    }

    /**
     * Sets the size of the screen, as well as the renderer
     */
    public void settings() {
//        fontMultiplier = ((displayHeight+displayWidth)/2f)/3000;
        size(1920, 1080, P2D);
//        fullScreen();
    }

    /**
     * Sets the framerate, the title of the applet, the icon of the applet, and makes the applet resizable
     */
    public void setup() {
        frameRate(DrawingSurface.goalFrameRate);
        if (!alreadySetup) {
            screenSetup();
        }


    }

    private void screenSetup() {
        alreadySetup = true;
        G4P.setGlobalColorScheme(GCScheme.CYAN_SCHEME);
        G4P.setCursor(ARROW);
        surface.setTitle("Dungeons and Magnums");
        surface.setResizable(false);
        for (Screen s :
                screens) {
            s.setup(this);
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

    @Override
    public void draw() {
        screens[current].draw(this);
//        screens[3].draw(this);
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

    public static int getGoalFrameRate() {
        return goalFrameRate;
    }

    public static void setScreen(Window w) {
        int index = w.index;
        for (int i = 0; i < screens.length; i++) {
            for (GAbstractControl b :
                    screens[i].getUI()) {
                b.setVisible(i == index);
            }
        }
        current = index;

    }

    public void changeFrameRate(int fr) {
        DrawingSurface.goalFrameRate = fr;
        setup();
    }


}










