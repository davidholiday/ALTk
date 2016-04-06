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
 * 
 * @author snerd
 *
 */
public class ElementPanel 
	extends JPanel implements MouseMotionListener, MouseListener {
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(ElementPanel.class.getName());
	
	protected Element[] elementARR;
	protected boolean mouseInFrameB = false;
	protected Vector mousePressPositionV; 
	

	public ElementPanel(Element[] shapeARR) {
		this.elementARR = shapeARR;
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(GUI.charcoalC);
	}
	
		
	
//	public void paintComponent(Graphics g) { super.paintComponent(g); }

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		for (Element element : elementARR) {
			element.renderPresentation(g, element);						
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

		Element element = elementARR[elementARR.length -1];		
		//if (!element.getClass().getName().contains("GravityBall")) return;
		
		
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