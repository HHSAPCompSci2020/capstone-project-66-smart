package kchandra423.actors;

import kchandra423.actors.MovingActors.DamageTypes;

public class Damage {
    private final int amount;
    private final float[] stats;
    private final DamageTypes type;
    public Damage(int amount, float[] stats, DamageTypes type) {
        this.amount = amount;
        this.stats = stats;
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }
    public float[] getStats(){
        return stats;
    }

    public DamageTypes getType() {
        return type;
    }
}
