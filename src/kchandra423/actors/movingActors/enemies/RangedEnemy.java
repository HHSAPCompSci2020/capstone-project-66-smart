package kchandra423.actors.movingActors.enemies;

import kchandra423.actors.movingActors.constants.ActorState;
import kchandra423.actors.movingActors.constants.DamageTypes;
import kchandra423.actors.movingActors.MovingActor;
import kchandra423.actors.weapons.projectiles.Projectile;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.levels.Room;
import kchandra423.utility.AssetLoader;
import kchandra423.utility.Calculator;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Represents an enemy that attacks the player from a range
 *
 * @author Kumar Chandra
 */
public abstract class RangedEnemy extends Enemy {
    private final float projectileVelocity;
    private final float fireRate;
    private final int damage;
    private final DamageTypes type;
    private long lastTimeShot;
    private final AssetLoader.Sprite projectile;
    private ArrayList<Projectile> projectiles;

    protected RangedEnemy(KImage[] images, float maxV, float accel, float[] stats, int health, DamageTypes type, AssetLoader.Sprite projectile, long deathTime, int damage) {
        super(images, maxV, accel, stats, health, deathTime);
        this.type = type;
        this.projectile = projectile;
        projectiles = new ArrayList<>();
        projectileVelocity = 7;
        fireRate = 1f;
        this.damage = damage;
        lastTimeShot = System.currentTimeMillis();
    }

    @Override
    public void draw(DrawingSurface drawingSurface) {
        super.draw(drawingSurface);
        for (Projectile p :
                projectiles) {
            p.draw(drawingSurface);
        }

    }

    @Override
    public void act(DrawingSurface d, Room r) {
        super.act(d, r);
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = projectiles.get(i);
            p.act(d, r);
            if (!p.isActive()) {
                projectiles.remove(i);
                i--;
            }
        }
    }

    @Override
    protected void onOpponentInteraction(MovingActor opponent) {
        float tempAngle = (float) Calculator.calculateAngle(image.getX(), image.getY(), opponent.getImage().getBounds().getCenterX(), opponent.getImage().getBounds().getCenterY());
        tempAngle += Math.random() * Math.PI / 8;
        tempAngle -= Math.PI / 8 / 2;
        Projectile p = new Projectile(AssetLoader.getImage(projectile), projectileVelocity, tempAngle, true, statMultipliers, type, damage);
        p.getImage().moveTo((float) (image.getBounds().getCenterX()), (float) (image.getBounds().getCenterY()));
//        projectiles.add(new Projectile(
//                new KImage((float) (image.getBounds().getCenterX()), (float) (image.getBounds().getCenterY())
//                        , false, false, projectile, projectileArea,st)
//                , projectileVelocity, tempAngle, false));
        lastTimeShot = System.currentTimeMillis();
    }

    @Override
    protected MovingActor interactsWOppponent(Room room) {
        if ((PApplet.dist(image.getX(), image.getY(), (float) room.getPlayer().getImage().getBounds().getCenterX(), (float) room.getPlayer().getImage().getBounds().getCenterY()) < 500) && canFire() && getState() != ActorState.DEAD) {
            updateState(ActorState.ATTACKING);
            vx = 0;
            vy = 0;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    updateState(ActorState.IDLE);
                }
            }, (long) (fireRate * 1000L));
            return room.getPlayer();
        }
        return null;
    }

    private boolean canFire() {
        return System.currentTimeMillis() - lastTimeShot >= fireRate * 1000;
    }

}
