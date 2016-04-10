package com.projectvalis.altk.noc.ch3;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

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
		this.setLocation(800, 200);		
		
		Color[] colorArr = new Color[5];
		colorArr[0] = GUI.mustardC;
		colorArr[1] = GUI.orangeC;
		colorArr[2] = GUI.purpleC;
		colorArr[3] = GUI.redC;
		colorArr[4] = GUI.tealC;
		
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
		
		Random randy = new Random();
		for (int i = 1; i < elementARR.length; i ++) {
			int diameterI = randy.nextInt(65);
			
			int colorIndexI = randy.nextInt(5);		
			Color fillColor = colorArr[colorIndexI];
			double locationX_D = randy.nextInt(this.getWidth());
			double locationY_D = randy.nextInt(this.getHeight());
			Vector locationV = new Vector(locationX_D, locationY_D);
						
			Element square = new GravitySquare(locationV, 
								   	   new Vector(0, 0), 
								   	   new Vector(0, 0), 
								   	   Color.black, 
								   	   fillColor, 
								   	   diameterI, 
								   	   diameterI,
								   	   diameterI);		

			square.velocityV = new Vector(randy.nextInt(3), randy.nextInt(3));
			elementARR[i] = square;		
		}
		
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
				
				for (Element element : elementARR) {	
					checkInputFlags();
					element.update(panelWidthI, panelHeightI);
					repaint();
					Thread.sleep(10);

					// ensure the window is still open
					keepOnTrucknB = (this.isClosed) ? (false) : (true);								
				}
				
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
		
		if (spacewarPanel.keyFlagsARR[2]) {
			elementARR[0].angularAccelerationD += 10;
		}
		
		if (spacewarPanel.keyFlagsARR[3]) {
			
		}
		
		if (spacewarPanel.keyFlagsARR[4]) {
			
		}
	}
	
	
	
}
