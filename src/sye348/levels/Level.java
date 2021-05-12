package sye348.levels;

import java.util.ArrayList;

/**
 * 
 * @author Spencer Ye
 * @version 0.1.0
 * Last Revised: 5/11/2021
 */
public class Level 
{
	
	private ArrayList<Room> rooms;
	
	private Room currentRoom;
	
	private int roomNumber;
    
    public Level(ArrayList<Room> rooms)
    {
    	rooms = room;
    	roomNumber = 1;
    	currentRoom = rooms.get(0);
    }
    
    public void goForwardRoom()
    {
    	if (rooms.size() == roomNumber)
    	{
    		System.out.println("NO MORE ROOMS TO GO FORWARD");
    		return;
    	}
    	currentRoom = rooms.get(roomNumber++);
    }
    
    public void goBackwardRoom()
    {
    	if (roomNumber == 0)
    	{
    		System.out.println("NO MORE ROOMS TO GO BACK");
    		return;
    	}
    	currentRoom = rooms.get(--roomNumber);
    }
    
    public int getRoomNumber()
    {
    	return roomNumber;
    }
}
