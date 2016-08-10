package com.projectvalis.altk.noc.ch5.bouncyball;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

import org.jbox2d.common.Vec2;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.noc.ch5.ManagedElementPair;
import com.projectvalis.altk.util.RandomVectorUtils;


/**
 * 
 * @author snerd
 *
 */
public class ManagedCircleGenerator {

	/**
	 * returns a managed element pair consisting of a circle of random 
	 * radius, color, and linear velocity
	 * 
	 * @param modelStartPosition
	 * @return
	 */
	public static ManagedElementPair 
		getRandomManagedCircle(Vec2 modelStartPosition) {
		
		Color[] colorArray = new Color[5];
		colorArray[0] = GUI.mustardC;
		colorArray[1] = GUI.orangeC;
		colorArray[2] = GUI.redC;
		colorArray[3] = GUI.tealC;
		colorArray[4] = GUI.purpleC.brighter();

		Vec2 modelLinearVelocity = RandomVectorUtils.getRandomVector(2);
		
		int radius = RandomVectorUtils.getRandomBoundedInt(4) + 1; // prevents radius:0 circles
		int colorArrayIndex = RandomVectorUtils.getRandomBoundedInt(5);
		
		ManagedCircleModel circleModel = 
				new ManagedCircleModel(modelStartPosition, 
						               modelLinearVelocity,
						               1, 
						               1f, 
						               0.3f, 
						               radius);
		
		ManagedCircleView circleView = 
				new ManagedCircleView(
						3, GUI.purpleC, colorArray[colorArrayIndex]);
		
		ManagedElementPair circlePair = new 
				ManagedElementPair(circleModel, circleView);
		
		return circlePair;
		
	}
	
	

	
}
