package com.projectvalis.altk.noc.ch1;

import java.awt.Dimension;
import java.util.logging.Logger;

import com.projectvalis.altk.init.internalFrameDark;
import com.projectvalis.altk.util.Pair;


/**
 * the 'controller' in the vector bouncy ball example in ch1 of nature of code
 * @author snerd
 *
 */
public class BouncingBallRunner extends internalFrameDark {
	
	private static final Logger LOGGER = 
			Logger.getLogger(BouncingBallRunner.class.getName());
	
	private BouncingBall bouncingBall;
	
	
	/**
	 * position and create the window and get the ball rolling -er- bouncing
	 */
	public BouncingBallRunner() {
		setLocation(800, 200);		
		bouncingBall = new BouncingBall();
		
		BouncingBallPanel bouncingBallPanel = 
				new BouncingBallPanel(bouncingBall);
		
		add(bouncingBallPanel);	
		attach(true);
		animate();
	}
	
	
	
	/**
	 * handles the logic that figures out ball movement
	 */
	private void animate() {
		
		boolean keepOnTrucknB = true;
		Vector accelerationV = new Vector(0.1, 0.3);
		
		while (keepOnTrucknB) {					
			int panelWidthI = this.getWidth();
			int panelHeightI = this.getHeight();
			
			try {
				bouncingBall.setAcceleration(accelerationV);
				Pair<Double, Double> ballPosP = bouncingBall.getLocation();
				
				if ((ballPosP.getLeft() > panelWidthI) || 
						ballPosP.getLeft() < 0) {
					
					bouncingBall.accelerationV.xD = 
							bouncingBall.accelerationV.xD * -1;						
				}
				
				if ((ballPosP.getRight() > panelHeightI) || 
						(ballPosP.getRight() < 0)) {
					bouncingBall.accelerationV.yD = 
							bouncingBall.accelerationV.yD * -1;	
				}
				
				bouncingBall.update();
				
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
