package sye348.levels;

import kchandra423.graphics.DrawingSurface;
import kchandra423.levels.Room;

import java.awt.Rectangle;
import java.util.ArrayList;


/**
 * @author Spencer Ye
 * @version 1.2.0
 * Last Revised: 5/20/2021
 */
public abstract class Level {

    private ArrayList<Room> rooms;

    private int roomNumber;



    /**
     * Creates a Level given a array of rooms
     *
     * @param rooms The rooms defined for a level
     */
    public Level(ArrayList<Room> rooms) 
    {
        this.rooms = rooms;
        roomNumber = 0;
    }

    /**
     * Draws the level
     *
     * @param window The DrawingSurface that is drawn on
     */
    public void draw(DrawingSurface window) {
        getCurrentRoom().draw(window);
    }

    /**
     * Advances the room that the player is in
     */
    public void goForwardRoom() {
        if (rooms.size() == roomNumber) {
            System.out.println("NO MORE ROOMS TO GO FORWARD");
            return;
        }
        roomNumber++;
    }

    /**
     * Moves the room that the player is in backwards
     */
    public void goBackwardRoom() {
        if (roomNumber == 0) {
            System.out.println("NO MORE ROOMS TO GO BACK");
            return;
        }
        roomNumber--;
    }

    /**
     * Gets the room number that the player is currently in
     *
     * @return The room number that the player is currently in
     */
    public int getRoomNumber() {
        return roomNumber + 1;
    }



    /**
     * Checks if the level has been completed
     *
     * @return If the level has been completed
     */
    public boolean isCompleted() {
        return roomNumber >= rooms.size()-1 && getCurrentRoom().isCompleted();
    }


    /**
     * Gives the current room that the level is on
     * @return The current room the level is on
     */
    public Room getCurrentRoom() {
        return rooms.get(roomNumber);
    }

    /**
     * Gives the next level after the current level
     * @return The next level to play
     */
    public abstract Level getNextLevel();
    
    /**
     * Gets a random x-coordinate of a random location given a boundary and the padding wanted
     * @param bounds The boundaries where the x-coordinate should correlate with
     * @param margin The absolute distance from the edge of the boundaries where the coordinate given
     * @return The x-coordinate of a random location
     */
    protected static float getRandXCoord(Rectangle bounds, int margin)
    {
    	return (float) (Math.random() * (bounds.width - margin) + bounds.x);
    }
    
    /**
     * Gets a random y-coordinate of a random location given a boundary and the padding wanted
     * @param bounds The boundaries where the y-coordinate should correlate with
     * @param margin The absolute distance from the edge of the boundaries where the coordinate given
     * @return The y-coordinate of a random location
     */
    protected static float getRandYCoord (Rectangle bounds, int margin)
    {
    	return (float) (Math.random() * (bounds.height - margin) + bounds.y);
    }
}



