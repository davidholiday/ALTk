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
	 */
	public BouncingBall(Vector location, Vector velocity, Vector acceleration, 
			Color strokeColor, Color fillColor, double width, double height) {
		
		this.locationV = location;
		this.velocityV = velocity;
		this.accelerationV = acceleration;
		this.strokeColorC = strokeColor;
		this.fillColorC = fillColor;
		this.widthD = width;
		this.heightD = height;
	}
	

	/**
	 * call this after you manipulate the acceleration vector directly. 
	 */
	public void update(int panelWidth, int panelHeight) {
		velocityV.add(accelerationV);
		applyVelocityLimit();
		locationV.add(velocityV);
		checkEdges(panelWidth, panelHeight);
	}
	
	
	/**
	 * makes the ball appear to bounce when it reaches a window edge
	 * @param panelWidth
	 * @param panelHeight
	 */
	protected void checkEdges(int panelWidth, int panelHeight) {
		
		if ((locationV.xD > panelWidth) || locationV.xD < 0) {
			accelerationV.xD = accelerationV.xD * -1;	
			velocityV.xD = velocityV.xD * -1;
		}
		
		if ((locationV.yD > panelHeight) || locationV.yD < 0) {
			accelerationV.yD = accelerationV.yD * -1;	
			velocityV.yD = velocityV.yD * -1;
		}
	}

	
}
