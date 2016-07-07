package com.projectvalis.altk.noc.ch5;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BouncyBallRunner extends ManagedElementRunner {
    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	
	
	@Override
	public void run() {
		Vec2 gravityVector = new Vec2(0, 0);
		Vec2 windowSizeVector = new Vec2(600, 600);
		Vec2 windowPositionVector = new Vec2(800, 200);
	    float timeStep = 1.0f / 60.0f;
	    int velocityIterations = 6;
	    int positionIterations = 2;
	    
		BouncyBallController managedController = 
				new BouncyBallController(gravityVector, 
						                 windowSizeVector, 
						                 windowPositionVector, 
						                 m_managedElementList,
						                 timeStep, 
		    		                     velocityIterations, 
		    		                     positionIterations);
		
		managedController.runSimulation();
	}

	@Override
	public void populateElementList() {
		m_managedElementList = new ArrayList<ManagedElementPair>();
		
		Vec2 modelStartPosition = new Vec2(0, 0);
		
		ManagedCircleModel circleModel = 
				new ManagedCircleModel(modelStartPosition, 
						               1, 
						               0.6f, 
						               0.3f, 
						               1);
		
		ManagedCircleView circleView = new ManagedCircleView();
		
		ManagedElementPair circlePair = new 
				ManagedElementPair(circleModel, circleView);
		
		m_managedElementList.add(circlePair);
	}

}
