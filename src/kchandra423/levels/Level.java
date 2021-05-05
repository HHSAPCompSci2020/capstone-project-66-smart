package kchandra423.levels;

public class Level {
    private Room[] rooms;
    int floorNum;
    int levelNum;
    public Level(int floornum, int levelNum){
        rooms=getRooms(floornum,levelNum);
        this.floorNum=floornum;
        this.levelNum=levelNum;
    }
    private static Room[] getRooms(int floornum, int levelnum){
        return null;
    }
}
