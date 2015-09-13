package com.projectvalis.altk.noc.ch1;

/**
 * simple euclidean vector object
 * @author snerd
 *
 */
public class Vector {

	public double xD;
	public double yD;
	
	
	// constructor
	public Vector(double x, double y) {
		xD = x;
		yD = y;
	}
	
	
	// vector addition
	void add(Vector v) {
		xD += v.xD;
		yD += v.yD;
	}
	
	
	
	
	
}
