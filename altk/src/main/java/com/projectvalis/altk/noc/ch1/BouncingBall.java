package com.projectvalis.altk.noc.ch1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
	

	public BouncingBall() {
		this.massD = widthD;
		this.heightD = widthD;		
		this.widthD = 50;
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
	protected Shape renderPresentation(Graphics g) {
		
		double ballLocationX_D = this.getLocation().getLeft();
		double ballLocationY_D = this.getLocation().getRight();
				
		Ellipse2D ballE2D = new Ellipse2D.Double(ballLocationX_D, 
				ballLocationY_D,
				this.widthD,
				this.heightD);

		// update center point variable
		this.centerV = 
				new Vector(ballE2D.getCenterX(), ballE2D.getCenterY());

		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(this.fillColorC);
		g2.fill(ballE2D);
		
		g2.setColor(this.strokeColorC);
		g2.setStroke(new BasicStroke(4));

		return ballE2D;
	}



}






