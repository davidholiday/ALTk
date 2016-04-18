package com.projectvalis.altk.noc.ch1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.init.internalFrameDark;


/**
 * the 'controller' in the vector bouncy ball example in ch1 of nature of code
 * @author snerd
 *
 */
public class BallRunner extends internalFrameDark {
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(BallRunner.class.getName());
	
	private List<Element> elementL = 
			Collections.synchronizedList(new ArrayList<Element>());
	
	private ElementPanel ballPanel;
	
	
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
		
		for (int i = 0; i < 10; i ++) {
			int diameterI = randy.nextInt(100);
			int colorIndexI = randy.nextInt(5);		
			Color fillColor = colorArr[colorIndexI];
			
			Element ball = new BouncingBall(new Vector(25 + (i * 50), 25), 
									new Vector(0, 0), 
									new Vector(0, 0), 
									Color.black, 
									fillColor, 
									diameterI, 
									diameterI,
									diameterI);		

			elementL.add(ball);
			
		}
		
			ballPanel = new ElementPanel(elementL);
			add(ballPanel);	
		
		
		this.attach(true);
		this.setTitle("Bouncing Ball Demo");
		animate();
	}
	
	
	
	/**
	 * handles the logic that figures out ball movement
	 */
	private void animate() {
		Graphics2D g2d = (Graphics2D) ballPanel.getGraphics();
		boolean keepOnTrucknB = true;
//		ball.accelerationV= new Vector(0.001, 0.003);
		Vector windV = new Vector(0.01, 0);
		Vector gravityV = new Vector(0, 0.1);

		/*
		 * for calculating friction: 
		 * friction vector = -1 * [mu] * N * v[vector]
		 * 		- friction points in the opposite direction as velocity. this
		 * 		is why we're multiplying the velocity vector by -1. this means
		 * 		we need to take the velocity vector, normalize it, and then
		 * 		multiply it by (-1).
		 * 
		 * 		- the magnitude of the friction vector is [mu] (pronounced 
		 * 		"mew") multiplied by the normal force 'N'. [mu] is the 
		 * 		coefficient of friction, which establishes the strength of a 
		 * 		friction force of a particular surface. A 'normal force' is 
		 * 		is a force that's perpendicular to an object's motion along
		 * 		a surface. The direction of the normal force and that of 
		 * 		gravity may be different, but the magnitude of the normal force
		 * 		is proportional to that of gravity. 
		 */
		double muD = 0.5;
		double normalForceD = 1;
		double frictionMagnitudeD = muD * normalForceD;
		
		
		while (keepOnTrucknB) {					
			int panelWidthI = this.getWidth();
			int panelHeightI = this.getHeight();
			
			try {	
				
				for (Element ballE : elementL) {
										
//					// get mouse location
//					Point p = ballPanel.getMousePosition();
//					
//					if (p != null)
//						ball.accelerationV = getMouseAccelerationVector(p);
					
					
					// calculate friction
					Vector frictionV = ballE.velocityV.clone();
					frictionV.multiply(-1);
					frictionV.normalize();
					frictionV.multiply(muD);
					
					// calculate gravity
					Vector appliedGravityV = gravityV.clone();
					appliedGravityV.multiply(ballE.massD);
					
					// apply force 
					ballE.applyForce(windV);
					ballE.applyForce(appliedGravityV);
					//ball.applyForce(frictionV);
					ballE.update(panelWidthI, panelHeightI);
				}
				
				this.repaint();
				Thread.sleep(10);
				
				// ensure the window is still open
				keepOnTrucknB = (this.isClosed) ? (false) : (true);
				
			} catch (InterruptedException e) {
				LOGGER.error("EXITING ON ERROR! ");
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
	private Vector getMouseAccelerationVector(Point p, Element ball) {
		
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










