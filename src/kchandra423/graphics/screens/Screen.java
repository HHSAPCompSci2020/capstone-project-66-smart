package kchandra423.graphics.screens;

import g4p_controls.GButton;
import kchandra423.graphics.screens.DrawingSurface;

public interface Screen {
    void draw(DrawingSurface d);
    void setup(DrawingSurface d);
    GButton[] getButtons();
}
