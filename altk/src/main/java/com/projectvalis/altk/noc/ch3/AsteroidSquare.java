package com.projectvalis.altk.noc.ch3;

import java.awt.Color;

import com.projectvalis.altk.noc.ch1.Vector;
import com.projectvalis.altk.util.EdgeDetectors;

/**
 * so far same as a regular square except for loop edge detection
 * @author snerd
 *
 */
public class AsteroidSquare extends Square {
	
	
	/**
	 * full control constructor. controls size and color of the element
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
	public AsteroidSquare (Vector location, 
						  Vector velocity, 
						  Vector acceleration, 
						  Color strokeColor, 
						  Color fillColor, 
						  double width,
						  double height, 
						  double mass) {
		
		locationV = location;
		velocityV = velocity;
		accelerationV = acceleration;
		strokeColorC = strokeColor;
		fillColorC = fillColor;
		widthD = width;
		heightD = height;
		massD = mass;
	}
	
	@Override
	protected void checkEdges(int panelWidth, int panelHeight) { 
		this.locationV = 
				EdgeDetectors.loopEdges(panelWidth, panelHeight, locationV);	
		
	}

}
