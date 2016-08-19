package com.projectvalis.altk.noc.ch5.asteroids;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.noc.ch5.ManagedElementPair;
import com.projectvalis.altk.noc.ch5.ManagedElementRunner;
import com.projectvalis.altk.noc.ch5.bouncyball.BouncyBallController;
import com.projectvalis.altk.util.RandomVectorUtils;

import scala.concurrent.forkjoin.ThreadLocalRandom;

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
	    
		AsteroidsController managedController = 
				new AsteroidsController(gravityVector, 
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
		ManagedElementPair asteroid = makeRandomAsteroid(false);
		m_managedElementList.add(asteroid);	
	}

	
	
	/**
	 * 
	 * @param staticPosition
	 * @return
	 */
	private ManagedElementPair makeRandomAsteroid(boolean staticPosition) {
		int randy = ThreadLocalRandom.current().nextInt(1, 40);
//		Vec2 startPosition = RandomVectorUtils.getRandomVector(randy);
		Vec2 startPosition = new Vec2(25, 25);
		Vec2 linearVelocity = null;
		
		if (staticPosition) {
			linearVelocity = new Vec2(0, 0);
		}
		else {
			linearVelocity = RandomVectorUtils.getRandomVector(2);
		}
		
		Vec2 shapeSize = new Vec2(5, 5);
		float angularVelocity = 2;
		
		ManagedAsteroidModel asteroidModel = 
				new ManagedAsteroidModel(startPosition, 
				 						 linearVelocity,
				 						 shapeSize,
				 						 angularVelocity,
				 						 5f,
				 						 0f,
				 						 0.5f);
			
		ManagedAsteroidView asteroidView = 
				new ManagedAsteroidView(1, GUI.redC.darker(), GUI.redC);
		
		ManagedElementPair elementPair = 
				new ManagedElementPair(asteroidModel, asteroidView);										
										
		return elementPair;
	}
	
	
}


