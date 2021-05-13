package rlee348.menu;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

import javax.swing.*;
import java.awt.*;


public class GuiTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainMenu drawing = new MainMenu();
		
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();
		surf.setSize(1024,768);
		surf.setResizable(true);
	//	window.setSize(1024, 768);
		window.setMinimumSize(new Dimension(1024,768));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setResizable(true);

		window.setVisible(true);
		canvas.requestFocus();

	}

}
