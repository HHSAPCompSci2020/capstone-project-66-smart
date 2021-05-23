package kchandra423.graphics;

import kchandra423.actors.movingActors.players.Player;
import kchandra423.graphics.textures.Texture;
import processing.core.PApplet;

public class HUD {
    private Texture bar = Texture.TextureBuilder.getTexture("res/Images/misc/HealthBar.png");
    private Player player;

    public HUD(Player player) {
        this.player = player;
        bar.resize((int) (bar.getWidth() * 1.5), (int) (bar.getHeight() * 1.5));
    }

    public void draw(PApplet p) {
        bar.draw(p, 10, 10);
        p.pushStyle();
        float percentage = (player.getHealth() / (float) player.getMaxHealth());
        percentage = percentage < 0 ? 0 : percentage;
//        rgb(132,222,2) green
//        rgb(175, 0, 42) red)
        p.fill(132, percentage * 222, 42);
        p.rect(15, 15, percentage * (bar.getWidth() - 10), bar.getHeight() - 10);
        p.popStyle();

    }
    
    public int getHealth() {
    	
    	return player.getHealth();
    }
    
    public Player getPlayer() {
    	
    	return player;
    }
}
