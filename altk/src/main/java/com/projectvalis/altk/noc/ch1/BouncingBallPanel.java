package com.projectvalis.altk.noc.ch1;

import java.awt.Graphics;
import javax.swing.JPanel;
import com.projectvalis.altk.init.GUI;

public class BouncingBallPanel extends JPanel{
	
	private Vector locationV;
	private Vector velocityV;

	public BouncingBallPanel(Vector location, Vector velocity) {
		locationV = location;
		velocityV = velocity;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(GUI.mustardC);
		g.drawOval((int)locationV.xF, (int)locationV.yF, 50, 50);
		//g.fillRect(0, 0, this.getWidth(), this.getHeight());

	}

	
}
