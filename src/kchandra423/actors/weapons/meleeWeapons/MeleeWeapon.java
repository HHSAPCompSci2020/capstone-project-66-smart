package kchandra423.actors.weapons.meleeWeapons;

import kchandra423.actors.Damage;
import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.movingActors.enemies.Enemy;
import kchandra423.actors.movingActors.enemies.RangedEnemy;
import kchandra423.actors.weapons.Weapon;
import kchandra423.actors.weapons.projectiles.Projectile;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;

public class MeleeWeapon extends Weapon {
    private int damage;
    private float[] stats;
    private float range;
    private final float swingTime;
    private long swingStartTime = System.currentTimeMillis();


    /**
     * Creates an actor with the specified image
     *
     * @param image The specified image
     * @param damage
     * @param stats
     * @param range
     */
    public MeleeWeapon(KImage image, int damage, float[] stats, float range, float swingTime) {
        super(image);
        this.damage = damage;
        this.stats = stats;
        this.range = range;
        this.swingTime=swingTime;
    }

    @Override
    public void act(DrawingSurface d, Room room) {
        //if you cannot fire, it means that you are firing
        if (!canFire()) {
            float differential = (float) (((System.currentTimeMillis() - swingStartTime) /  (swingTime * 1000)) * Math.PI /2* 3 +Math.PI);
            image.rotate((float) (range * Math.cos(differential)));
            for (Enemy e :
                    room.getEnemies()) {
                if (intersects(e)) {
                    e.interceptHitBox(new Damage(damage,stats, DamageTypes.MELEE));
                }
                if(e instanceof RangedEnemy) {
                    for (Projectile p :
                            ((RangedEnemy) e).getProjectiles()) {
                        if(intersects(p)){
                            p.setActive(false);
                        }
                    }
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
