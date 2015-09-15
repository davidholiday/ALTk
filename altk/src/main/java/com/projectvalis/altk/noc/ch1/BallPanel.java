package com.projectvalis.altk.noc.ch1;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import com.projectvalis.altk.init.GUI;


/**
 * the 'view' for the vector bouncing ball example in ch1 of nature of code
 * 
 * @author snerd
 *
 */
public class BallPanel extends JPanel implements MouseListener {
	
	private Ball[] ballArr;
	private boolean mouseInFrameB = false;

	

	public BallPanel(Ball[] ballArr) {
		this.ballArr = ballArr;
		addMouseListener(this);
		setBackground(GUI.charcoalC);
	}
	
		
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < ballArr.length; i ++) {
			Ball ball = ballArr[i];
			
			double ballLocationX_D = ball.getLocation().getLeft();
			double ballLocationY_D = ball.getLocation().getRight();
			
			Ellipse2D ballE2D = new Ellipse2D.Double(ballLocationX_D, 
													 ballLocationY_D,
													 ball.widthD,
													 ball.heightD);
			
			Graphics2D g2 = (Graphics2D)g;
			g2.setColor(ball.strokeColorC);
			g2.setStroke(new BasicStroke(4));
			g2.draw(ballE2D);
			g2.setColor(ball.fillColorC);
			g2.fill(ballE2D);						
		}
		
	
	}

	
	public boolean isMouseInFrame() {
		return mouseInFrameB;
	}
	


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		mouseInFrameB = true;
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		mouseInFrameB = false;
		
	}
	


}