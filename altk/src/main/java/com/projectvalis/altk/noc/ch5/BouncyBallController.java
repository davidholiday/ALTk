package com.projectvalis.altk.noc.ch5;


import java.util.List;

import org.jbox2d.common.Vec2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.noc.ch1.BallRunner;

public class BouncyBallController extends ManagedElementController {

	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(BouncyBallController.class);
	

	public BouncyBallController(Vec2 gravityVector, 
			                    Vec2 windowSize,
			                    Vec2 windowLocation, 
			                    List<ManagedElementPair> managedPairList,
			                    float timeStep, 
		                        int velocityIterations, 
		                        int positionIterations) {
		
		
		super(gravityVector, 
			  windowSize, 
			  windowLocation, 
			  managedPairList,
			  timeStep,
			  velocityIterations,
			  positionIterations);

	}


	@Override
	public void checkInputFlags() {
		// TODO Auto-generated method stub
		
	}
	

}

