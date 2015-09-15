package com.projectvalis.altk.noc.ch1;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.logging.Logger;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.init.internalFrameDark;


/**
 * the 'controller' in the vector bouncy ball example in ch1 of nature of code
 * @author snerd
 *
 */
public class BallRunner extends internalFrameDark {
	
	private static final Logger LOGGER = 
			Logger.getLogger(BallRunner.class.getName());
	
	private Ball[] ballArr = new Ball[25];
	private BallPanel ballPanel;
	
	
	/**
	 * position and create the window and get the ball rolling -er- bouncing
	 */
	public BallRunner() {
		setLocation(800, 200);	
		
		Color[] colorArr = new Color[5];
		colorArr[0] = GUI.mustardC;
		colorArr[1] = GUI.orangeC;
		colorArr[2] = GUI.purpleC;
		colorArr[3] = GUI.redC;
		colorArr[4] = GUI.tealC;
		
		Random randy = new Random();
		
		for (int i = 0; i < 25; i ++) {
			int diameterI = randy.nextInt(100);
			int colorIndexI = randy.nextInt(5);		
			Color fillColor = colorArr[colorIndexI];
			
			Ball ball = new BouncingBall(new Vector(10, 300), 
									new Vector(0, 0), 
									new Vector(0, 0), 
									Color.black, 
									fillColor, 
									diameterI, 
									diameterI,
									diameterI * 2);		

			ballArr[i] = ball;
			
		}
		
			ballPanel = new BallPanel(ballArr);
			add(ballPanel);	
		
		
		attach(true);
		animate();
	}
	
	
	
	/**
	 * handles the logic that figures out ball movement
	 */
	private void animate() {
		
		boolean keepOnTrucknB = true;
//		ball.accelerationV= new Vector(0.001, 0.003);
		Vector windV = new Vector(0.01, 0);
		Vector gravityV = new Vector(0, 0.1);
		
		
		while (keepOnTrucknB) {					
			int panelWidthI = this.getWidth();
			int panelHeightI = this.getHeight();
			
			try {	
				
				for (int i = 0; i < ballArr.length; i ++) {
					Ball ball = ballArr[i];
					
//					// get mouse location
//					Point p = ballPanel.getMousePosition();
//					
//					if (p != null)
//						ball.accelerationV = getMouseAccelerationVector(p);
					
					ball.applyForce(windV);
					ball.applyForce(gravityV);
					ball.update(panelWidthI, panelHeightI);
				}
				
				repaint();
				Thread.sleep(5);
				
			} catch (InterruptedException e) {
				LOGGER.severe("EXITING ON ERROR! ");
				e.printStackTrace();
				keepOnTrucknB = false;
			}
			
		}
		
	}
	
	
	/**
	 * calculates an acceleration vector based on current ball position and
	 * location of mouse pointer
	 * 
	 * @return
	 * 		new acceleration vector
	 */
	private Vector getMouseAccelerationVector(Point p, Ball ball) {
		
		// get mouse vector
		Vector mouseV = new Vector(p.getX(), p.getY());
		
		// get current ball location
		Double locationX_D = ball.getLocation().getLeft();
		Double locationY_D = ball.getLocation().getRight();
		Vector locationV = new Vector(locationX_D, locationY_D);
		
		// calculate new acceleration vector
		Vector newAccelerationV = new Vector(mouseV.xD, mouseV.yD);
		newAccelerationV.subtract(locationV);
		newAccelerationV.normalize();
		newAccelerationV.multiply(0.5);
		
		return newAccelerationV;
	}
	
	
}










