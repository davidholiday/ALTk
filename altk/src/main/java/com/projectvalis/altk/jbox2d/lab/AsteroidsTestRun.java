package com.projectvalis.altk.jbox2d.lab;


import java.util.HashMap;
import java.util.Map;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.WeldJointDef;
import org.jbox2d.testbed.framework.TestbedSettings;
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
		
		makeJointedCompoundAsteroid(8, new Vec2(10, 10));		
		makeCompoundAsteroid(8, new Vec2(0, 0));
		makeUssTriangle(new Vec2(-5, -5));
		
	}

	
	@Override
	public void step(TestbedSettings settings) {
	  super.step(settings);

//	  TestbedModel model = getModel();
//	  if (model.getKeys()['a']) { // model also contains the coded key values
//	    
//	  }


	  Vec2 worldMouse = super.getWorldMouse(); // which is in world coordinates

	  // etc
	}
	
	
	
	private void makeUssTriangle(Vec2 position) {
		BodyDef triangleBodyDef = new BodyDef();
		triangleBodyDef.setType(BodyType.DYNAMIC);
		triangleBodyDef.setPosition(position);
		
		Body triangleBody = this.getWorld().createBody(triangleBodyDef);
		
		//double x2Points[] = {0, -10, 0, 10};			
		//double y2Points[] = {0, 35, 25, 35};
		
		Vec2[] verticies = new Vec2[4];
		verticies[0] = new Vec2(0, 0);
		verticies[1] = new Vec2(-1, 3.5f);
		verticies[2] = new Vec2(0, 2.5f);
		verticies[3] = new Vec2(1, 3.5f);
		
		PolygonShape triangleShape = new PolygonShape();
		triangleShape.set(verticies, verticies.length);
		
		triangleBody.createFixture(triangleShape, 10);
	}
	
	
	/**
	 * creates one body with multiple shapes
	 * 
	 * @param dimension
	 * @param position
	 */
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
        		asteroidElementShape.setAsBox(0.5f, 0.5f, transformPosition, 0.0f);
        		asteroidBody.createFixture(asteroidElementShape, 10);  
        		
        		transformPosition.x += 1;
			}
			
			transformPosition.x = position.x;	
			transformPosition.y -=1;	
		}

	
	}
	
	
	
	/**
	 * creates multiple bodies each with their own shape. bodies are joined
	 * by a weld joint
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





