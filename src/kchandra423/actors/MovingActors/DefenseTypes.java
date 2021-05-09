package kchandra423.actors.MovingActors;

public enum DefenseTypes implements Stat{
    PHYSDEF(3), MAGDEF(4), RANGEDEF(5);

    private final int val;

    DefenseTypes(int val) {
        this.val = val;
    }

    public int getValue() {
        return val;
    }
}
