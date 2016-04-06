package com.projectvalis.altk.noc.ch3;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.noc.ch1.Element;


public class UssTriangle extends Element {
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(UssTriangle.class.getName());
	
	@Override
	protected void checkEdges(int panelWidth, int panelHeight) { /* noop */ }

	@Override
	protected void renderPresentation(Graphics g, Element element) {
		double elementLocationX_D = element.getLocation().getLeft();
		double elementLocationY_D = element.getLocation().getRight();
				
		
		Graphics2D g2 = (Graphics2D)g;	
		
		// treat the shape as a path object so we can easily rotate it
		int x2Points[] = {65, 55, 65, 75};
		int y2Points[] = {182, 217, 207, 217};
		
		GeneralPath ussTriangleGP = new GeneralPath(
				GeneralPath.WIND_EVEN_ODD, x2Points.length);
		
		ussTriangleGP.moveTo(x2Points[0], y2Points[0]);
		
		for (int i = 1; i < x2Points.length; i ++) {
			ussTriangleGP.lineTo(x2Points[i], y2Points[i]);
		}
		
		ussTriangleGP.closePath();

//		g2.setColor(element.fillColorC);
//		g2.fill(ussTriangleGP);
		
		// render the outline of the image
		g2.setColor(element.strokeColorC);
		g2.setStroke(new BasicStroke(2));
		
		g2.draw(ussTriangleGP);

//	    AffineTransform affineTransform = new AffineTransform();
//	   
//	    affineTransform.rotate(Math.toRadians(angleD), 
//	    					   squareR2D.getCenterX(), 
//	    					   squareR2D.getCenterY());
//	    
//	    path.transform(affineTransform);
//	    
//	    // render the filled in image
//		g2.setColor(element.fillColorC);
//		g2.fill(path);
//		
//		// render the outline of the image
//		g2.setColor(element.strokeColorC);
//		g2.setStroke(new BasicStroke(2));
//	    g2.draw(path);				 		
	}
	
}
