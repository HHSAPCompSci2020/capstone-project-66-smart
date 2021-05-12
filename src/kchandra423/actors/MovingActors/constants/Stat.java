package kchandra423.actors.MovingActors.constants;

import kchandra423.actors.MovingActors.constants.DamageTypes;
import kchandra423.actors.MovingActors.constants.DefenseTypes;

/**
 * A stat represents any constant that can be represented as an integer value
 * @author Kumar Chandra
 * @see DamageTypes
 * @see DefenseTypes
 */
public interface Stat {
    /**
     * Returns the value of this constant as an integer
     * @return The value of this constant as an integer
     */
    public int getValue();
}
