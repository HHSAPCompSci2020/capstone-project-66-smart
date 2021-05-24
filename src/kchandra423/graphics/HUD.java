package kchandra423.graphics;

import kchandra423.actors.movingActors.players.Player;
import kchandra423.graphics.textures.Texture;
import processing.core.PApplet;
import sye348.levels.Level;

class HUD {
    private final Texture bar = Texture.TextureBuilder.getTexture("res/Images/misc/HealthBar.png");


    HUD() {
        bar.resize((int) (bar.getWidth() * 1.5), (int) (bar.getHeight() * 1.5));
    }

    void draw(PApplet p, Level level) {
        bar.draw(p, 10, 10);
        p.pushStyle();
        float percentage = (level.getCurrentRoom().getPlayer().getHealth() / (float) level.getCurrentRoom().getPlayer().getMaxHealth());
        percentage = percentage < 0 ? 0 : percentage;
//        rgb(132,222,2) green
//        rgb(175, 0, 42) red)
        p.fill(132, percentage * 222, 42);
        p.rect(15, 15, percentage * (bar.getWidth() - 10), bar.getHeight() - 10);
        p.fill(0);
        p.strokeWeight(30);
        p.textMode(PApplet.CENTER);
        p.text(level.toString(), p.width/2f, p.height-50);
        p.popStyle();

    }
    

}
