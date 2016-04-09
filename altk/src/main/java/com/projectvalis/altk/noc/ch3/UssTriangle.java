package com.projectvalis.altk.noc.ch3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.noc.ch1.Element;
import com.projectvalis.altk.noc.ch1.Vector;


public class UssTriangle extends Element {
	

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
	public UssTriangle (Vector location, 
						Vector velocity, 
						Vector acceleration, 
						Color strokeColor, 
						Color fillColor, 
						double width,
						double height, 
						double mass) {
		
		this.locationV = location;
		this.velocityV = velocity;
		this.accelerationV = acceleration;
		this.strokeColorC = strokeColor;
		this.fillColorC = fillColor;
		this.widthD = width;
		this.heightD = height;
		this.massD = mass;
	}
	
	
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(UssTriangle.class.getName());
	
	
	@Override
	protected void checkEdges(int panelWidth, int panelHeight) { /* noop */ }

	@Override
	protected void renderPresentation(Graphics g, Element element) {						
		Graphics2D g2 = (Graphics2D)g;	
		
		// create ss triangle path at origin
		double x2Points[] = {0, -10, 0, 10};			
		double y2Points[] = {0, 35, 25, 35};
		
		GeneralPath ussTriangleGP = new GeneralPath(
				GeneralPath.WIND_EVEN_ODD, x2Points.length);
		
		ussTriangleGP.moveTo(x2Points[0], y2Points[0]);
		
		for (int i = 1; i < x2Points.length; i ++) {
			ussTriangleGP.lineTo(x2Points[i], y2Points[i]);
		}
		
		ussTriangleGP.closePath();
	
		// translate, scale, and rotate
		AffineTransform affineTransform = new AffineTransform();

		double elementLocationX_D = element.getLocation().getLeft();
		double elementLocationY_D = element.getLocation().getRight();		
		affineTransform.translate(elementLocationX_D, elementLocationY_D);

		affineTransform.scale(0.5, 0.5);
		
		double ussTriangleCenterX_D = ussTriangleGP.getBounds().getCenterX();
		double ussTriangleCenterY_D = ussTriangleGP.getBounds().getCenterY();		
	    affineTransform.rotate(Math.toRadians(angleD), 
	    		               ussTriangleCenterX_D, 
	    		               ussTriangleCenterY_D);		
	    
	    ussTriangleGP.transform(affineTransform);
		
		// render 
		g2.setColor(element.strokeColorC);
		g2.setStroke(new BasicStroke(1));		
		g2.draw(ussTriangleGP);
		g2.setColor(element.fillColorC);
		g2.fill(ussTriangleGP);
	 		
	}
	
}
