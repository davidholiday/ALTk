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
	
	private Ball[] ballArr = new Ball[2];
	private BallPanel ballPanel;
	
	
	/**
	 * 
	 */
	public GravityRunner() {
		setLocation(800, 200);	
		
		
		Vector gravityBallLocationV = 
				new Vector(this.getWidth() / 2, this.getHeight() / 2);
		
		GravityBall gravityBall = new GravityBall(gravityBallLocationV, 
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
		
		for (int i = 0; i < ballArr.length; i ++) {
			//int diameterI = randy.nextInt(100);
			int diameterI = 25;
			int colorIndexI = randy.nextInt(5);		
			//Color fillColor = colorArr[colorIndexI];
			Color fillColor = GUI.orangeC;
			
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
		
		boolean keepOnTrucknB = true;
		
		while (keepOnTrucknB) {					
			int panelWidthI = this.getWidth();
			int panelHeightI = this.getHeight();
			
			try {	
				int gravityBallIndexI = ballArr.length -1;
				
				GravityBall gravityBall = 
						(GravityBall)ballArr[gravityBallIndexI];
				
				for (int i = 0; i < gravityBallIndexI; i ++) {
					Ball ball = ballArr[i];
					
//					// get mouse location
//					Point p = ballPanel.getMousePosition();
//					
//					if (p != null)
//						ball.accelerationV = getMouseAccelerationVector(p);
					
					
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










