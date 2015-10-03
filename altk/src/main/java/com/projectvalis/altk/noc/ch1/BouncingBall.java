package com.projectvalis.altk.noc.ch1;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * the 'model' for the bouncing ball in the ch1 examples.
 * 
 * @author snerd
 *
 */
public class BouncingBall extends Element {

	private static final Logger LOGGER = 
			LoggerFactory.getLogger(BouncingBall.class.getName());
	
	
	// ensure default behavior is to create a circle, and to set mass
	// equal to the diameter of that circle 
	public double widthD = 50;
	public double heightD = widthD;
	
	
	public BouncingBall() {
		this.massD = widthD;
		this.shapeGeometry = new Ellipse2D.Double(locationV.xD, 
												  locationV.yD,
												  widthD,
												  heightD);
	}

	
	/**
	 * full control constructor. controls size and color of the shape
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
	public BouncingBall(Vector location, 
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
		
		this.shapeGeometry = new Ellipse2D.Double(locationV.xD, 
				  								  locationV.yD,
				  								  widthD,
				  								  heightD);
	}
	

	
	/**
	 * makes the ball appear to bounce when it reaches a window edge
	 * @param panelWidth
	 * @param panelHeight
	 */
	protected void checkEdges(int panelWidth, int panelHeight) {
		
		// x axis
		//
		if ((locationV.xD + widthD) > panelWidth) {
			velocityV.xD *= -1;
			double locationDiffD = (locationV.xD + widthD) - panelWidth;
			locationV.xD = panelWidth - locationDiffD - widthD;
		}
		else if ((locationV.xD) < 0) {
			velocityV.xD *= -1;
			locationV.xD = 0;
		}
		
		// y axis
		//
		if ((locationV.yD + heightD) > panelHeight) {
			velocityV.yD *= -1;
			double locationDiffD = (locationV.yD + heightD) - panelHeight;
			locationV.yD = panelHeight - locationDiffD - heightD;
		}
		else if ((locationV.yD + heightD) < 0) {
			velocityV.yD *= -1;
			locationV.yD = 0;
		}	
		
	}


	@Override
	public Shape getShapeObject() {	
		return shapeGeometry;
	}


	@Override
	protected Point getShapeObjectCenterPoint() {		
		Double centerX_D = ((Ellipse2D.Double)shapeGeometry).getCenterX();
		Double centerY_D = ((Ellipse2D.Double)shapeGeometry).getCenterY();
		Point point = new Point();
		point.setLocation(centerX_D, centerY_D);
		return point;
	}


	@Override
	public void updateCenterVector() {
		this.centerV = new Vector( getShapeObjectCenterPoint() );		
	}


}






