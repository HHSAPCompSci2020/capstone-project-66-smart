package kchandra423.graphics;

import kchandra423.actors.movingActors.players.Player;
import sye348.levels.Level;
import sye348.levels.LevelOne;

public class BattleScreen implements  Screen{


    private static Level level;
    private final HUD hud;
    public BattleScreen(Player p){

        level = new LevelOne(p);
        hud = new HUD();
    }
    /**
     * Draws the current room and everything in it
     */
    public void draw(DrawingSurface p) {

        p.background(255);
        p.pushMatrix();
        int halfx = p.width / 2;
        int halfy = p.height / 2;
        if (level == null) {
            p.background(0);
            p.textSize(50);
            p.fill(255);
            p.text("Congrats \n You won!", p.width/3, p.height/2);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            p.exit();
                            System.exit(0);
                        }
                    },
                    5000
            );
            return;
        }
        if (level.isCompleted()) {
            level = level.getNextLevel();
        }

        p.translate(-level.getCurrentRoom().getPlayer().getImage().getX() + halfx - level.getCurrentRoom().getPlayer().getImage().getTexture().getWidth() / 2.0f, -level.getCurrentRoom().getPlayer().getImage().getY() + halfy - level.getCurrentRoom().getPlayer().getImage().getTexture().getHeight() / 2.0f);
        level.draw(p);
        p.popMatrix();
        hud.draw(p, level);
        p.fill(0);
        p.text(p.frameRate + " : fps", p.width - 100, p.height - 100);

        if (!level.getCurrentRoom().getPlayer().isActive()) {

            p.background(0);
            p.textSize(50);
            p.fill(255);
            p.text("Game Over \n You Lost!", p.width/3, p.height/2);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            p.exit();
                            System.exit(0);
                        }
                    },
                    5000
            );
        }

    }
    public static Level getCurrentlevel(){
        return level;
    }
}

