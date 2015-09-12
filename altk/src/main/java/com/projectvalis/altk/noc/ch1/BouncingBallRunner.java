package com.projectvalis.altk.noc.ch1;

import java.awt.BorderLayout;
import java.util.logging.Logger;

import com.projectvalis.altk.init.internalFrameDark;


public class BouncingBallRunner extends internalFrameDark {

	// setup logger 
	private static final Logger LOGGER = 
			Logger.getLogger(BouncingBallRunner.class.getName());
	
	public void makeItSo() {
		setLocation(800, 200);
		
		Vector locationV = new Vector(100, 100);
		Vector velocityV = new Vector(0, 0);
		
		add(new BouncingBallPanel(locationV, velocityV));
		
		attach(true);
	}
	
	
	
	
}
