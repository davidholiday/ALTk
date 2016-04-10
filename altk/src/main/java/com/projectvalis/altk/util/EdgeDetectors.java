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
	 * @return
	 */
	public static Vector loopEdges(int panelWidth, 
								   int panelHeight, 
								   Vector locationV) {
		
		if (locationV.xD > panelWidth - 10) { locationV.xD = 10; }
		if (locationV.xD < 10) { locationV.xD = panelWidth - 10; }
		
		if (locationV.yD > panelHeight - 10) { locationV.yD = 10; }
		if (locationV.yD < 10) { locationV.yD = panelHeight - 10; }
		
		return locationV;
	}
	
}
