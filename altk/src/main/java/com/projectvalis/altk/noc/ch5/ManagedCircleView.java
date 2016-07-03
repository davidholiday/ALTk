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
 */
public class ManagedCircleView extends ManagedElementView {

	@Override
	protected void renderPresentation(
			Graphics2D g2, Vec2 posVector, Pair<Float, Float> screenSize) {
		
		float ballLocationX = posVector.x;
		float ballLocationY = posVector.y;
		
		float screenWidth = (float)screenSize.getLeft();
		float screenHeight = (float)screenSize.getRight();		
		
		Ellipse2D ballE2D = 
			new Ellipse2D.Float(ballLocationX, 
				                ballLocationY,
				                screenWidth,
				                screenHeight);
		
		g2.setColor(this.fillColorC);
		g2.fill(ballE2D);
		g2.setColor(this.strokeColorC);
		g2.setStroke(this.stroke);
		g2.draw(ballE2D);		
	}

}
