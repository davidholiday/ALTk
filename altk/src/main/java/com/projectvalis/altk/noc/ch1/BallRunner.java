package com.projectvalis.altk.noc.ch1;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Logger;

import com.projectvalis.altk.init.internalFrameDark;


/**
 * the 'controller' in the vector bouncy ball example in ch1 of nature of code
 * @author snerd
 *
 */
public class BallRunner extends internalFrameDark {
	
	private static final Logger LOGGER = 
			Logger.getLogger(BallRunner.class.getName());
	
	private Ball ball;
	private BallPanel ballPanel;
	
	
	/**
	 * position and create the window and get the ball rolling -er- bouncing
	 */
	public BallRunner() {
		setLocation(800, 200);		
		ball = new Ball();		
		ballPanel = new BallPanel(ball);
		add(ballPanel);	
		attach(true);
		animate();
	}
	
	
	
	/**
	 * handles the logic that figures out ball movement
	 */
	private void animate() {
		
		boolean keepOnTrucknB = true;
		ball.accelerationV= new Vector(0.001, 0.003);
		
		while (keepOnTrucknB) {					
			int panelWidthI = this.getWidth();
			int panelHeightI = this.getHeight();
			
			try {	
				
				if (ballPanel.isMouseInFrame())
					ball.accelerationV = getMouseAccelerationVector();
				
				ball.update(panelWidthI, panelHeightI);
				repaint();
				Thread.sleep(1);
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
	private Vector getMouseAccelerationVector() {
		
		// get mouse location
		Point p = ballPanel.getMousePosition();		
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










