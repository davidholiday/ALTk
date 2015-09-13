package com.projectvalis.altk.noc.ch1;

import java.awt.Color;

import com.projectvalis.altk.init.GUI;


/**
 * the 'model' for the bouncing ball in the ch1 examples.
 * 
 * @author snerd
 *
 */
public class BouncingBall {

	public Vector locationV = new Vector(100, 100);
	public Vector velocityV = new Vector(0.1, 0.8);
	public Color strokeColorC = GUI.redC;
	public Color fillColorC = GUI.mustardC;
	public double widthD = 50;
	public double heightD = 50;
	
	
	/**
	 * to create and position a default ball element
	 * 
	 * @param location
	 * 		where the thing starts in the window
	 * @param velocity
	 * 		what the rate of change of location for the thing should be 
	 */
	public BouncingBall(Vector location, Vector velocity) {
		locationV = location;
		velocityV = velocity;
	}
	
	
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
	public BouncingBall(Vector location, Vector velocity, Color strokeColor, 
			Color fillColor, double width, double height) {
		
		locationV = location;
		velocityV = velocity;
		strokeColorC = strokeColor;
		fillColorC = fillColor;
		widthD = width;
		heightD = height;
	}
	
	
	
}
