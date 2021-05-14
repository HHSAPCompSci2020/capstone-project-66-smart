package kchandra423.utility;

import kchandra423.graphics.textures.KImage;
/**
 * Loads all assets needed initially and held statically. 
 * This is to prevent the need to do this throughout runtime, which is extremely time consuming. 
 * Returns clones of these so as to not have to recalculate, but also be safe to use as separate objects.
 * @author Kumar Chandra
 *
 */
public class AssetLoader {
    static {
        long start = System.currentTimeMillis();
        sprites = new KImage[]{
                new KImage("res/Images/Enemies/Attack/Bat.gif"),
                new KImage("res/Images/Enemies/Attack/Goblin.gif"),
                new KImage("res/Images/Enemies/Attack/Witch.gif"),
                new KImage("res/Images/Enemies/Death/Bat.gif"),
                new KImage("res/Images/Enemies/Death/Goblin.gif"),
                new KImage("res/Images/Enemies/Death/Witch.gif"),
                new KImage("res/Images/Enemies/Idle/Bat.gif"),
                new KImage("res/Images/Enemies/Idle/Goblin.gif"),
                new KImage("res/Images/Enemies/Idle/Witch.gif"),
                new KImage("res/Images/Enemies/Moving/Bat.gif"),
                new KImage("res/Images/Enemies/Moving/Goblin.gif"),
                new KImage("res/Images/Enemies/Moving/Witch.gif"),
                new KImage("res/Images/Obstacles/Box.png"),
                new KImage("res/Images/Players/KnightIdle.gif"),
                new KImage("res/Images/Players/MageActive.gif"),
                new KImage("res/Images/Players/MageIdle.gif"),
                new KImage("res/Images/Players/RogueIdle.gif"),
                new KImage("res/Images/Projectiles/Bullet.png"),
                new KImage("res/Images/Projectiles/Hex.gif"),
                new KImage("res/Images/Weapons/MagicStaff.png"),
                new KImage("res/Images/Weapons/Pistol.png"),
                new KImage("res/Images/Weapons/RustySword.png"),
                new KImage("res/Images/Weapons/Shotgun.png"),
                new KImage("res/Images/Weapons/SMG.png"),
                new KImage("res/Images/Weapons/SpellBook.png"),
                new KImage("res/Images/Weapons/Sword.png"),
                new KImage("res/Images/Obstacles/Teleporter.gif")
        };
        long end = System.currentTimeMillis();
        System.out.println("Initializing assets took " + ((end - start) / 1000.0)+" seconds");
    }

    private static final KImage[] sprites;
    public static final int BAT_ATTACK = 0;
    public static final int GOBLIN_ATTACK = 1;
    public static final int WITCH_ATTACK = 2;
    public static final int BAT_DEATH = 3;
    public static final int GOBLIN_DEATH = 4;
    public static final int WITCH_DEATH = 5;
    public static final int BAT_IDLE = 6;
    public static final int GOBLIN_IDLE = 7;
    public static final int WITCH_IDLE = 8;
    public static final int BAT_MOVING = 9;
    public static final int GOBLIN_MOVING = 10;
    public static final int WITCH_MOVING = 11;
    public static final int BOX = 12;
    public static final int KNIGHT_IDLE = 13;
    public static final int MAGE_ACTIVE = 14;
    public static final int MAGE_IDLE = 15;
    public static final int ROGUE_IDLE = 16;
    public static final int BULLET = 17;
    public static final int HEX = 18;
    public static final int MAGIC_STAFF = 19;
    public static final int PISTOL = 20;
    public static final int RUSTY_SWORD = 21;
    public static final int SHOTGUN = 22;
    public static final int SMG = 23;
    public static final int SPELLBOOK = 24;
    public static final int SWORD = 25;
    public static final int TELEPORTER = 26;

    /**
     * Gets a clone of the KImage at the given index. All indexes are specified as a constant. For example, to get a Bat attack sprite,
     *  you would call getImage(BAT_ATTACK);
     * @param index The index of which image you want
     * @return A clone of the original image.
     */
    public static KImage getImage(int index){
        return (KImage) AssetLoader.sprites[index].clone();
    }


}
