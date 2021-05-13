package sye348.levels;

import kchandra423.graphics.DrawingSurface;
import kchandra423.levels.Room;

import java.util.ArrayList;

/**
 * 
 * @author Spencer Ye
 * @version 1.0.0
 * Last Revised: 5/12/2021
 */
public class Level 
{
	
	private ArrayList<Room> rooms;
	
	private Room currentRoom;
	
	private int roomNumber;
    
	/**
	 * Creates a Level given a array of rooms
	 * @param rooms The rooms defined for a level
	 */
    public Level(ArrayList<Room> rooms)
    {
    	this.rooms = rooms;
    	roomNumber = 1;
    	currentRoom = rooms.get(0);
    }
    
    /**
     * Draws the level
     * @param window The DrawingSurface that is drawn on
     */
    public void draw(DrawingSurface window)
    {
    	currentRoom.draw(window);
    }
    
    /**
     * Advances the room that the player is in
     */
    public void goForwardRoom()
    {
    	if (rooms.size() == roomNumber)
    	{
    		System.out.println("NO MORE ROOMS TO GO FORWARD");
    		return;
    	}
    	currentRoom = rooms.get(roomNumber++);
    }
    
    /**
     * Moves the room that the player is in backwards
     */
    public void goBackwardRoom()
    {
    	if (roomNumber == 0)
    	{
    		System.out.println("NO MORE ROOMS TO GO BACK");
    		return;
    	}
    	currentRoom = rooms.get(--roomNumber);
    }
    
    /**
     * Gets the room number that the player is currently in
     * @return The room number that the player is currently in
     */
    public int getRoomNumber()
    {
    	return roomNumber;
    }
}
