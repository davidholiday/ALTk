package com.projectvalis.altk.noc.ch5;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import org.jbox2d.common.Vec2;

import com.projectvalis.altk.util.Pair;


/**
 * handles the rendering of a managed circle
 * 
 * @author snerd
 * 
 * TODO update to draw from center of object not upper left corner!
 *
 */
public class ManagedCircleView extends ManagedElementView {

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
		
		g2.setColor(this.fillColorC);
		g2.fill(ballE2D);
		g2.setColor(this.strokeColorC);
		g2.setStroke(this.stroke);
		g2.draw(ballE2D);	

	}


}
