package com.projectvalis.altk.noc.ch1;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class BallPanel extends ElementPanel {

	
	
	public BallPanel(Element[] shapeARR) {
		super(shapeARR);
	}

	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < elementARR.length; i ++) {
			Element ball = elementARR[i];
			
			double ballLocationX_D = ball.getLocation().getLeft();
			double ballLocationY_D = ball.getLocation().getRight();
	
			Ellipse2D ballE2D = new Ellipse2D.Double(ballLocationX_D, 
													 ballLocationY_D,
													 ball.widthD,
													 ball.heightD);
			
			// update center point variable
			ball.centerV = 
					new Vector(ballE2D.getCenterX(), ballE2D.getCenterY());
			
			Graphics2D g2 = (Graphics2D)g;
			g2.setColor(ball.strokeColorC);
			g2.setStroke(new BasicStroke(4));
			g2.draw(ballE2D);
			g2.setColor(ball.fillColorC);
			g2.fill(ballE2D);						
		}
		
		
		
	}
	
}
