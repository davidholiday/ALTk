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
public class BouncingBall extends Ball {

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
	 * @param mass
	 * 		indicator of mass in the physics sense
	 */
	public BouncingBall(Vector location, Vector velocity, Vector acceleration, 
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
	 * makes the ball appear to bounce when it reaches a window edge
	 * @param panelWidth
	 * @param panelHeight
	 */
	protected void checkEdges(int panelWidth, int panelHeight) {
		
		if (( (locationV.xD + widthD) > (panelWidth)) || 
				(locationV.xD + widthD) < 0) {
			
			accelerationV.xD = accelerationV.xD * -1;	
			velocityV.xD = velocityV.xD * -1;
		}
		
		if (( (locationV.yD + heightD) > (panelHeight)) || 
				(locationV.yD + heightD) < 0) {
			
			accelerationV.yD = accelerationV.yD * -1;	
			velocityV.yD = velocityV.yD * -1;
		}
	}

	
}
