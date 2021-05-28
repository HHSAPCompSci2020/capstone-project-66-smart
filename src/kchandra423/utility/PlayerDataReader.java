package kchandra423.utility;

import kchandra423.actors.weapons.Weapon;
import kchandra423.actors.weapons.guns.MagicStaff;
import kchandra423.actors.weapons.guns.SMG;
import kchandra423.actors.weapons.guns.Shotgun;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerDataReader {
    static {
        int coins1;
        ArrayList<Weapon> knightWeapons1 = new ArrayList<>();
        //add defaults
        ArrayList<Weapon> mageWeapons1 = new ArrayList<>();
        mageWeapons1.add(new MagicStaff());
        ArrayList<Weapon> rogueWeapons1 = new ArrayList<>();
        try {
            Scanner dataReader = new Scanner(new BufferedReader(new FileReader("res/Data/PlayerStats")));
            coins1 = Integer.parseInt(dataReader.nextLine());
            String raw = dataReader.nextLine();
            if (raw.contains("SMG")) {
                knightWeapons1.add(new SMG());
            }
            raw = dataReader.nextLine();
//            if(raw.contains("MagicStaff")){
//                mageWeapons1.add(new MagicStaff());
//            }
            raw = dataReader.nextLine();
            if (raw.contains("SMG")) {
                rogueWeapons1.add(new SMG());
            }
            if (raw.contains("Shotgun")) {
                rogueWeapons1.add(new Shotgun());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Could not properly load player data");
            coins1 = 0;
        }
        mageWeapons = mageWeapons1;
        knightWeapons = knightWeapons1;
        rogueWeapons = rogueWeapons1;
        coins = coins1;
    }

    private static final int coins;
    private static final ArrayList<Weapon> knightWeapons;
    private static final ArrayList<Weapon> mageWeapons;
    private static final ArrayList<Weapon> rogueWeapons;

    public static int getCoins() {
        return coins;
    }

    public static ArrayList<Weapon> getKnightWeapons() {
        return knightWeapons;
    }

    public static ArrayList<Weapon> getMageWeapons() {
        return mageWeapons;
    }

    public static ArrayList<Weapon> getRogueWeapons() {
        return rogueWeapons;
    }
}
