package com.projectvalis.altk.noc.ch1;

import java.awt.Point;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * simple euclidean vector object
 * @author snerd
 *
 */
public class Vector {

	private static final Logger LOGGER = 
			LoggerFactory.getLogger(Vector.class.getName());
	
	public double xD;
	public double yD;
	
	
	public Vector(double x, double y) {
		xD = x;
		yD = y;
	}
	
	
	public Vector(Point p) {
		xD = p.getX();
		yD = p.getY();
	}
	
	
	
	public void add(Vector v) {
		xD += v.xD;
		yD += v.yD;
	}
	
	
	public void subtract(Vector v) {
		xD -= v.xD;
		yD -= v.yD;
	}
	
	
	// tee hee
	public void multiply(double d) {
		xD *= d;
		yD *= d;
	}
	
	
	public void divide(double d) {
		xD /= d;
		yD /= d;
	}
	
	
	public double getMagnitude() {
		return Math.sqrt(xD*xD + yD*yD);
	}

	
	public void normalize() {
		Double magnitudeD = getMagnitude();
		
		if (magnitudeD != 0) {
			divide(magnitudeD);
		}
		
	}


	public Vector clone() {
		return new Vector(xD, yD);
	}
	

	
	
}











