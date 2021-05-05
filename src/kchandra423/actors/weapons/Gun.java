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

    public void draw(DrawingSurface d) {
        d.fill(255);
        for (Projectile p :
                projectiles) {
//            Rectangle bounds = p.getBounds();
//            d.rect(bounds.x,bounds.y, bounds.width, bounds.height);
            p.draw(d);
        }

        super.draw(d);
    }

    public void fire() {
        if(canFire()) {
            float tempAngle = (float) image.getAngle();
            if (Math.random() >= 0.5) {
                tempAngle += Math.random() * spread / 2;
            } else {
                tempAngle -= Math.random() * spread / 2;
            }
            projectiles.add(new Projectile(
                    new KImage((float) (image.getX() + 60 * Math.cos(image.getAngle())), (float) (image.getY() + 60 * Math.sin(image.getAngle()))
                            , false, false, projectile, projectileArea)
                    , projectileVelocity, tempAngle));
            lastTimeShot = System.currentTimeMillis();
        }
    }

    public boolean canFire() {
        if(magazine>0) {
            if(System.currentTimeMillis()-lastTimeShot>=fireRate*1000) {
                return !reloading;
            }
        }
        return false;
    }
}
