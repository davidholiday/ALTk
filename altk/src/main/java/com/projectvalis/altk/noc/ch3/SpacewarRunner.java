package com.projectvalis.altk.noc.ch3;

import java.awt.Color;
import java.awt.Point;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.init.internalFrameDark;
import com.projectvalis.altk.noc.ch1.Element;
import com.projectvalis.altk.noc.ch1.ElementPanel;
import com.projectvalis.altk.noc.ch1.Vector;
import com.projectvalis.altk.noc.ch2.GravityBall;
import com.projectvalis.altk.util.TrigHelpers;


public class SpacewarRunner extends internalFrameDark {

	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(SpacewarRunner.class.getName());
	
	private Element[] elementARR = new Element[1];
	private SpacewarPanel spacewarPanel;
	private Element ussTriangleE;
	
	

	/**
	 * constructor bootstraps the whole demo
	 */
	public SpacewarRunner() {
		this.setLocation(800, 200);		
		
		Vector ussTriangleLocationVector = 
				new Vector(this.getWidth() / 2, this.getHeight() / 2);
		
		ussTriangleE = new UssTriangle(ussTriangleLocationVector, 
									   new Vector(0, 0), 
									   new Vector(0, 0), 
									   GUI.redC, 
									   GUI.charcoalC, 
									   50, 
									   50,
									   50);	
		
		elementARR[0] = ussTriangleE;	
		spacewarPanel = new SpacewarPanel(elementARR);
		
		this.add(spacewarPanel);
		this.attach(true);		
		animate();
	}

	
	private void animate() {

		
		boolean keepOnTrucknB = true;

		while (keepOnTrucknB) {					
			int panelWidthI = this.getWidth();
			int panelHeightI = this.getHeight();				
			
			// go through ball array and update their positions 
			try {	
				checkInputFlags();
				elementARR[0].update(this.getWidth(), this.getHeight());
				repaint();
				Thread.sleep(10);
				
				// ensure the window is still open
				keepOnTrucknB = (this.isClosed) ? (false) : (true);
				
			} catch (InterruptedException e) {
				LOGGER.error("EXITING ON ERROR! ");
				e.printStackTrace();
				keepOnTrucknB = false;
			}
			
		}
		
	}
	
	
	private void checkInputFlags() {
		
		if (spacewarPanel.keyFlagsARR[0]) {
			elementARR[0].angularAccelerationD -= 3;
		}
		
		if (spacewarPanel.keyFlagsARR[1]) {
			double thetaD = elementARR[0].headingD;
			double radiusD = 0.03;
			
			Vector newAccelerationVector = 
					TrigHelpers.PolarToVector(thetaD, radiusD);
			
			elementARR[0].accelerationV = newAccelerationVector;
		}
		
		if (spacewarPanel.keyFlagsARR[2]) {
			elementARR[0].angularAccelerationD += 3;
		}
		
		if (spacewarPanel.keyFlagsARR[3]) {
			
		}
		
		if (spacewarPanel.keyFlagsARR[4]) {
			
		}
	}
	
	
	
}
