package com.projectvalis.altk.noc.ch1;

import java.awt.Color;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.util.Pair;


/**
 * the 'model' for the bouncing ball in the ch1 examples.
 * 
 * @author snerd
 *
 */
public class BouncingBall {

	private Vector locationV = new Vector(100, 100);
	private Vector velocityV = new Vector(0, 0);
	public Vector accelerationV = new Vector(0, 0);
	public Color strokeColorC = GUI.redC;
	public Color fillColorC = GUI.mustardC;
	public double widthD = 50;
	public double heightD = 50;
	

	// placeholder
	public BouncingBall() {}

	
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
	public BouncingBall(Vector location, Vector velocity, Vector acceleration, 
			Color strokeColor, Color fillColor, double width, double height) {
		
		locationV = location;
		velocityV = velocity;
		accelerationV = acceleration;
		strokeColorC = strokeColor;
		fillColorC = fillColor;
		widthD = width;
		heightD = height;
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
	 * returns velocity w/o granting access to this object's location
	 * vector
	 * 
	 * @return
	 * 		Pair(x, y)
	 */
	public Pair<Double, Double> getVelocity() {
		return new Pair<Double, Double>(velocityV.xD, velocityV.yD);
	}
	
	
	
	/**
	 * returns velocity w/o granting access to this object's location
	 * vector
	 * 
	 * @return
	 * 		Pair(x, y)
	 */
	public Pair<Double, Double> getAcceleration() {
		return new Pair<Double, Double>(accelerationV.xD, accelerationV.yD);
	}
	
	
	
	/**
	 * call this after you manipulate the acceleration vector directly. 
	 */
	public void update() {
		setAcceleration(accelerationV);
	}
	
	
	
	/**
	 * acceleration is rate of change of velocity. velocity is rate of change 
	 * of location. by setting acceleration, you're also setting velocity and
	 * location.
	 * 
	 * @param acceleration
	 * 		what you want the new acceleration to be
	 */
	public void setAcceleration(Vector acceleration) {
		accelerationV = acceleration;
		velocityV.add(accelerationV);
		locationV.add(velocityV);
System.out.println("** velocityV is now: " + velocityV.xD + " " + velocityV.yD);
System.out.println("** locationV is now: " + locationV.xD + " " + locationV.yD);
System.out.println("** acceleration is now: " + accelerationV.xD + " " + accelerationV.yD);
	}
	
	
	
	
}
