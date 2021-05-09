package kchandra423.actors.MovingActors.enemies;

import kchandra423.actors.MovingActors.ActorState;
import kchandra423.actors.MovingActors.MovingActor;
import kchandra423.actors.weapons.projectiles.Projectile;
import kchandra423.graphics.DrawingSurface;
import kchandra423.graphics.textures.KImage;
import kchandra423.graphics.textures.Texture;
import kchandra423.levels.Room;
import kchandra423.utility.Calculator;
import processing.core.PApplet;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.TimerTask;

public class RangedEnemy extends Enemy {
    private final float projectileVelocity;
    private final float fireRate;
    private long lastTimeShot;
    private final Texture projectile;
    private final Area projectileArea;
    private ArrayList<Projectile> projectiles;

    protected RangedEnemy(KImage[] images, float maxV, float accel) {
        super(images, maxV, accel);
        projectiles = new ArrayList<>();
        projectile = Texture.TextureBuilder.getTexture("res/Images/Projectiles/Hex.gif");
        projectileArea = KImage.loadArea(projectile);
        projectileVelocity = 7;
        fireRate = 1f;
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
    protected void onOpponentCollision(MovingActor opponent) {
        float tempAngle = (float) Calculator.calculateAngle(image.getX(), image.getY(), opponent.getImage().getBounds().getCenterX(), opponent.getImage().getBounds().getCenterY());
        tempAngle += Math.random() * Math.PI / 8;
        tempAngle -= Math.PI / 8 / 2;
        projectiles.add(new Projectile(
                new KImage((float) (image.getBounds().getCenterX()), (float) (image.getBounds().getCenterY())
                        , false, false, projectile, projectileArea)
                , projectileVelocity, tempAngle, false));
        lastTimeShot = System.currentTimeMillis();
    }

    @Override
    protected MovingActor collidesWOppponent(Room room) {
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
