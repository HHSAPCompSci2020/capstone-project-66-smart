Dungeons and Magnums
Authors: Kumar Chandra, Spencer Ye, Ryan Lee
Revision: 5/7/2021


Introduction: 
Our program creates a graphical-based game where the user controls a character around a room and uses weapons to defeat computer-controlled enemies and other players to gain as many points as they can.[a][b][c]
The problem that we solve is the continued problem of boredom. A lot of people are still stuck at home, with little entertainment, or they have gotten bored of the entertainment they previously had, and they can use this program to get entertainment. 
The story of our game is that, when we were sitting in a call trying to decide what our AP Computer Science capstone project, one of our team members was playing Soul Knight, so we decided to create our ideal version of the game, which is how our game came to be.[d]
The rules of our game is that you must stay in the confines of the room, there are game mechanics that prevent you from leaving. Similarly, most other rules of the game are enforced through code, such as: players cannot move onto obstacles[e]. The goal of our game is to gather as many points as possible.[f]
While this game is targeted more at a younger audience, anyone who wants a new form of entertainment, especially in a game form, will want to use our program.
The primary features of our game includes a menu that allows users to reach the pages they want. The pages they can reach include a settings page, which allows them to change program settings such as volume, and a game page, where the actual game will be started, played, and ended.


Instructions:[g]
Throughout the actual game, the player will use the typical movement keys, “W”, “A”, “S’, and “D” to respectively move up, right, down, and left. 
During the game, the player may click anywhere on the screen to fire the weapon in their hand. Players will use their mouse cursor to aim their weapon, simply by moving it to the direction that they want to aim at. 
There will be a single start menu that has two buttons, a “settings” button and a few other buttons, representing the different player types that the player can choose. 
To start a game, the user can simply pick a character by clicking the button correlating to that player.
Once in the game, players can use any controls in any order they wish to control their character.


Features List (THE ONLY SECTION THAT CANNOT CHANGE LATER):
Must-have Features:[h]
* GUI to start the game, and change settings. Users should be able to choose their character and other aspects of the game, such as the music or volume.
* Menu has a basic instruction manual, which just explains how to play the game. Should explain how to fight against enemies, how to progress through levels, Until they eventually finish or die.[i]
* Playable game - Users should be able to move, fire weapons[j], and collide with objects[k]. Players should also be able to move through and navigate each level, and progress onto the next one
* Multiple levels - Multiple unique levels, each with different obstacle and enemy layouts
* 2 dimensional graphics - The entire game should be shown through images and all graphics should have color. 




Want-to-have Features:
* Consistently hold 60 fps - Throughout the entire runtime, the program should be able to hold at least 60 fps, to ensure that movement is smooth and consistent. Any optimizations to the code to increase performance should be sought after.
*  Enemy AI - Enemy AI will make enemies behave in a somewhat intelligent manner (move towards player, circle player, fire towards player, etc), which will make the game able to be actually fun. 
* Store player information on a database - Players stats, such as level[l], username, password, xp, etc. Players will be able to log on from any device and log into their account using their username and password, and all relevant stats will be saved.
* Players can save game progress - Games that are in progress will also be stored on the database or in a local file. Players will be able to restart a level from a certain point with these saves. 
* Multiple characters - Players should be able to choose from multiple characters, all with different stats and appearances
* Full screen support - Users should be able to toggle in and out of a full screen mode with no changes to gameplay, or significant differences in performance. 
* Music + sounds effects - Some sort of music should play, and sound effects should be played on certain actions. For example, some background music would play the entire game, but when you take damage or are below a certain health, a sound effect would play on top of the music.
Stretch Features:
* Fancy neural net for enemy ai - This feature is not for gameplay purposes, but rather more for a learning experience. The AI should be able to play against the person, and should be able to do basic activities to attempt to defeat the player, even if it is weird/ too bad/ too good.
* Multiple difficulty settings - There should be multiple difficulty levels, (easy, medium, hard) which provide different levels of challenge to the player. These changes could be shifts in the ai’s behavior, and / or changing the stats and multipliers for enemies. 
* Online multiplayer - Users would be able to play online with up to 3 different people. They would play co - op against the computer, and player and enemy stats would be modified to ensure there is still a challenge. 
* Character abilities - Characters would have unique abilities, such as shooting a projectile, or add a buff multiplier to themselves or their teammates. 
* Local multiplayer for up to three players at a time on the same network. Similar to online multiplayer, except it would be faster and everyone would just need to be on the same network. 




Class List:

* Entity[n]
   * An entity has a sprite and can be drawn
* Actor
   * An actor is an entity that can move and has physics that bounce it back when it collides with another object, extends Entity class
* Enemy
   * Extends Actor
* Goblin, Witch, Bat
   * Extends Enemy, each represents a enemy with their own images
* Player
   * Extends Actor, the player is the user and can interact with the game environment with abilities, weapons, and different classes
* Knight
   * Extends Player, has class specific abilities, knight is more tanky than the other classes 
* Mage
   * Extends Player, has class specific abilities, mage is less tanky than the other classes, but can deal more damage
* Rouge
   * Extends Player, has class specific abilities, rouge is more tanky than mage, but less tanky that the knight, Rouge has better movement than the other classes
* Obstacle
   * Extends Entity and implements Collideable, blocks the Player and other Actors from movement and bounces them back when they collide
* Level
   * An abstract class that represents a level, it helps provides helper methods and structure the other levels
* LevelOne, LevelTwo, LevelThree
   * Extends Level, Classes that represents levels with their own layout of obstacles and unique enemies
* Room
   * The room contains the Player, Obstacles, enemies, and projectiles, it is where the game is played
* DrawingSurface
   * Extends PApplet, this is used to draw all the entities and take care of graphical processing
* Circle
   * A circle with center coords and diameter, extends shape, used as hitbox
* Line
   * A line with 2 coordinate pairs, extends shape, used as hitbox
* Rectangle
   * A rectangle made up of 4 lines, extends shape, used as hitbox
* Sprite
   * Has  a shape and texture, and can be moved around and collided with other sprites.
* Texture
   * Like a PImage, but also supports GIfs
* RangedWeapon
   * Has a sprite and a projectile texture, used by Player and Enemy as a weapon
* Sword
   * Used by Player and Enemy as a weapon, has a set swing duration and length
* Projectile
   * Projectile used by RangedWeapon and SemiAutoGun[o][p], has its own sprite, and determines if it comes into contact with an enemy or player, and whether they are killed or not
* Music Player
   * Plays certain tracks of music
* LevelRunner
   * A class that runs the program with levels
* MainMenu
   * A class that represents the main menu of the program
* DataBaseCommunicator
   * Talks to the database to get and send information
* UserData
   * A class that represents all the information for a user

Credits[q]:
* Kumar
   * Create all classes related to graphics
   * Create all classes related to the physics engine and enemy interactions
   * Created some of the art assets for the project
* Spencer
   * Created the enemies
   * Created levels and the level system.
   * Set up database for saving settings and progress
* Ryan
   * Create the GUI
   * Create the music and sounds effect system
   * Created and did the UML diagram
* Give credit to all outside resources used. This includes downloaded images or sounds, external java libraries, parent/tutor/student coding help, etc.]
   * Processing
      * We used the Java processing library to help us display all of our graphics quickly
   * JaysEasierSound
      * Taken from Shelby’s coding demos in order to use sounds.
   * Processing Widget Libraries
      * Taken from Shelby’s coding demos.
   * Elthen 
      * Patreon at https://www.patreon.com/elthen
      * Used his Sprites for the bat enemy
