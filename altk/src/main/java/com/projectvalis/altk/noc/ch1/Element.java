package com.projectvalis.altk.noc.ch1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.util.Pair;


/**
 * base class for all shape objects
 * 
 * @author snerd
 *
 */
public abstract class Element {
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(Element.class.getName());
	
		
	protected Vector locationV = new Vector(10, 300);
	protected Vector velocityV = new Vector(0, 0);
	public Vector accelerationV = new Vector(0, 0);
	
	protected double angleD = 0;
	protected double angularVelocityD = 0;
	public double angularAccelerationD = 0;
	
	// populated only when drawn
	public Vector centerV;
	
	public Color strokeColorC = GUI.orangeC;
	public Color fillColorC = GUI.mustardC;
	
	public double widthD;
	public double heightD;
	public double massD;
	
	// ensure velocity doesn't get out of hand
	public double velocityLimitD = 8;
	

	
	// placeholder
	public Element() {}
	
	
	/**
	 * adds the provided vector to this object's location vector
	 * 
	 * @param updateLocationVector
	 */
	public void addToLocationVector(Vector updateLocationVector) {
		locationV.xD += updateLocationVector.xD;
		locationV.yD += updateLocationVector.yD;
	}
	

	/**
	 * returns location w/o granting access to this object's location
	 * vector. 
	 * 
	 * @return
	 * 		Pair(x, y)
	 */
	public Pair<Double, Double> getLocation() {
		return new Pair<Double, Double>(locationV.xD, locationV.yD);
	}
	
	
	
	/**
	 * returns velocity w/o granting access to this object's velocity
	 * vector
	 * 
	 * @return
	 * 		Pair(x, y)
	 */
	public Pair<Double, Double> getVelocity() {
		return new Pair<Double, Double>(velocityV.xD, velocityV.yD);
	}
	
	
	
	
	/**
	 * call this after you manipulate the acceleration vector directly. 
	 */
	public void update(int panelWidth, int panelHeight) {
		
		// apply the acceleration vector to the object
		velocityV.add(accelerationV);
		applyVelocityLimit();
		locationV.add(velocityV);
		
		// handle case where ball reaches window edge
		checkEdges(panelWidth, panelHeight);

		// update angular rotation
		angularAccelerationD += accelerationV.xD;
		angularVelocityD += angularAccelerationD;
		angleD += angularVelocityD;	

		// reset the acceleration vectors to zero because we're recalculating 
		// it every time step
		accelerationV.multiply(0);	
		angularAccelerationD *=0;
		
	}
	
	
	/**
	 * adds a force vector to the acceleration vector. remember - "a force is a
	 * vector that causes an object with mass to accelerate"
	 * @param force
	 */
	public void applyForce(Vector force) {
		Vector forceLocalV = force.clone();		
		forceLocalV.divide(massD);
		accelerationV.add(forceLocalV);	
	}

	
	/**
	 * ensures velocity does not exceed limit
	 */
	protected void applyVelocityLimit() {
		
		if (Math.abs(velocityV.xD) > velocityLimitD) {
			velocityV.xD = velocityLimitD * Math.signum(velocityV.xD);
		}
		
		if (Math.abs(velocityV.yD) > velocityLimitD) {
			velocityV.yD = velocityLimitD * Math.signum(velocityV.yD);
		}
		
	}
	
	
	/**
	 * makes the shape appear to bounce when it reaches a window edge
	 * @param panelWidth
	 * @param panelHeight
	 */
	protected abstract void checkEdges(int panelWidth, int panelHeight);
	
	
	protected abstract void renderPresentation(Graphics g, Element element);

	
}







