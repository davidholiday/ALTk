package com.projectvalis.altk.noc.ch5.asteroids;

import java.util.List;

import org.jbox2d.common.Vec2;

import com.projectvalis.altk.noc.ch5.ManagedElementController;
import com.projectvalis.altk.noc.ch5.ManagedElementPair;

public class AsteroidsController extends ManagedElementController {


	public AsteroidsController(Vec2 gravityVector, 
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
