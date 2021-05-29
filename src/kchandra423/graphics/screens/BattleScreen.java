package kchandra423.graphics.screens;

import g4p_controls.GAbstractControl;
import g4p_controls.GButton;
import kchandra423.actors.movingActors.players.Knight;
import kchandra423.actors.movingActors.players.Mage;
import kchandra423.actors.movingActors.players.Player;
import kchandra423.actors.movingActors.players.Rogue;
import kchandra423.utility.AssetLoader;
import sye348.levels.Level;
import sye348.levels.LevelOne;

public class BattleScreen implements Screen {

    private GButton pause;
    private static Level level;
    private final HUD hud;
    private static boolean ready = false;
    enum PlayerType{
        MAGE,KNIGHT,ROGUE;
    }
    public BattleScreen() {
        hud = new HUD();
    }

    static void init(PlayerType p) {
        AssetLoader.loadKImages();
        Player player;
        if(p==PlayerType.MAGE){
            player = new Mage(700,700);
        }else if(p == PlayerType.KNIGHT){
            player = new Knight(700,700);
        }else{
            player = new Rogue(700,700);
        }
        BattleScreen.level = new LevelOne(player);
        ready = true;
    }

    /**
     * Draws the current room and everything in it
     */
    public void draw(DrawingSurface p) {
        if (ready) {
            p.background(100);
            p.pushMatrix();
            int halfx = p.width / 2;
            int halfy = p.height / 2;
            if (level.isCompleted()) {
                level = level.getNextLevel();
            }
            if (level == null) {
                p.background(0);
                p.textSize(50);
                p.fill(255);
                p.text("Congrats \n You won!", p.width / 3, p.height / 2);
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
                p.text("Game Over \n You Lost!", p.width / 3, p.height / 2);
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

    }

    @Override
    public void setup(DrawingSurface d) {

    }

    @Override
    public GAbstractControl[] getUI() {
        return new GButton[0];
    }

    public static Level getCurrentlevel() {
        return level;
    }
}

