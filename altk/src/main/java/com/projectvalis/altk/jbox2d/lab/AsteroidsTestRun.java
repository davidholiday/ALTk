package com.projectvalis.altk.jbox2d.lab;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import org.jbox2d.testbed.framework.TestbedTest;


/**
 * test run for a basic asteroids game
 * 
 * @author snerd
 *
 */
public class AsteroidsTestRun extends TestbedTest {

	
	@Override
	public String getTestName() {
		return "asteroids test run";
	}

	
	
	@Override
	public void initTest(boolean arg0) {
		Vec2 gravityVector = new Vec2(0, 0);
		this.getWorld().setGravity(gravityVector);
		
		Vec2 screenCenterVector = new Vec2(10, 10);
		makeAsteroid(2, screenCenterVector);
		
	}

	
	
	/**
	 * 
	 * @param dimension
	 * @param position
	 */
	private void makeAsteroid(int dimension, Vec2 position) {
		
		List<Body> asteroidBodyList = new ArrayList<>();
		Vec2 currentPosition = position.clone();
		int xInversion = 1;
		
		for (int i = 0; i < dimension; i ++) {
					
			for (int k = 0; k < dimension; k ++) {	
				BodyDef asteroidBodyDef = new BodyDef();
				asteroidBodyDef.setType(BodyType.DYNAMIC);
				asteroidBodyDef.setPosition(currentPosition);
				
				Body asteroidBody = this.getWorld().createBody(asteroidBodyDef);		
                asteroidBodyList.add(asteroidBody);
                
        		PolygonShape asteroidElementShape = new PolygonShape();
        		asteroidElementShape.setAsBox(0.5f, 0.5f);
        		asteroidBody.createFixture(asteroidElementShape, 10);
 System.out.println(currentPosition.x);        		
 				currentPosition.x += (1 * xInversion);
      		        		
			}
			
			//currentPosition.x = position.x;
			currentPosition.x -= (1 * xInversion);
			xInversion *= -1;
			currentPosition.y -= 1;
		}
		
		
		int halfListSize = asteroidBodyList.size() / 2;
		int lastElementIndex = asteroidBodyList.size() - 1;
		float jointLength = 1.0f;
		float jointFrequency = 0;
		
		for (int i = 0; i < halfListSize + 1; i ++) {
			DistanceJointDef distanceJointDef = new DistanceJointDef();
			distanceJointDef.bodyA = asteroidBodyList.get(i);
			distanceJointDef.bodyB = asteroidBodyList.get(i+1);
			distanceJointDef.length = jointLength;
			distanceJointDef.frequencyHz = jointFrequency;
			
			this.getWorld().createJoint(distanceJointDef);
		}
		
		// attach head to tail element
		DistanceJointDef distanceJointDef = new DistanceJointDef();
		distanceJointDef.bodyA = asteroidBodyList.get(0);
		distanceJointDef.bodyB = asteroidBodyList.get(lastElementIndex);
		distanceJointDef.length = jointLength;
		distanceJointDef.frequencyHz = jointFrequency;
		
		this.getWorld().createJoint(distanceJointDef);
		
		
	}
	
}





