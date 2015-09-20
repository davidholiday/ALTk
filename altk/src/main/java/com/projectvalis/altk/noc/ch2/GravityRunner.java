package com.projectvalis.altk.noc.ch2;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.init.internalFrameDark;
import com.projectvalis.altk.noc.ch1.Ball;
import com.projectvalis.altk.noc.ch1.BallPanel;
import com.projectvalis.altk.noc.ch1.Vector;


/**
 * the 'controller' in the vector gravity example in ch2 of nature of code
 * @author snerd
 *
 */
public class GravityRunner extends internalFrameDark {
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(GravityRunner.class.getName());
	
	private Ball[] ballArr = new Ball[5];
	private BallPanel ballPanel;
	private GravityBall gravityBall;
	
	
	/**
	 * 
	 */
	public GravityRunner() {
		setLocation(800, 200);	
		
		
		Vector gravityBallLocationV = 
				new Vector(this.getWidth() / 2, this.getHeight() / 2);
		
		gravityBall = new GravityBall(gravityBallLocationV, 
												  new Vector(0, 0), 
												  new Vector(0, 0), 
												  Color.black, 
												  GUI.mustardC, 
												  50, 
												  50,
												  50);	
		
		
		
		
		Color[] colorArr = new Color[5];
		colorArr[0] = GUI.mustardC;
		colorArr[1] = GUI.orangeC;
		colorArr[2] = GUI.purpleC;
		colorArr[3] = GUI.redC;
		colorArr[4] = GUI.tealC;
	
		Random randy = new Random();
		
		Color fillColor = GUI.tealC;
		int diameterI = 25;
		Vector ballLocationV = gravityBallLocationV.clone();
		ballLocationV.xD += 75;
		ballLocationV.yD -= 25;
		
		Ball ball = new Ball(ballLocationV, 
							 new Vector(0, 0), 
							 new Vector(0.35, 1), 
							 Color.black, 
							 fillColor, 
							 diameterI, 
							 diameterI,
							 diameterI);
		
		ballArr[0] = ball;
		
		for (int i = 1; i < ballArr.length; i ++) {
			diameterI = randy.nextInt(100);
			
			int colorIndexI = randy.nextInt(5);		
			fillColor = colorArr[colorIndexI];
			double locationX_D = randy.nextInt(this.getWidth());
			double locationY_D = randy.nextInt(this.getHeight());
			Vector locationV = new Vector(locationX_D, locationY_D);
						
			ball = new Ball(locationV, 
							new Vector(0, 0), 
							new Vector(0, 0), 
							Color.black, 
							fillColor, 
							diameterI, 
							diameterI,
							diameterI);		

			ballArr[i] = ball;		
		}
		
		ballArr[ballArr.length - 1] = gravityBall;
		
		ballPanel = new BallPanel(ballArr);
		add(ballPanel);	
		
		attach(true);
		animate();
	}
	
	
	
	/**
	 * handles the logic that figures out ball movement
	 */
	private void animate() {
		
		int gravityBallIndexI = ballArr.length -1;
		
		GravityBall gravityBall = 
				(GravityBall)ballArr[gravityBallIndexI];
				
		boolean keepOnTrucknB = true;

		while (keepOnTrucknB) {					
			int panelWidthI = this.getWidth();
			int panelHeightI = this.getHeight();
			
			// get mouse location
			Point p = ballPanel.getMousePosition();
		
			if (p != null && getMouseOver(p, gravityBall)) {
				gravityBall.fillColorC = GUI.orangeC;
			} else {
				gravityBall.fillColorC = GUI.mustardC;
			}			
			
			
			try {	

				for (int i = 0; i < gravityBallIndexI; i ++) {
					Ball ball = ballArr[i];
				

					
					
					Vector attractionV = gravityBall.attract(ball);
					ball.applyForce(attractionV);
					ball.update(panelWidthI, panelHeightI);
				}
				
				repaint();
				Thread.sleep(10);
				
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
	private Vector getMouseAccelerationVector(Point p, Ball ball) {
		
		// get mouse vector
		Vector mouseV = new Vector(p.getX(), p.getY());
		
		// get current ball location
		double locationX_D = ball.getLocation().getLeft();
		double locationY_D = ball.getLocation().getRight();
		Vector locationV = new Vector(locationX_D, locationY_D);
		
		// calculate new acceleration vector
		Vector newAccelerationV = new Vector(mouseV.xD, mouseV.yD);
		newAccelerationV.subtract(locationV);
		newAccelerationV.normalize();
		newAccelerationV.multiply(0.5);
		
		return newAccelerationV;
	}


	
	private boolean getMouseOver(Point p, Ball ball) {		
		double centerX_D = ball.centerV.xD;
		double centerY_D = ball.centerV.yD;
		double radiusD = ball.widthD / 2;
		
		double xDistanceD = Math.pow(centerX_D - p.getX(), 2);
		double yDistanceD = Math.pow(centerY_D - p.getY(), 2);
		double distanceD = Math.sqrt(xDistanceD + yDistanceD);
		return (distanceD <= radiusD) ? (true) : (false);
		
	}
	
	
	


	
}










