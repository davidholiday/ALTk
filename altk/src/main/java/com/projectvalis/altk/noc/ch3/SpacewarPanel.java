package com.projectvalis.altk.noc.ch3;

import java.awt.event.KeyEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.noc.ch1.Element;
import com.projectvalis.altk.noc.ch1.ElementPanel;

public class SpacewarPanel extends ElementPanel {

	private static final Logger LOGGER = 
			LoggerFactory.getLogger(SpacewarPanel.class.getName());
	
	public SpacewarPanel(Element[] elementARR) {
		super(elementARR);
		this.addKeyListener(this);
		this.setFocusable(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
		// left
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {			
			elementARR[0].angularAccelerationD -= 10;
		}
	
		
		// thrust (up)
		if (e.getKeyCode() == KeyEvent.VK_UP) {
		}		
		
		
		// right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			elementARR[0].angularAccelerationD += 10;
		}
		

		
		
	}

	
}
