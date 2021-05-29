package kchandra423.graphics.screens;

import g4p_controls.GAbstractControl;
import g4p_controls.GButton;
import g4p_controls.GEvent;
import g4p_controls.GSlider;
import processing.core.PConstants;

public class PerformanceScreen implements Screen{
    private int goalFrameRate=60;
    private GSlider frameRate;
    private GButton save;
    @Override
    public void draw(DrawingSurface d) {

        d.background(100);
        d.pushStyle();

        d.fill(255);
        d.textSize(60);

        d.textAlign(PConstants.CENTER, PConstants.BOTTOM);
        d.textSize(20);
        d.text("Framerate\n" +goalFrameRate, d.width / 2f, d.height / 2f-50);
        d.popStyle();
    }

    @Override
    public void setup(DrawingSurface d) {
        frameRate = new GSlider(d, d.width/2f - d.width/4f,d.height/2f-30,d.width/2f, 60, 50);
        frameRate.setLimits(60,30,90);
        frameRate.setVisible(false);
        frameRate.setShowTicks(true);
        frameRate.addEventHandler(this,"setFrameRate");
        save = new GButton(d, d.width / 4f - 300, d.height - d.height / 6f - 40, 400, 80, "Save");
        save.addEventHandler(this, "save");
        save.setVisible(false);
    }

    @Override
    public GAbstractControl[] getUI() {
        return new GAbstractControl[]{frameRate,save};
    }
    public void setFrameRate(GSlider source, GEvent event){
        if(event == GEvent.RELEASED){
            goalFrameRate = source.getValueI();
        }
    }
    public void save(GButton b, GEvent event){
        ((DrawingSurface)b.getPApplet()).changeFrameRate(goalFrameRate);

        DrawingSurface.setScreen(Window.HOME);
    }
}
