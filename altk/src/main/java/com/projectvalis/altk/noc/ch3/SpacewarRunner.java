package com.projectvalis.altk.noc.ch3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.init.internalFrameDark;
import com.projectvalis.altk.noc.ch1.Element;
import com.projectvalis.altk.noc.ch1.ElementPanel;
import com.projectvalis.altk.noc.ch1.Vector;
import com.projectvalis.altk.noc.ch2.GravityBall;
import com.projectvalis.altk.noc.ch4.ParticleAbstract;
import com.projectvalis.altk.noc.ch4.ParticleCircle;
import com.projectvalis.altk.util.TrigHelpers;


public class SpacewarRunner extends internalFrameDark {

	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(SpacewarRunner.class.getName());
	
	private List<Element> elementL = 
			Collections.synchronizedList(new ArrayList<Element>());

	private List<ParticleAbstract> particleL = 
			new ArrayList<ParticleAbstract>();
	
	private List<Color> colorL = new ArrayList<Color>();
	private SpacewarPanel spacewarPanel;
	private Element ussTriangleE;
	
	

	/**
	 * constructor bootstraps the whole demo
	 */
	public SpacewarRunner() {
		this.setLocation(400, 200);
		this.setSize(1280, 720);
		
		//List<Element> elementL = new ArrayList<Element>();
		
		colorL.add(GUI.mustardC);
		colorL.add(Color.ORANGE);
		//colorL.add(GUI.redC);
		//colorL.add(Color.yellow);
		//colorL.add(GUI.charcoalLightC);
		//colorL.add(GUI.purpleC);
		//colorL.add(Color.ORANGE);
		
		Vector ussTriangleLocationVector = 
				new Vector(this.getWidth() / 2, this.getHeight() / 2);
		
		ussTriangleE = new UssTriangle(ussTriangleLocationVector, 
									   new Vector(0, 0), 
									   new Vector(0, 0), 
									   Color.black, 
									   Color.GREEN, 
									   50, 
									   50,
									   50);	
		
		elementL.add(ussTriangleE);	
		
		Random randy = new Random();
		int colorIndexI = 0;
		
		for (int i = 1; i < 6; i ++) {
			int diameterI = ThreadLocalRandom.current().nextInt(25, 65 + 1);	
			//Color fillColor = colorL.get(colorIndexI);
			Color fillColor = GUI.redC;
			colorIndexI = (colorIndexI == 2) ? (0) : (colorIndexI + 1);
			double locationX_D = randy.nextInt(this.getWidth());
			double locationY_D = randy.nextInt(this.getHeight());
			Vector locationV = new Vector(locationX_D, locationY_D);
						
			Element square_E = new AsteroidSquare(locationV, 
								   	   new Vector(0, 0), 
								   	   new Vector(0, 0), 
								   	   Color.black, 
								   	   fillColor, 
								   	   diameterI, 
								   	   diameterI,
								   	   diameterI);	
			
			int randomX_I = ThreadLocalRandom.current().nextInt(-2, 2 + 1);
			int randomY_I = ThreadLocalRandom.current().nextInt(-2, 2 + 1);	
			randomX_I = (randomX_I == 0) ? (1) : (randomX_I);
			randomY_I = (randomY_I == 0) ? (1) : (randomY_I);
			
			square_E.velocityV = new Vector(randomX_I, randomY_I);
			square_E.angularAccelerationD = square_E.velocityV.xD;
			elementL.add(square_E);		
		}
		
		spacewarPanel = new SpacewarPanel(elementL);
		this.add(spacewarPanel);
		this.attach(true);		
		this.setTitle("SpaceWar!");
		animate();
	}	
	
	

