package kchandra423.actors.movingActors.players;

import kchandra423.actors.movingActors.MovingActor;
import kchandra423.actors.weapons.guns.SMG;
import kchandra423.actors.weapons.guns.Shotgun;
import kchandra423.utility.AssetLoader;

/**
 * Represents a Rogue type player
 *
 * @author Kumar Chandra
 */
public class Rogue extends Player {
    private static final float[] stats = MovingActor.createStates(2f, 1f, 1.25f, 1.5f, 1f, 1f);

    /**
     * Creates a new Rogue at the given x y position
     *
     * @param x The x coord
     * @param y The y coord
     */
    public Rogue(float x, float y) {
        super(AssetLoader.getImage(AssetLoader.Sprite.ROGUE_IDLE),
                AssetLoader.getImage(AssetLoader.Sprite.ROGUE_IDLE),
                13.5f, 1.5f, stats,
                200
                , Math.random() > 0.5 ? new Shotgun(stats) : new SMG(stats));
        image.moveTo(x, y);
    }
}
