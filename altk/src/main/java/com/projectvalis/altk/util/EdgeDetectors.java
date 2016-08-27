package com.projectvalis.altk.util;

import org.jbox2d.common.Vec2;

import com.projectvalis.altk.noc.ch1.Vector;
import com.projectvalis.altk.noc.ch5.ManagedElementModel;


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
	 * if you hit the edge of the panel, redraw element at opposite end
	 * of panel
	 * 
	 * @param panelWidth
	 * @param panelHeight
	 * @param locationV
	 */
	public static void loopEdges(Vec2 panelSizeInBox, 
								 ManagedElementModel elementModel) {
		
		float panelWidth = panelSizeInBox.x;
		float panelHeight = panelSizeInBox.y;
		Vec2 locationV = elementModel.getBody().getPosition().clone();

System.out.println(panelWidth + " " + panelHeight + " " + locationV);		
//		if (locationV.x > panelWidth - 10) { locationV.x = 10; }
//		if (locationV.x < 10) { locationV.x = panelWidth - 10; }
//		
//		if (locationV.y > panelHeight - 10) { locationV.y = 10; }
//		if (locationV.y < 10) { locationV.y = panelHeight - 10; }
		
		if ((locationV.x > panelWidth - 1) || 
				(locationV.x < (panelWidth * -1))) { 
System.out.println("in x" + " " + locationV.x + " " + ((panelWidth + 1) * -1));			
			locationV.x *= -1; 
		}
		
				
		if ((locationV.y > panelHeight - 1) || 
				(locationV.y < (panelHeight * -1))) { 
System.out.println("in y"+ " " + locationV.y + " " + ((panelHeight + 1) * -1));				
			locationV.y *= -1; 
		}
		
		
		float angularVelocity = elementModel.getBody().getAngle();
System.out.println(locationV);
System.out.println("-=-=-=-=-=-=-=-=-=-=");
		elementModel.getBody().setTransform(locationV, angularVelocity);
		
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
