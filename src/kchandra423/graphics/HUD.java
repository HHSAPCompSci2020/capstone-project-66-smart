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

    }
}
