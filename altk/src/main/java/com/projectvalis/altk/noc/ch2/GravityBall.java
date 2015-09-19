package com.projectvalis.altk.noc.ch2;

import java.awt.Color;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.noc.ch1.Ball;
import com.projectvalis.altk.noc.ch1.Vector;


public class GravityBall extends Ball {

	private static final Logger LOGGER = 
			LoggerFactory.getLogger(GravityBall.class.getName());
	

	double gravConstantD = 0.2;
	double lowerDistanceConstraintD = 5.0;
	double upperDistanceConstraintD = 25.0;
	
	/**
	 * full control constructor. controls size and color of the ball
	 * 
	 * @param location
	 * 		where the thing starts in the window
	 * @param velocity
	 * 		what the rate of change of location for the thing should be
	 * @param strokeColor
	 * 		color of the outline of the circle
	 * @param fillColor
	 * 		color of the interior of the circle
	 * @param width
	 * 		horizontal size of the circle
	 * @param height
	 * 		vertical size of the circle
	 * @param mass
	 * 		indicator of mass in the physics sense
	 */
	public GravityBall (Vector location, Vector velocity, Vector acceleration, 
				Color strokeColor, Color fillColor, double width,
				double height, double mass) {
		
		super(location, velocity, acceleration, strokeColor, fillColor, width,
				height, mass);
	}
	
	 
	/**
	 * TODO: comment this mother fucker -- make notes on how all this works
	 * from the book. 
	 * @param ball
	 * @return
	 */
	Vector attract(Ball ball) {
		
		Vector ballLocationV = new Vector(
				ball.getLocation().getLeft(), ball.getLocation().getRight());
		
		Vector forceV = this.locationV.clone();
		forceV.subtract(ballLocationV);
		
		double distanceD = forceV.getMagnitude();
		
		distanceD = (distanceD < lowerDistanceConstraintD) ? 
				(lowerDistanceConstraintD) : (distanceD);
				
		distanceD = (distanceD > upperDistanceConstraintD) ? 
				(upperDistanceConstraintD) : (distanceD);
		
		forceV.normalize();
		
		double strengthD = 
				(gravConstantD * massD * ball.massD) / (distanceD * distanceD);
		
		forceV.multiply(strengthD);

		return forceV;
	}
	
	
	
	public void setLocation(Vector newLocation) {
		this.locationV = newLocation;
	}
	
}
