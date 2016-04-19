package com.projectvalis.altk.noc.ch3;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import com.projectvalis.altk.noc.ch1.Element;
import com.projectvalis.altk.noc.ch1.Vector;

public class Square extends Element {

	@Override
	protected void checkEdges(int panelWidth, int panelHeight) { /* noop */ }

	@Override
	protected void renderPresentation(Graphics2D g2) {
		double squareLocationX_D = this.getLocation().getLeft();
		double squareLocationY_D = this.getLocation().getRight();
				
		Rectangle2D squareR2D = new Rectangle2D.Double(squareLocationX_D, 
													   squareLocationY_D,
													   this.widthD,
													   this.heightD);
				
		// update center point variable
		this.centerV = 
				new Vector(squareR2D.getCenterX(), squareR2D.getCenterY());
				
		// treat the shape as a path object so we can easily rotate it
	    Path2D.Double squareAsPath = new Path2D.Double();
	    squareAsPath.append(squareR2D, false);
	    AffineTransform affineTransform = new AffineTransform();
	   
	    affineTransform.rotate(Math.toRadians(headingD), 
	    					   squareR2D.getCenterX(), 
	    					   squareR2D.getCenterY());
	    
	    squareAsPath.transform(affineTransform);
	    
	    // render the filled in image
		g2.setColor(this.fillColorC);
		g2.fill(squareAsPath);
		
		// render the outline of the image
		g2.setColor(this.strokeColorC);
		g2.setStroke(new BasicStroke(2));

		g2.draw(squareAsPath);
		//return squareAsPath;
	}

}
