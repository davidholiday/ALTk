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
		Vec2 windowSizeVector = new Vec2(640, 480);
		Vec2 windowPositionVector = new Vec2(800, 200);

		BouncyBallController managedController = 
				new BouncyBallController(gravityVector, 
						                 windowSizeVector, 
						                 windowPositionVector, 
						                 m_managedElementList);
		
		managedController.runSimulation();
	}

	@Override
	public void populateElementList() {
		m_managedElementList = new ArrayList<ManagedElementPair>();
		
		Vec2 modelStartPosition = new Vec2(10, 10);
		
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
