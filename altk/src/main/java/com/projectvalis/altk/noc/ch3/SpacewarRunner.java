package com.projectvalis.altk.noc.ch3;

import java.awt.Color;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.init.internalFrameDark;
import com.projectvalis.altk.noc.ch1.Element;
import com.projectvalis.altk.noc.ch1.ElementPanel;


public class SpacewarRunner extends internalFrameDark {

	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(SpacewarRunner.class.getName());
	
	private Element[] elementARR = new Element[1];
	private SpacewarPanel spacewarPanel;
	private Element ussTriangleE = new UssTriangle();
	
	

	/**
	 * constructor bootstraps the whole demo
	 */
	public SpacewarRunner() {
		this.setLocation(800, 200);	
			
		Color[] colorArr = new Color[5];
		colorArr[0] = GUI.mustardC;
		colorArr[1] = GUI.orangeC;
		colorArr[2] = GUI.purpleC;
		colorArr[3] = GUI.redC;
		colorArr[4] = GUI.tealC;
	
		elementARR[0] = ussTriangleE;
				
		spacewarPanel = new SpacewarPanel(elementARR);
		
		this.add(spacewarPanel);
		this.attach(true);		
		animate();
	}

	
	private void animate() {
		
		while(true) {
			elementARR[0].update(this.getWidth(), this.getHeight());
		}
		
	}
	
}
