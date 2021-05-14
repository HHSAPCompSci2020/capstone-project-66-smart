package sye348.levels;

import kchandra423.actors.movingActors.Player;
import kchandra423.actors.obstacles.Obstacle;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture.TextureBuilder;
import kchandra423.levels.Room;

import java.util.ArrayList;


/**
 * @author Spencer Ye
 * @version 1.1.1
 * Last Revised: 5/13/2021
 */
public abstract class Level {

    private ArrayList<Room> rooms;

    private Room currentRoom;

    private int roomNumber;

    private Obstacle teleporter;

    private boolean completed;


    /**
     * Creates a Level given a array of rooms
     *
     * @param rooms The rooms defined for a level
     */
    public Level(ArrayList<Room> rooms, Obstacle teleporter) {
        this.rooms = rooms;
        roomNumber = 1;
        currentRoom = rooms.get(0);
        this.teleporter = teleporter;
    }

    /**
     * Draws the level
     *
     * @param window The DrawingSurface that is drawn on
     */
    public void draw(DrawingSurface window) {
        currentRoom.draw(window);
        if (completed) teleporter.draw(window);
    }

    /**
     * Advances the room that the player is in
     */
    public void goForwardRoom() {
        if (rooms.size() == roomNumber) {
            System.out.println("NO MORE ROOMS TO GO FORWARD");
            return;
        }
        currentRoom = rooms.get(roomNumber++);
    }

    /**
     * Moves the room that the player is in backwards
     */
    public void goBackwardRoom() {
        if (roomNumber == 0) {
            System.out.println("NO MORE ROOMS TO GO BACK");
            return;
        }
        currentRoom = rooms.get(--roomNumber);
    }

    /**
     * Gets the room number that the player is currently in
     *
     * @return The room number that the player is currently in
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Gets the room object that the player is in
     *
     * @return The room that the player is in
     */
    public Room getRoom() {
        return currentRoom;
    }

    /**
     * Checks if the level has been completed
     *
     * @return If the level has been completed
     */
    public boolean isCompleted() {
        return (roomNumber == rooms.size()) && allEnemiesDead();
    }

    private boolean allEnemiesDead() {

        return currentRoom.getEnemies().size() == 0;
    }

    /**
     * Tells the level to display the teleporter that moves to the next level
     */
    public void displayTeleporter() {
        completed = true;
    }

    /**
     * Checks if the player is touching the teleporter
     *
     * @return If the player is touching the teleporter
     */
    public boolean playerisOnTeleporter() {
        return currentRoom.getPlayer().intersects(teleporter);
    }

    public abstract Level getNextLevel();


    /**
     * Gets a Player object based on the character type, which is the parameter
     *
     * @param characterName A string that represents a character type's name
     * @return A player object of the given character type
     */
    protected static Player getPlayerType(String characterName) {
        if ("mage".equalsIgnoreCase(characterName)) {
            return new Player(new KImage(TextureBuilder.getTexture("res/Images/Players/MageIdle.gif")), new KImage(TextureBuilder.getTexture("res/Images/Players/MageActive.gif")));
        }
        if ("knight".equalsIgnoreCase(characterName)) {
            return new Player(new KImage(TextureBuilder.getTexture("res/Images/Players/KnightIdle.gif")), new KImage(TextureBuilder.getTexture("res/Images/Players/KnightIdle.gif")));
        }
        if ("rogue".equalsIgnoreCase(characterName)) {
            return new Player(new KImage(TextureBuilder.getTexture("res/Images/Players/RogueIdle.gif")), new KImage(TextureBuilder.getTexture("res/Images/Players/RogueIdle.gif")));
        }
        return null;
    }
}
