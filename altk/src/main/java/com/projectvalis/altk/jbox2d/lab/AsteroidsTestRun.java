package com.projectvalis.altk.jbox2d.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.DistanceJointDef;
import org.jbox2d.dynamics.joints.WeldJointDef;
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
		
		Vec2 posVector = new Vec2(10, 10);
		makeJointedCompoundAsteroid(4, posVector);
		
		posVector = new Vec2(0, 10);
		makeCompoundAsteroid(4, posVector);
		
	}

	
	
	private void makeCompoundAsteroid(int dimension, Vec2 position) {		
		Vec2 currentPosition = position.clone();
		Vec2 transformPosition = new Vec2(0, 0);

		BodyDef asteroidBodyDef = new BodyDef();
		asteroidBodyDef.setType(BodyType.DYNAMIC);
		asteroidBodyDef.setPosition(currentPosition);
		
		Body asteroidBody = this.getWorld().createBody(asteroidBodyDef);	
		
		for (int i = 0; i < dimension; i ++) {
					
			for (int k = 0; k < dimension; k ++) {								 						
				PolygonShape asteroidElementShape = new PolygonShape();
        		asteroidElementShape.setAsBox(0.5f, 0.5f);
        		
        		Transform transform = new Transform();
        		asteroidElementShape.centroid(transform);
        		
        		asteroidBody.createFixture(asteroidElementShape, 10);  
        		
        		currentPosition.x += 1;		
        		transformPosition.x += 1;
			}
			
			currentPosition.x = position.x;
			currentPosition.y -= 1;					
		}

	
	}
	
	
	
	/**
	 * 
	 * 
	 * @param dimension
	 * @param position
	 */
	private void makeJointedCompoundAsteroid(int dimension, Vec2 position) {
		
		Map<String, Body> asteroidMap = new HashMap<>();
		
		Body currentAsteroidBody = null;
		Body lastAsteroidBody = null;
		
		Vec2 currentPosition = position.clone();
		Vec2 jointAngleBack = new Vec2(-1, 0);
		Vec2 jointAngleUp = new Vec2(0, 1);
		
		for (int i = 0; i < dimension; i ++) {
					
			for (int k = 0; k < dimension; k ++) {	
				BodyDef asteroidBodyDef = new BodyDef();
				asteroidBodyDef.setType(BodyType.DYNAMIC);
				asteroidBodyDef.setPosition(currentPosition);
				
				lastAsteroidBody = currentAsteroidBody;
				currentAsteroidBody = this.getWorld().createBody(asteroidBodyDef);	
				 						
				String key = new String(i + " " + k);
				asteroidMap.put(key, currentAsteroidBody);

				PolygonShape asteroidElementShape = new PolygonShape();
        		asteroidElementShape.setAsBox(0.5f, 0.5f);
        		currentAsteroidBody.createFixture(asteroidElementShape, 10);  
        		
//        		currentAsteroidBody.getFixtureList().setRestitution(0);
//        		currentAsteroidBody.getFixtureList().setFriction(1);
        		
        		currentPosition.x += 1;

        		// up joints
 	 			if (i > 0) {
 	 				String aboveKey = new String((i - 1) + " " + k );
 	 				Body aboveAsteroidBody = asteroidMap.get(aboveKey);
 	 				makeJoint(currentAsteroidBody, aboveAsteroidBody, jointAngleUp);
 	 			}        		
        		
 	 			// back joints
 				if (k > 0) { 															
 					makeJoint(currentAsteroidBody, lastAsteroidBody, jointAngleBack);
 				}
				
			}
			
			currentPosition.x = position.x;
			currentPosition.y -= 1;					
		}

		
	}
	
	
	/**
	 * 
	 * @param bodyA
	 * @param bodyB
	 * @param jointAngle
	 */
	private void makeJoint(Body bodyA, Body bodyB, Vec2 jointAngle) {
			WeldJointDef weldJointUpDef = new WeldJointDef();
			weldJointUpDef.bodyA = bodyA;
			weldJointUpDef.bodyB = bodyB;
			weldJointUpDef.localAnchorA.set(jointAngle);
			weldJointUpDef.dampingRatio = 1;
			weldJointUpDef.frequencyHz = 0;
			
			this.getWorld().createJoint(weldJointUpDef);
	}
	
}





