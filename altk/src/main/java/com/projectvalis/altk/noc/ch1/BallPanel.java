package com.projectvalis.altk.noc.ch1;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.noc.ch2.GravityBall;


/**
 * the 'view' for the vector bouncing ball example in ch1 of nature of code
 * 
 * @author snerd
 *
 */
public class BallPanel 
	extends JPanel implements MouseMotionListener, MouseListener {
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(BallPanel.class.getName());
	
	private Ball[] ballArr;
	private boolean mouseInFrameB = false;
	private Vector mousePressPositionV; 
	

	public BallPanel(Ball[] ballArr) {
		this.ballArr = ballArr;
		addMouseListener(this);
		addMouseMotionListener(this);
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

	
	public boolean isMouseInFrame() {
		return mouseInFrameB;
	}
	


	@Override
	public void mouseClicked(MouseEvent e) {

	}



	@Override
	public void mousePressed(MouseEvent e) {
		Point p = this.getMousePosition();
		mousePressPositionV = new Vector(p.getX(), p.getY());
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



	@Override
	public void mouseDragged(MouseEvent e) {

		Ball ball = ballArr[ballArr.length -1];		
		if (!ball.getClass().getName().contains("GravityBall")) return;
		
		
		// if the ball color has changed, then we know the mouse is over
		// the ball at click-time
		if (ball.fillColorC == GUI.orangeC) {
			GravityBall gravityBall = (GravityBall)ball;

			// get the vector betwixt the point at which the mouse was pressed
			// and the current mouse position
			Point p = this.getMousePosition();
			
			if (p == null) return;
			
			Vector currentMousePositionV = new Vector(p.getX(), p.getY());
			
			Vector newBallPositionV = currentMousePositionV.clone();
			newBallPositionV.subtract(mousePressPositionV);
			//newBallPositionV.normalize();
			//newBallPositionV.multiply(0.5);
			
			
			gravityBall.locationV.add(newBallPositionV);
			mousePressPositionV = currentMousePositionV;
			
		}		
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	


}