package sye348.levels;

import kchandra423.graphics.DrawingSurface;
import kchandra423.levels.Room;

import java.util.ArrayList;


/**
 * @author Spencer Ye
 * @version 1.1.1
 * Last Revised: 5/13/2021
 */
public abstract class Level {

    private ArrayList<Room> rooms;

//    private Room currentRoom;

    private int roomNumber;

//    private Obstacle teleporter;

//    private boolean completed;


    /**
     * Creates a Level given a array of rooms
     *
     * @param rooms The rooms defined for a level
     */
    public Level(ArrayList<Room> rooms) {
        this.rooms = rooms;
        roomNumber = 0;
//        currentRoom = rooms.get(0);
//        this.teleporter = teleporter;
    }

    /**
     * Draws the level
     *
     * @param window The DrawingSurface that is drawn on
     */
    public void draw(DrawingSurface window) {
        getCurrentRoom().draw(window);
//        if (allEnemiesDead()) teleporter.draw(window);
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
//        currentRoom = rooms.get(roomNumber++);
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
//        currentRoom = rooms.get(--roomNumber);
    }

    /**
     * Gets the room number that the player is currently in
     *
     * @return The room number that the player is currently in
     */
    public int getRoomNumber() {
        return roomNumber + 1;
    }

//    /**
//     * Gets the room object that the player is in
//     *
//     * @return The room that the player is in
//     */
//    public Room getRoom() {
//        return currentRoom;
//    }

    /**
     * Checks if the level has been completed
     *
     * @return If the level has been completed
     */
    public boolean isCompleted() {
        return roomNumber >= rooms.size()-1 && getCurrentRoom().isCompleted();
    }

//    private boolean allEnemiesDead() {
//
//        return currentRoom.getEnemies().size() == 0;
//    }

//    /**
//     * Tells the level to display the teleporter that moves to the next level
//     */
//    public void displayTeleporter() {
//        completed = true;
//    }

//    /**
//     * Checks if the player is touching the teleporter
//     *
//     * @return If the player is touching the teleporter
//     */
//    public boolean playerisOnTeleporter() {
//        return currentRoom.getPlayer().intersects(teleporter);
//    }

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


//    /**
//     * Gets a Player object based on the character type, which is the parameter
//     *
//     * @param characterName A string that represents a character type's name
//     * @return A player object of the given character type
//     */
//    protected static Player getPlayerType(String characterName) {
//        if ("mage".equalsIgnoreCase(characterName)) {
//            return new Player(new KImage(TextureBuilder.getTexture("res/Images/Players/MageIdle.gif")), new KImage(TextureBuilder.getTexture("res/Images/Players/MageActive.gif")));
//        }
//        if ("knight".equalsIgnoreCase(characterName)) {
//            return new Player(new KImage(TextureBuilder.getTexture("res/Images/Players/KnightIdle.gif")), new KImage(TextureBuilder.getTexture("res/Images/Players/KnightIdle.gif")));
//        }
//        if ("rogue".equalsIgnoreCase(characterName)) {
//            return new Player(new KImage(TextureBuilder.getTexture("res/Images/Players/RogueIdle.gif")), new KImage(TextureBuilder.getTexture("res/Images/Players/RogueIdle.gif")));
//        }
//        return null;
//    }
}
