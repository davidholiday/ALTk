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
		
		ManagedAsteroidModel asteroidModel = makeRandomAsteroid(false);
		
		ManagedAsteroidView asteroidView = 
				new ManagedAsteroidView(1, GUI.redC.darker(), GUI.redC);
		
		ManagedElementPair elementPair = 
				new ManagedElementPair(asteroidModel, asteroidView);
		
		m_managedElementList.add(elementPair);
		
		
	}

	
	
	/**
	 * TODO make boolean do something
	 * 
	 * @param staticPosition
	 * @return
	 */
	private ManagedAsteroidModel makeRandomAsteroid(boolean staticPosition) {
		int randy = ThreadLocalRandom.current().nextInt(1, 40);
		Vec2 startPosition = RandomVectorUtils.getRandomVector(randy);
		Vec2 linearVelocity = RandomVectorUtils.getRandomVector(2);
		
		return new ManagedAsteroidModel(startPosition, 
										linearVelocity,
										linearVelocity.clone(),
										5f,
										0f,
										0.5f,
										0f);
										
										
		
		
	}
	
	
}


