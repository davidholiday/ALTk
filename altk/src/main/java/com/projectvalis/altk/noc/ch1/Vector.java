package com.projectvalis.altk.noc.ch1;

/**
 * simple euclidean vector object
 * @author snerd
 *
 */
public class Vector {

	public double xD;
	public double yD;
	
	
	public Vector(double x, double y) {
		xD = x;
		yD = y;
	}
	
	
	
	void add(Vector v) {
		xD += v.xD;
		yD += v.yD;
	}
	
	
	void subtract(Vector v) {
		xD -= v.xD;
		yD -= v.yD;
	}
	
	
	// tee hee
	void multiply(double d) {
		xD *= d;
		yD *= d;
	}
	
	
	void divide(double d) {
		xD /= d;
		yD /= d;
	}
	
	
	double getMagnitude() {
		return Math.sqrt(xD*xD + yD*yD);
	}

	
	void normalize() {
		Double magnitudeD = getMagnitude();
		
		if (magnitudeD != 0) {
			divide(magnitudeD);
		}
		
	}


	
	

	
	
}











