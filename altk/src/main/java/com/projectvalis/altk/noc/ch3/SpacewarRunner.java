package com.projectvalis.altk.noc.ch3;

import java.awt.Color;
import java.awt.Point;
import java.beans.PropertyVetoException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
	
	private Element[] elementARR = new Element[6];
	private SpacewarPanel spacewarPanel;
	private Element ussTriangleE;
	
	

	/**
	 * constructor bootstraps the whole demo
	 */
	public SpacewarRunner() {
		this.setLocation(400, 200);
		this.setSize(1280, 720);
		
		Color[] colorArr = new Color[3];
		colorArr[0] = GUI.mustardC;
		colorArr[1] = GUI.orangeC;
		colorArr[2] = GUI.redC;
		
		Vector ussTriangleLocationVector = 
				new Vector(this.getWidth() / 2, this.getHeight() / 2);
		
		ussTriangleE = new UssTriangle(ussTriangleLocationVector, 
									   new Vector(0, 0), 
									   new Vector(0, 0), 
									   Color.black, 
									   GUI.tealC, 
									   50, 
									   50,
									   50);	
		
		elementARR[0] = ussTriangleE;	
		
		Random randy = new Random();
		for (int i = 1; i < elementARR.length; i ++) {
			int diameterI = ThreadLocalRandom.current().nextInt(25, 65 + 1);
			
			int colorIndexI = randy.nextInt(3);		
			Color fillColor = colorArr[colorIndexI];
			double locationX_D = randy.nextInt(this.getWidth());
			double locationY_D = randy.nextInt(this.getHeight());
			Vector locationV = new Vector(locationX_D, locationY_D);
						
			Element square = new AsteroidSquare(locationV, 
								   	   new Vector(0, 0), 
								   	   new Vector(0, 0), 
								   	   Color.black, 
								   	   fillColor, 
								   	   diameterI, 
								   	   diameterI,
								   	   diameterI);	
			
			int randomX_I = ThreadLocalRandom.current().nextInt(-2, 2 + 1);
			int randomY_I = ThreadLocalRandom.current().nextInt(-2, 2 + 1);
			square.velocityV = new Vector(randomX_I, randomY_I);
			square.angularAccelerationD = square.velocityV.xD;
			elementARR[i] = square;		
		}
		
		spacewarPanel = new SpacewarPanel(elementARR);
		
		this.add(spacewarPanel);
		this.attach(true);		
		animate();
	}	
	
	
	/**
	 * 
	 */
	private void animate() {

		
		boolean keepOnTrucknB = true;

		while (keepOnTrucknB) {					
			int panelWidthI = this.getWidth();
			int panelHeightI = this.getHeight();				
			
			// go through ball array and update their positions 
			try {	
				
				for (Element element : elementARR) {	
					checkInputFlags();
					element.update(panelWidthI, panelHeightI);					
				}
				
				repaint();
				Thread.sleep(10);

				// ensure the window is still open
				keepOnTrucknB = (this.isClosed) ? (false) : (true);	
				
			} catch (InterruptedException e) {
				LOGGER.error("EXITING ON ERROR! ", e);
				keepOnTrucknB = false;
			}
			
		}
		
	}
	
	
	private void checkInputFlags() {
		
		// port thruster
		if (spacewarPanel.keyFlagsARR[0]) {
			elementARR[0].angularAccelerationD -= 0.5;
		}
		
		// forward thruster
		if (spacewarPanel.keyFlagsARR[1]) {
			
			// convert heading to radians - which is what polar format 
			// expects. the (-90) is to account for the difference in 
			// orientation between heading and angle (eg -- heading 000 is a
			// 90 degree angle).
			double thetaD = Math.toRadians(elementARR[0].headingD - 90);
			double radiusD = 0.03;
			
			Vector newAccelerationVector = 
					TrigHelpers.PolarToVector(thetaD, radiusD);
			
			elementARR[0].accelerationV = newAccelerationVector;
		}
		
		// starboard thruster
		if (spacewarPanel.keyFlagsARR[2]) {
			elementARR[0].angularAccelerationD += 0.5;
		}
		
		if (spacewarPanel.keyFlagsARR[3]) {
			
		}
		
		if (spacewarPanel.keyFlagsARR[4]) {
			
		}
	}
	
	
	
}
