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
	 * constructor bootstraps the whole demo
	 */
	public GravityRunner() {
		this.setLocation(800, 200);	
			
		Color[] colorArr = new Color[5];
		colorArr[0] = GUI.mustardC;
		colorArr[1] = GUI.orangeC;
		colorArr[2] = GUI.purpleC;
		colorArr[3] = GUI.redC;
		colorArr[4] = GUI.tealC;
	
			
		// create the gravity ball first 
		//
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
		

		// create orbiting ball
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
		
		
		// create balls at random locations of random ontologies 
		//
		Random randy = new Random();
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
		
		// append gravity ball to tail of array
		ballArr[ballArr.length - 1] = gravityBall;
		
		// put it all together
		ballPanel = new BallPanel(ballArr);
		this.add(ballPanel);		
		this.attach(true);
		
		// showtime!
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
			
			Point p = ballPanel.getMousePosition();
		
			// check if mouse is over gravity ball
			//
			if (p != null && getMouseOver(p, gravityBall)) {
				gravityBall.fillColorC = GUI.orangeC;
			} else {
				gravityBall.fillColorC = GUI.mustardC;
			}			
			
			// go through ball array and update their positions 
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
	 * tells us whether or not the mouse pointer is currently over a ball - only
	 * used to detect whether or not the mouse is over the gravity ball.
	 * 
	 * works by calculating distance from center of ball for mouse pointer and
	 * checks that against the radius of the ball.
	 * 
	 * @param p
	 * 		current mouse point
	 * @param ball
	 * 		ball to be checked for colocation
	 * @return
	 * 		true if mouse is atop ball -- else false.
	 */
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


