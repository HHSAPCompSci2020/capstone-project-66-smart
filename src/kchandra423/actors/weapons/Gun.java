package kchandra423.actors.weapons;

import kchandra423.actors.Actor;
import kchandra423.actors.weapons.projectiles.Projectile;
import kchandra423.levels.Room;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a basic gun. Currently only fires and has spread.
 *
 * @author Kumar Chandra
 */
public class Gun extends Actor {
    private final Texture projectile;
    private final Area projectileArea;
    private final float projectileVelocity;
    private final ArrayList<Projectile> projectiles;
    private final float fireRate;
    private final int reloadTime;///1000ths
    private int magazine;
    private final int magazineSize;
    private TimerTask reloadTask;
    private Timer reloadTimer;
    private boolean reloading;
//    private int ammo;
//    private int pellets;
//    private final int maxAmmo;
    private long lastTimeShot;
    private final float spread;
    private long timeSinceReloaded;

    /**
     * Creates a new gun at the specified location. Currently all other values are hardcoded
     * @param x The x coordinate of the specified location to be created at.
     * @param y The y coordinate of the specified location to be created at.
     */
    public Gun(float x, float y) {
        super(new KImage(x, y, false, true, Texture.TextureBuilder.getTexture("res/Images/Weapons/SMG.png")));
        projectile = Texture.TextureBuilder.getTexture("res/Images/Projectiles/Bullet.png");
        fireRate= 0.1f;
        reloadTime = 500;
        magazineSize=30;
        magazine=magazineSize;
        reloadTimer= new Timer();
        spread = (float)(Math.PI/10);
        timeSinceReloaded = System.currentTimeMillis();
        projectileVelocity = 20;
        projectileArea = KImage.loadArea(projectile);
        projectiles = new ArrayList<>();
        reloading = false;
    }

    /**
     * Makes all the projectiles act. Removes inactive projectiles from this guns list of projectiles
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
     * @param d The drawing surface to be drawn onto
     */
    @Override
    public void draw(DrawingSurface d) {
        for (Projectile p :
                projectiles) {
            p.draw(d);
        }

        super.draw(d);
    }

    /**
     * Creates a new projectile using this images
     */
    public void fire() {
        if(canFire()) {
            float tempAngle = (float) image.getAngle();
                tempAngle += Math.random() * spread ;
                tempAngle -= spread / 2;
            projectiles.add(new Projectile(
                    new KImage((float) (image.getX() + image.getWidth() * Math.cos(image.getAngle())), (float) (image.getY() + image.getHeight()/2 * Math.sin(image.getAngle()))
                            , false, false, projectile, projectileArea)
                    , projectileVelocity, tempAngle));
            lastTimeShot = System.currentTimeMillis();
        }
    }

    private boolean canFire() {
        if(magazine>0) {
            if(System.currentTimeMillis()-lastTimeShot>=fireRate*1000) {
                return !reloading;
            }
        }
        return false;
    }
}
