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
	
}
