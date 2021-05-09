package kchandra423.actors.MovingActors;

public enum DamageTypes implements Stat{
    RANGED(0), MAGIC(1), MELEE(2);

    private final int val;

    DamageTypes(int val){
        this.val = val;
    }

    public int getValue() {
        return val;
    }
}
