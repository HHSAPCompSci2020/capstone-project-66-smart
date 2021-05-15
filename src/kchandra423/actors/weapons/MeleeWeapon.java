package kchandra423.actors.weapons;

import kchandra423.actors.Damage;
import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.movingActors.enemies.Enemy;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;

public class MeleeWeapon extends Weapon {
    private final long swingTime = 1;
    private long swingStartTime = System.currentTimeMillis();

    /**
     * Creates an actor with the specified image
     *
     * @param image The specified image
     */
    public MeleeWeapon(KImage image) {
        super(image);
    }

    @Override
    public void act(DrawingSurface d, Room room) {
        //if you cannot fire, it means that you are firing
        if (!canFire()) {
            float differential = (float) (((System.currentTimeMillis() - swingStartTime) / (float) (swingTime * 1000)) * Math.PI * 2);
            image.rotate((float) (Math.PI / 4 * Math.sin(differential)));
            for (Enemy e :
                    room.getEnemies()) {
                if (intersects(e)) {
                    e.interceptHitBox(new Damage(40,new float[]{1,1,1,1,1,1}, DamageTypes.MELEE));
                }
            }
        }
    }

    @Override
    public void fire() {
        if (canFire()) {
            swingStartTime = System.currentTimeMillis();
        }
    }

    private boolean canFire() {
        return (System.currentTimeMillis() - swingStartTime) >= swingTime * 1000;
    }
}
