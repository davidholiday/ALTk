package com.projectvalis.altk.noc.ch5.bouncyball;


import java.util.List;

import org.jbox2d.common.Vec2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.noc.ch1.BallRunner;
import com.projectvalis.altk.noc.ch5.ManagedElementController;
import com.projectvalis.altk.noc.ch5.ManagedElementPair;

import junit.framework.Assert;

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
		
		if (m_ballPanel.m_mouseInFrame && 
				m_ballPanel.m_mousePressed &&
				    m_ballPanel.m_mousePressBox2dPositionVector != null) {

			Vec2 modelStartPosition = 
					m_ballPanel.m_mousePressBox2dPositionVector.clone();
	
			ManagedElementPair circlePair = 
					ManagedCircleGenerator.getRandomManagedCircle(
							modelStartPosition);
		
			circlePair.getLeft().createInWorld(m_world);
			m_managedPairList.add(circlePair);

		}
		
	}
	

}

