package kchandra423.actors.weapons.guns;

import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.weapons.Weapon;
import kchandra423.actors.weapons.projectiles.Projectile;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;
import kchandra423.utility.AssetLoader;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a basic gun. Currently only fires and has spread.
 *
 * @author Kumar Chandra
 */
class Gun extends Weapon {
    private final Projectile projectile;
    private final ArrayList<Projectile> projectiles;
    private final float fireRate;
    private final int reloadTime;///1000ths
    private int magazine;
    private int damage;
    private final int pellets;
    private final int magazineSize;
    private TimerTask reloadTask;
    private Timer reloadTimer;
    private boolean reloading;
    private long lastTimeShot;
    private final float spread;
    private long timeSinceReloaded;

    Gun(KImage sprite, float fireRate, float spread, Projectile projectile, int pellets) {
        super(sprite);
        this.projectile = projectile;
        this.fireRate = fireRate;
        this.pellets = pellets;
        reloadTime = 500;
        magazineSize = 30;
        magazine = magazineSize;
        reloadTimer = new Timer();
        this.spread = spread;
        timeSinceReloaded = System.currentTimeMillis();
        projectiles = new ArrayList<>();
        reloading = false;
    }

    /**
     * Makes all the projectiles act. Removes inactive projectiles from this guns list of projectiles
     *
     * @param d The drawing surface to be acted upon
     * @param r The room the actor is currently in
     */
    @Override
    public void act(DrawingSurface d, Room r) {
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = projectiles.get(i);
            p.act(d, r);
            if (!p.isActive()) {
                projectiles.remove(i);
                i--;
            }

        }
    }

    /**
     * Draws this actor, as well as all all of its projectiles
     *
     * @param d The drawing surface to be drawn onto
     */
    @Override
    public void draw(DrawingSurface d) {

        super.draw(d);

        for (Projectile p :
                projectiles) {
            p.draw(d);
        }
    }

    /**
     * Creates a new projectile using this images current angle. Applies all effects such as the spread, and velocity and pellets
     */
    public void fire() {
        if (canFire()) {
            for (int i = 0; i < pellets; i++) {
                float tempAngle = image.getAngle();
                tempAngle += Math.random() * spread;
                tempAngle -= spread / 2;
                Projectile p = (Projectile) projectile.clone(tempAngle);

                p.getImage().moveTo((float) (image.getBounds().getCenterX() + image.getWidth() / 2 * Math.cos(image.getAngle())), (float) (image.getBounds().getCenterY() + image.getHeight() / 2 * Math.sin(image.getAngle())));
                projectiles.add(p);
            }
            lastTimeShot = System.currentTimeMillis();
        }
    }

    private boolean canFire() {
        if (magazine > 0) {
            if (System.currentTimeMillis() - lastTimeShot >= fireRate * 1000) {
                return !reloading;
            }
        }
        return false;
    }


}