	/**
	 * cycles through the element list and updates the scene
	 */
	private void animate() {
		boolean keepOnTrucknB = true;

		while (keepOnTrucknB) {		
			//List<Element> elementL = spacewarPanel.getElementListCopy();
			int panelWidthI = this.getWidth();
			int panelHeightI = this.getHeight();				
			
			// update scene 
			try {	
				
				checkInputFlags(elementL);
				elementL.addAll(particleL);
				
				for (Element element : elementL) {			
					element.update(panelWidthI, panelHeightI);					
				}
				
				// check for dead particles
				//
				List<Element> deadParticlesL = 
					elementL.parallelStream()
				            .filter(e -> e.getClass()
				            		      .getName()
				            		      .contains("Particle"))
				            .filter(e -> 
				                ((ParticleAbstract)e).getIsAliveFlag() == false)
				            .collect(Collectors.toList());
						  
				// filter out the dead ones
				//
				deadParticlesL.parallelStream()
				              .forEach(p -> elementL.remove(p));
				
				// paint and nappie-poo				
				//spacewarPanel.setElementList(elementL);
				particleL.clear();
				this.repaint();						
				Thread.sleep(10);

				// ensure the window is still open
				keepOnTrucknB = (this.isClosed) ? (false) : (true);	
				
			} catch (InterruptedException e) {
				LOGGER.error("EXITING ON ERROR! ", e);
				keepOnTrucknB = false;
			}
			
		}
		
	}
	
	
	/**
	 * checks the input flags from the JPanel handling the 'view' stuff and 
	 * handles the 'controller' logic. 
	 * 
	 * @param elementL
	 */
	private void checkInputFlags(List<Element> elementL) {
		
		Element ussTriangle_E = elementL.get(0);
		
		// port thruster
		if (spacewarPanel.keyFlagsARR[0]) {
			ussTriangle_E.angularAccelerationD -= 3;
		}
		
		// forward thruster
		if (spacewarPanel.keyFlagsARR[1]) {
			
			// convert heading to radians - which is what polar format 
			// expects. the (-90) is to account for the difference in 
			// orientation between heading and angle (eg -- heading 000 is a
			// 90 degree angle).
			double thetaD = Math.toRadians(ussTriangle_E.headingD - 90);
			double radiusD = 0.03;
			
			Vector newAccelerationVector = 
					TrigHelpers.PolarToVector(thetaD, radiusD);
			
			ussTriangle_E.accelerationV = newAccelerationVector;
			
			// add some thruster exhaust
			for (int i = 0; i < 1; i ++) {
				int diameterI = ThreadLocalRandom.current().nextInt(2, 5);
				
				int colorIndexI = 
						ThreadLocalRandom.current().nextInt(0, colorL.size());
				
				Color fillColor = colorL.get(colorIndexI);
				
				double locationX_D = ussTriangle_E.getLocation().getLeft();
				double locationY_D = ussTriangle_E.getLocation().getRight();
				Vector shipLocationV = new Vector(locationX_D, locationY_D);
				
				// take the current acceleration vector for the ship, normalize
				// it, set a new magnitude, then invert so it points in the 
				// opposite direction 
				Vector particleVelocityVector = newAccelerationVector.clone();
				particleVelocityVector.normalize();
				particleVelocityVector.multiply(-1);	

				Vector particleSpoutLocationOffsetV = 
						particleVelocityVector.clone();

				double newMagnitudeD = 
						ThreadLocalRandom.current().nextInt(1, 5);
				
				particleVelocityVector.multiply(newMagnitudeD);
				
				// offset the spout location down a few units then use the
				// particle velocity unit vector to ensure the spout is always
				// placed at the ass-end of the ship
				Vector particleSpoutLocationV = shipLocationV.clone();				
				particleSpoutLocationV.yD += 12;	
				particleSpoutLocationOffsetV.multiply(8);	
				particleSpoutLocationV.add(particleSpoutLocationOffsetV);	
		
				ParticleCircle particleCircle = 
						new ParticleCircle(particleSpoutLocationV, 
									   	   particleVelocityVector, 
									   	   new Vector(0, 0), 
									   	   Color.black, 
									   	   fillColor, 
									   	   diameterI, 
									   	   diameterI,
									   	   diameterI, 
									   	   40);		

				particleL.add(particleCircle);		
			}
			
		}
		
		// starboard thruster
		if (spacewarPanel.keyFlagsARR[2]) {
			ussTriangle_E.angularAccelerationD += 3;
		}
		
		if (spacewarPanel.keyFlagsARR[3]) {
			
		}
		
		if (spacewarPanel.keyFlagsARR[4]) {
			int diameterI = ThreadLocalRandom.current().nextInt(25, 65 + 1);			
			double locationX_D = 100;
			double locationY_D = 100;
			Vector locationV = new Vector(locationX_D, locationY_D);
						
			Element square_E = new AsteroidSquare(locationV, 
								   	   new Vector(0, 0), 
								   	   new Vector(0, 0), 
								   	   Color.black, 
								   	   Color.GREEN, 
								   	   diameterI, 
								   	   diameterI,
								   	   diameterI);	
			
			int randomX_I = ThreadLocalRandom.current().nextInt(-2, 2 + 1);
			int randomY_I = ThreadLocalRandom.current().nextInt(-2, 2 + 1);
			square_E.velocityV = new Vector(randomX_I, randomY_I);
			square_E.angularAccelerationD = square_E.velocityV.xD;
			elementL.add(square_E);	
		}
	}
	
	
	
}
