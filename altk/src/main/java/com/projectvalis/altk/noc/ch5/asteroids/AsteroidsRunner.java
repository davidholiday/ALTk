package com.projectvalis.altk.noc.ch5.asteroids;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.noc.ch5.ManagedElementPair;
import com.projectvalis.altk.noc.ch5.ManagedElementRunner;
import com.projectvalis.altk.noc.ch5.bouncyball.BouncyBallController;

public class AsteroidsRunner extends ManagedElementRunner {
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void run() {
		Vec2 gravityVector = new Vec2(0, 0);
		Vec2 windowSizeVector = new Vec2(1280, 720);
		Vec2 windowPositionVector = new Vec2(400, 200);
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
		
		
		
	}

}
