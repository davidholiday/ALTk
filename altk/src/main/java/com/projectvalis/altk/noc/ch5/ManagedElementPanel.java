package com.projectvalis.altk.noc.ch5;


import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import org.jbox2d.common.Vec2;
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
public class ManagedElementPanel 
	extends JPanel implements MouseMotionListener, MouseListener, KeyListener {
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(ManagedElementPanel.class.getName());
	

	public boolean[] keyFlagsARR;
	protected boolean mouseInFrameB = false;
	protected Vec2 mousePressPositionVector; 
	

	public ManagedElementPanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(GUI.charcoalC);
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
		mousePressPositionVector = new Vec2((float)p.getX(), (float)p.getY());
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