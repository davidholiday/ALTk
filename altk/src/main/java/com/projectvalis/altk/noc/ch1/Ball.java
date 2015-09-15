package com.projectvalis.altk.noc.ch1;

import java.awt.Color;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.util.Pair;

/**
 * root class for ball elements. contains logic that specifies look, 
 * movement vectors, and window edge collision behavior. 
 * 
 * @author snerd
 *
 */
public class Ball {

	protected Vector locationV = new Vector(100, 100);
	protected Vector velocityV = new Vector(0, 0);
	public Vector accelerationV = new Vector(0, 0);
	
	public Color strokeColorC = GUI.orangeC;
	public Color fillColorC = GUI.mustardC;
	
	// ensure default behavior is to create a circle, and to set mass
	// equal to the diameter of that circle 
	public double widthD = 50;
	public double heightD = widthD;
	public double massD = widthD;
	
	// ensure velocity doesn't get out of hand
	public double velocityLimitD = 1;
	

	// placeholder
	public Ball() {}

	
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
	 */
	public Ball(Vector location, Vector velocity, Vector acceleration, 
				Color strokeColor, Color fillColor, double width,
				double height, double mass) {
		
		locationV = location;
		velocityV = velocity;
		accelerationV = acceleration;
		strokeColorC = strokeColor;
		fillColorC = fillColor;
		widthD = width;
		heightD = height;
		massD = mass;
	}
	
	

	/**
	 * returns location w/o granting access to this object's location
	 * vector. 
	 * 
	 * @return
	 * 		Pair(x, y)
	 */
	public Pair<Double, Double> getLocation() {
		return new Pair<Double, Double>(locationV.xD, locationV.yD);
	}
	
	
	
	/**
	 * returns velocity w/o granting access to this object's velocity
	 * vector
	 * 
	 * @return
	 * 		Pair(x, y)
	 */
	public Pair<Double, Double> getVelocity() {
		return new Pair<Double, Double>(velocityV.xD, velocityV.yD);
	}
	
	
	
	
	/**
	 * call this after you manipulate the acceleration vector directly. 
	 */
	public void update(int panelWidth, int panelHeight) {
		
		// apply the acceleration vector to the object
		velocityV.add(accelerationV);
		applyVelocityLimit();
		locationV.add(velocityV);
		
		// handle case where ball reaches window edge
		checkEdges(panelWidth, panelHeight);
		
		// reset the acceleration vector to zero because we're recalculating 
		// it every time step
		accelerationV.multiply(0);
	}
	
	
	/**
	 * adds a force vector to the acceleration vector. remember - "a force is a
	 * vector that causes an object with mass to accelerate"
	 * @param force
	 */
	public void applyForce(Vector force) {
		Vector forceLocalV = force.clone();
		forceLocalV.divide(massD);
		accelerationV.add(forceLocalV);
	}
	
	
	
	
	/**
	 * makes the ball appear to bounce when it reaches a window edge
	 * @param panelWidth
	 * @param panelHeight
	 */
	protected void checkEdges(int panelWidth, int panelHeight) {	
		
		if (locationV.xD > panelWidth) locationV.xD = panelWidth - 50;
		if (locationV.xD < 0) locationV.xD = 0;
		
		if (locationV.yD > panelHeight) locationV.yD = panelHeight - 50;
		if (locationV.yD < 0) locationV.yD = 0;
		
	}
	
	
	
	
	/**
	 * ensures velocity doe not exceed limit
	 */
	protected void applyVelocityLimit() {
		
		if (Math.abs(velocityV.xD) > velocityLimitD) {
			velocityV.xD = velocityLimitD * Math.signum(velocityV.xD);
		}
		
		if (Math.abs(velocityV.yD) > velocityLimitD) {
			velocityV.yD = velocityLimitD * Math.signum(velocityV.yD);
		}
		
	}
	
	
}
