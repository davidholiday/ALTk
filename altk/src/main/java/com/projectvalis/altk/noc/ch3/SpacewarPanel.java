package com.projectvalis.altk.noc.ch3;

import java.awt.event.KeyEvent;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.noc.ch1.Element;
import com.projectvalis.altk.noc.ch1.ElementPanel;

public class SpacewarPanel extends ElementPanel {

	private static final Logger LOGGER = 
			LoggerFactory.getLogger(SpacewarPanel.class.getName());
	
	public SpacewarPanel(List<Element> elementL) {
		super(elementL);
		this.keyFlagsARR = new boolean[5];
		this.addKeyListener(this);
		this.setFocusable(true);
	}


	// keyFlags = {left, up, right, space}
	//
	@Override
	public void keyPressed(KeyEvent e) {
	
		// left
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {	
			this.keyFlagsARR[0] = true;
		}
	
		
		// thrust (up)
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			this.keyFlagsARR[1] = true;
		}		
		
		
		// right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.keyFlagsARR[2] = true;
		}		
		
	}

	
	public void keyReleased(KeyEvent e) {
		
		// left
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {	
			this.keyFlagsARR[0] = false;
		}
	
		
		// thrust (up)
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			this.keyFlagsARR[1] = false;
		}		
		
		
		// right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.keyFlagsARR[2] = false;
		}	
		
	}
	
	
	
}
