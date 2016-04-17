package com.projectvalis.altk.noc.ch1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.init.GUI;


/**
 * base panel for handling the rendering of elements and the capture of 
 * user input. 
 * 
 * @author snerd
 *
 */
public class ElementPanel 
	extends JPanel implements MouseMotionListener, MouseListener, KeyListener {
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(ElementPanel.class.getName());
	
	protected List<Element> elementL;

	public boolean[] keyFlagsARR;
	protected boolean mouseInFrameB = false;
	protected Vector mousePressPositionV; 
	

	public ElementPanel(List<Element> elementL) {
		this.elementL = elementL;
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(GUI.charcoalC);
	}
	
		
	public void setElementList(List<Element> elementL) {
		this.elementL = elementL;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	

		Graphics2D g2 = (Graphics2D)g;
		List<Element> localL = new ArrayList<Element>();
		localL.addAll(elementL);
		
		// doing it this way 
		// seems to remove the ability to add things to the element list
		// after the fact. 
		localL.stream()
			    .map(x -> x.renderPresentation(g2))
			    .forEach(g2::draw);
		
		//g2.dispose();
		
	}
	
	
//	public void paintElements(List<Shape> renderList) {
//		Graphics2D g2 = (Graphics2D)this.getGraphics();
//		super.paintComponent(this.getGraphics());
//		
//		renderList.parallelStream()
//		          .forEach(g2 :: draw);
//		
//		//this.repaint();
//	}
	
	
	
	
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
		
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	


}