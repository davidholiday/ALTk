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


/**
 * the 'controller' in the vector bouncy ball example in ch1 of nature of code
 * @author snerd
 *
 */
public class BouncingBallRunner extends internalFrameDark {
	
	private static final Logger LOGGER = 
			Logger.getLogger(BouncingBallRunner.class.getName());
	
	private BouncingBall bouncingBall;
	
	public BouncingBallRunner() {
		setLocation(800, 200);
		
		Vector locationV = new Vector(100, 100);
		Vector velocityV = new Vector(0.1, 0.8);
		
		bouncingBall = new BouncingBall(locationV, velocityV);
		
		BouncingBallPanel bouncingBallPanel = 
				new BouncingBallPanel(bouncingBall);
		
		add(bouncingBallPanel);	
		attach(true);
		
		animate();
	}
	
	
	private void animate() {
		
		boolean keepOnTrucknB = true;
		
		while (keepOnTrucknB) {
			int panelWidthI = this.getWidth();
			int panelHeightI = this.getHeight();
			
			
							
			try {
				bouncingBall.locationV.add(bouncingBall.velocityV);
				
				if ((bouncingBall.locationV.xD > panelWidthI) || 
						(bouncingBall.locationV.xD < 0)) {
					
					bouncingBall.velocityV.xD = bouncingBall.velocityV.xD * -1;
				}
				
				if ((bouncingBall.locationV.yD > panelHeightI) || 
						(bouncingBall.locationV.yD < 0)) {
					
					bouncingBall.velocityV.yD = bouncingBall.velocityV.yD * -1;
				}
				
				repaint();
				Thread.sleep(1);
			} catch (InterruptedException e) {
				LOGGER.severe("EXITING ON ERROR! ");
				e.printStackTrace();
				keepOnTrucknB = false;
			}
			
		}
		
	}
	
	
	

	
	
}
