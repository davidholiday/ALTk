package com.projectvalis.altk.util;

import com.projectvalis.altk.noc.ch1.Vector;

public class TrigHelpers {

	/*
	 * takes a given polar coordinate and returns the appropriate cartesian
	 * vector. remember:
	 * 
	 * Cartesian coordinate—the x,y components of a vector
	 * Polar coordinate—the magnitude (length) and direction (angle) of a vector
	 * 
	 * sine(theta)   = y/r   →   y = r * sine(theta)
	 * cosine(theta) = x/r   →   x = r * cosine(theta)
     *
     *
	 */
	public static Vector PolarToVector(double thetaD, double radiusD) {
		double vectorX_D = Math.cos(thetaD) * radiusD;
		double vectorY_D = Math.sin(thetaD) * radiusD;	
		return new Vector(vectorX_D, vectorY_D);		
	}

	
	
	/**
	 * takes a point assumed to be on the circumference of a unit circle
	 * and rotates it thetaD (polar) degrees.  
	 * 
	 * 
	 * @param thetaD
	 * @param locationV
	 * @return
	 */
	public static Vector RotatePoint(double thetaD, Vector locationV) {
		double vectorX_D = 1 * Math.cos(thetaD) + locationV.xD;
		double vectorY_D = 1 * Math.sin(thetaD) + locationV.yD;
		return new Vector(vectorX_D, vectorY_D);
	}
	
}
