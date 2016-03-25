package com.projectvalis.altk.noc.ch3;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import com.projectvalis.altk.noc.ch1.Element;
import com.projectvalis.altk.noc.ch1.Vector;

public class Square extends Element {

	@Override
	protected void checkEdges(int panelWidth, int panelHeight) { /* noop */ }

	@Override
	protected void renderPresentation(Graphics g, Element element) {
		double squareLocationX_D = element.getLocation().getLeft();
		double squareLocationY_D = element.getLocation().getRight();
				
		Rectangle2D squareR2D = new Rectangle2D.Double(squareLocationX_D, 
													   squareLocationY_D,
													   element.widthD,
													   element.heightD);
				
		// update center point variable
		element.centerV = 
				new Vector(squareR2D.getCenterX(), squareR2D.getCenterY());
		
		Graphics2D g2 = (Graphics2D)g;	
		
		// treat the shape as a path object so we can easily rotate it
	    Path2D.Double path = new Path2D.Double();
	    path.append(squareR2D, false);
	    AffineTransform affineTransform = new AffineTransform();
	   
	    affineTransform.rotate(Math.toRadians(angleD), 
	    					   squareR2D.getCenterX(), 
	    					   squareR2D.getCenterY());
	    
	    path.transform(affineTransform);
	    
	    // render the filled in image
		g2.setColor(element.fillColorC);
		g2.fill(path);
		
		// render the outline of the image
		g2.setColor(element.strokeColorC);
		g2.setStroke(new BasicStroke(2));
	    g2.draw(path);				 		
	}

}
