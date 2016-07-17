package com.projectvalis.altk.noc.ch5;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

import org.jbox2d.common.Vec2;

import com.projectvalis.altk.init.GUI;


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
		
		float randomLinearVelocityX = getRandomFloat();
		float randomLinearVelocityY = getRandomFloat();
					
		Vec2 modelLinearVelocity = 
				new Vec2(randomLinearVelocityX, randomLinearVelocityY);
		
		int radius = getRandomBoundedInt(7) + 1; // prevents radius:0 circles
		int colorArrayIndex = getRandomBoundedInt(5);
		
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
	
	
	/**
	 * returns .nextFloat() * 10 * 2
	 * 
	 * @return
	 */
	private static float getRandomFloat() {
		return ThreadLocalRandom.current().nextFloat() * 10 * 2;
	}
	

	/**
	 * bound is exclusive
	 * 
	 * @param bound
	 * @return
	 */
	private static int getRandomBoundedInt(int bound) {
		return ThreadLocalRandom.current().nextInt(bound);
	}
	
}
