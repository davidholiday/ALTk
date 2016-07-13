package com.projectvalis.altk.util;

import com.projectvalis.altk.noc.ch1.Vector;


/**
 * common edge detection strategies used by elements
 * 
 * @author snerd
 *
 */
public class EdgeDetectors {

	
	/**
	 * if you hit the edge of the panel, redraw element at opposite end
	 * of panel
	 * 
	 * @param panelWidth
	 * @param panelHeight
	 * @param locationV
	 */
	public static void loopEdges(int panelWidth, 
								 int panelHeight, 
								 Vector locationV) {
		
		if (locationV.xD > panelWidth - 10) { locationV.xD = 10; }
		if (locationV.xD < 10) { locationV.xD = panelWidth - 10; }
		
		if (locationV.yD > panelHeight - 10) { locationV.yD = 10; }
		if (locationV.yD < 10) { locationV.yD = panelHeight - 10; }
		
	}
	
	
	/**
	 * bounces elements off the edges of the panel
	 * 
	 * @param panelWidth
	 * @param panelHeight
	 * @param locationV
	 * @param velocityV
	 * @param widthD
	 * @param heightD
	 */
	public static void bounceEdges(int panelWidth, 
								   int panelHeight, 
								   Vector locationV,
								   Vector velocityV,
								   double widthD,
								   double heightD) {
		
		// x axis
		//
		if ((locationV.xD + widthD) > panelWidth) {
			velocityV.xD *= -1;
			double locationDiffD = (locationV.xD + widthD) - panelWidth;
			locationV.xD = panelWidth - locationDiffD - widthD;
		}
		else if ((locationV.xD) < 0) {
			velocityV.xD *= -1;
			locationV.xD = 0;
		}
		
		// y axis
		//
		if ((locationV.yD + heightD) > panelHeight) {
			velocityV.yD *= -1;
			double locationDiffD = (locationV.yD + heightD) - panelHeight;
			locationV.yD = panelHeight - locationDiffD - heightD;
		}
		else if ((locationV.yD + heightD) < 0) {
			velocityV.yD *= -1;
			locationV.yD = 0;
		}	
		
	}
	
	
}
