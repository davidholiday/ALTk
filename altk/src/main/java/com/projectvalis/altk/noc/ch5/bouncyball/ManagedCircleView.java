package com.projectvalis.altk.noc.ch5.bouncyball;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import org.jbox2d.common.Vec2;

import com.projectvalis.altk.noc.ch5.ManagedElementView;
import com.projectvalis.altk.util.Pair;


/**
 * handles the rendering of a managed circle
 * 
 * @author snerd
 * 
 *
 */
public class ManagedCircleView extends ManagedElementView {

	ManagedCircleView(int strokeWidth, Color strokeColor, Color fillColor) {
		super(strokeWidth, strokeColor, fillColor);
	}

	@Override
	protected void renderPresentation(
			Graphics2D g2, Vec2 posVector, Vec2 sizeVector) {
		
		// java draws from the upper left corner. here we are 
		// ensuring the center of the shape is placed at the desired x/y coord
		float ballLocationX = posVector.x - (sizeVector.x / 2);
		float ballLocationY = posVector.y - (sizeVector.y / 2);		
		
		Ellipse2D ballE2D = 
			new Ellipse2D.Float(ballLocationX, 
				                ballLocationY,
				                sizeVector.x,
				                sizeVector.y);
		
		g2.setColor(m_fillColor);
		g2.fill(ballE2D);
		g2.setColor(m_strokeColor);
		g2.setStroke(m_stroke);
		g2.draw(ballE2D);	

	}


}
