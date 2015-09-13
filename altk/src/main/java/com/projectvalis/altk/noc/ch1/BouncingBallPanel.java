package com.projectvalis.altk.noc.ch1;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import com.projectvalis.altk.init.GUI;


/**
 * the 'view' for the vector bouncing ball example in ch1 of nature of code
 * 
 * @author snerd
 *
 */
public class BouncingBallPanel extends JPanel {
	
	private BouncingBall bouncingBall;

	

	public BouncingBallPanel(BouncingBall bouncingBall) {
		this.bouncingBall = bouncingBall;
		setBackground(GUI.charcoalC);
	}
	
		
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		double ballLocationX_D = bouncingBall.getLocation().getLeft();
		double ballLocationY_D = bouncingBall.getLocation().getRight();
		
		Ellipse2D ballE2D = new Ellipse2D.Double(ballLocationX_D, 
												 ballLocationY_D,
												 bouncingBall.widthD,
											     bouncingBall.heightD);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(bouncingBall.strokeColorC);
		g2.setStroke(new BasicStroke(4));
		g2.draw(ballE2D);
		g2.setColor(bouncingBall.fillColorC);
		g2.fill(ballE2D);	
	}
	


}