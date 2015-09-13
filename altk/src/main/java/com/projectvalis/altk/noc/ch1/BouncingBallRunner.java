package com.projectvalis.altk.noc.ch1;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.logging.Logger;

import javax.swing.JPanel;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.init.internalFrameDark;


public class BouncingBallRunner extends internalFrameDark {
	
	// setup logger 
	private static final Logger LOGGER = 
			Logger.getLogger(BouncingBallRunner.class.getName());
	
	
	public void makeItSo() {
		setLocation(800, 200);
		
		Vector locationV = new Vector(100, 100);
		Vector velocityV = new Vector(0.1, 0.8);
		
		BouncingBallPanel bouncingBallPanel = 
				new BouncingBallPanel(locationV, velocityV);
		
		add(bouncingBallPanel);	
		attach(true);
		
		bouncingBallPanel.animate();
	}
	
	
	
	
	
	public class BouncingBallPanel extends JPanel{
		
		private Vector locationV;
		private Vector velocityV;

		
		public BouncingBallPanel(Vector location, Vector velocity) {
			locationV = location;
			velocityV = velocity;
			setBackground(GUI.charcoalC);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Ellipse2D ball = 
					new Ellipse2D.Double(locationV.xD, locationV.yD, 50, 50);
			
			Graphics2D g2 = (Graphics2D)g;
			g2.setColor(GUI.redC);
			g2.setStroke(new BasicStroke(4));
			g2.draw(ball);
			g2.setColor(GUI.mustardC);
			g2.fill(ball);	
		}
		
		public void animate() {
			
			while (true) {
				int panelWidthI = this.getWidth();
				int panelHeightI = this.getHeight();
								
				try {
					locationV.add(velocityV);
					
					if ((locationV.xD > panelWidthI) || (locationV.xD < 0)) {
						velocityV.xD = velocityV.xD * -1;
					}
					
					if ((locationV.yD > panelHeightI) || (locationV.yD < 0)) {
						velocityV.yD = velocityV.yD * -1;
					}
					
					repaint();
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
	
	}
	
	
}
