package com.projectvalis.altk.noc.ch1;

/**
 * simple euclidean vector object
 * @author snerd
 *
 */
public class Vector {

	public float xF;
	public float yF;
	
	
	// constructor
	public Vector(float x, float y) {
		xF = x;
		yF = y;
	}
	
	
	// vector addition
	void add(Vector v) {
		xF += v.xF;
		yF += v.yF;
	}
	
	
	
	
	
}
