package com.projectvalis.altk.noc.ch5.asteroids;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import org.jbox2d.common.Vec2;

import com.projectvalis.altk.noc.ch5.ManagedElementView;

public class ManagedAsteroidView extends ManagedElementView {

	public ManagedAsteroidView(int strokeWidth, 
							   Color strokeColor,
							   Color fillColor) {
		
		super(strokeWidth, strokeColor, fillColor);
	}
	
	
	@Override
	protected void renderPresentation(
			Graphics2D g2, Vec2 posVector, Vec2 sizeVector, float heading) {
		
		// java draws from the upper left corner. here we are 
		// ensuring the center of the shape is placed at the desired x/y coord
		float ballLocationX = posVector.x - (sizeVector.x / 2);
		float ballLocationY = posVector.y - (sizeVector.y / 2);			

		Rectangle2D squareR2D = new Rectangle2D.Double(ballLocationX, 
													   ballLocationY,
													   sizeVector.x,
													   sizeVector.y);
		
		// treat the shape as a path object so we can easily rotate it
	    Path2D.Double squareAsPath = new Path2D.Double();
	    squareAsPath.append(squareR2D, false);
	    AffineTransform affineTransform = new AffineTransform();
	   
	    affineTransform.rotate(Math.toRadians(heading), 
	    					   squareR2D.getCenterX(), 
	    					   squareR2D.getCenterY());
	    
	    squareAsPath.transform(affineTransform);
	    
		g2.setColor(m_fillColor);
		g2.fill(squareR2D);
		g2.setColor(m_strokeColor);
		g2.setStroke(m_stroke);
		g2.draw(squareR2D);	
	}

}



