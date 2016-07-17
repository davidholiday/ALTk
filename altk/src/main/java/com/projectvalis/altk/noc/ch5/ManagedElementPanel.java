package com.projectvalis.altk.noc.ch5;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JPanel;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.util.Jbox2dUtils;


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
	
   
    protected List<ManagedElementPair> m_managedPairList;
	protected boolean[] m_keyFlagsARR;
	protected boolean m_mouseInFrame = false;
	protected boolean m_mousePressed = false;
	protected boolean m_mouseClicked = false;
	protected Vec2 m_mousePressPixelPositionVector; 
	protected Vec2 m_mousePressBox2dPositionVector;


	public ManagedElementPanel(List<ManagedElementPair> managedPairList) {
		m_managedPairList = managedPairList;
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(GUI.charcoalC);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);				
		Graphics2D g2d = (Graphics2D)g;	
		Dimension screenSize = this.getSize();

		Vec2 screenSizeVector = 
				new Vec2(screenSize.width, screenSize.height);
		
		int scaleFactor = Jbox2dUtils.NUM_PIXELS_TO_METER;
		
		synchronized(m_managedPairList) {
			
			for (ManagedElementPair pair : m_managedPairList) {
				
				try {
					pair.getLeft().getBody().getPosition();
				} catch (Exception e) {
					System.out.println(e.toString());
					System.exit(0);
				}
				
			}
			
	        List<Vec2> currentPositionsList = 
	            	m_managedPairList.stream()
	                                 .parallel()
	                                 .map(ManagedElementPair::getLeft)
	                                 .map(ManagedElementModel::getBody)
	                                 .map(Body::getPosition)
	                                 .map(x -> 
	                                     Jbox2dUtils.box2dToPixelCoordinate(
	                                		x, screenSizeVector))
	                                 .collect(Collectors.toList());
	                                 
	        
	        IntStream
	            .range(0, currentPositionsList.size())
	            .forEach(i -> 
	                m_managedPairList.get(i)
	                                 .getRight()
	                                 .renderPresentation(
	                            	     g2d, 
	                                     currentPositionsList.get(i), 
	                                     m_managedPairList.get(i)
	                                                      .getLeft()
	                                                      .m_jboxSizeVector
	                                                      .mul(scaleFactor)));	
	
		}
		

	}
		
	
	public boolean isMouseInFrame() {
		return m_mouseInFrame;
	}
	


	@Override
	public void mouseClicked(MouseEvent e) {
		m_mouseClicked = true;

	}



	@Override
	public void mousePressed(MouseEvent e) {	
        m_mousePressed = true;
		Point p = this.getMousePosition();
		
		m_mousePressPixelPositionVector = 
				new Vec2((float)p.getX(), (float)p.getY());
				
		Dimension screenSize = this.getSize();
		
		Vec2 screenSizeVector = 
				new Vec2(screenSize.width, screenSize.height);
			
		m_mousePressBox2dPositionVector = 
				Jbox2dUtils.pixelToBox2dCoordinate(
						m_mousePressPixelPositionVector, screenSizeVector);
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		m_mousePressed = false;	
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		m_mouseInFrame = true;
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		m_mouseInFrame = false;
		
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