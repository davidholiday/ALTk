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
public class ElementPanel 
	extends JPanel implements MouseMotionListener, MouseListener {
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(ElementPanel.class.getName());
	
	private Element[] shapeARR;
	private boolean mouseInFrameB = false;
	private Vector mousePressPositionV; 
	

	public ElementPanel(Element[] shapeARR) {
		this.shapeARR = shapeARR;
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(GUI.charcoalC);
	}
	
		
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < shapeARR.length; i ++) {
			Element element = shapeARR[i];	
			
			double ballLocationX_D = element.getLocation().getLeft();
			double ballLocationY_D = element.getLocation().getRight();
			
			element.updateCenterVector();
			java.awt.Shape shapePresentation = element.getShapeObject();			
			Graphics2D g2 = (Graphics2D)g;
			g2.setColor(element.strokeColorC);
			g2.setStroke(new BasicStroke(4));
			g2.draw(shapePresentation);
			g2.setColor(element.fillColorC);
			g2.fill(shapePresentation);						
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

		Element element = shapeARR[shapeARR.length -1];		
		if (!element.getClass().getName().contains("GravityBall")) return;
		
		
		// if the ball color has changed, then we know the mouse is over
		// the ball at click-time
		if (element.fillColorC == GUI.orangeC) {
			GravityBall gravityBall = (GravityBall)element;

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