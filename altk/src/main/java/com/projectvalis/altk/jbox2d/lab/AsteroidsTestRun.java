package com.projectvalis.altk.jbox2d.lab;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.jbox2d.collision.shapes.CircleShape;

//import com.projectvalis.altk.util.KeyConstants;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.joints.WeldJointDef;
import org.jbox2d.testbed.framework.TestbedModel;
import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.util.KeyConstants;
import com.projectvalis.altk.util.TrigHelpers;


/**
 * test run for a basic asteroids game
 * 
 * @author snerd
 *
 */
public class AsteroidsTestRun extends TestbedTest {

	public static final Logger LOGGER = LoggerFactory.getLogger(AsteroidsTestRun.class);
	public static final int BATTERY_CAPACITY = 25;
	public static final int BATTERY_RECHARGE_RATE = 1;
	
	private Body ussTriangleBody = null;
	private int ussTriangleBattery = BATTERY_CAPACITY;
	private List<Body> bulletList = new ArrayList<>();
	
	@Override
	public String getTestName() {
		return "asteroids test run";
	}

	
	
	@Override
	public void initTest(boolean arg0) {
		Vec2 gravityVector = new Vec2(0, 0);
		this.getWorld().setGravity(gravityVector);
		
		bulletList.clear();
		
//		makeJointedCompoundAsteroid(8, new Vec2(10, 10));		
		makeCompoundAsteroid(3, new Vec2(0, 0));
		makeUssTriangle(new Vec2(-5, -5));
		
	}
	
	

	
	@Override
	public void step(TestbedSettings settings) {
		super.step(settings);

		ussTriangleBody.setAngularVelocity(0);

		TestbedModel model = getModel();
		Vec2 shipPosition = ussTriangleBody.getPosition();

		// fire!
		//
		if (model.getKeys()['x'] && ussTriangleBattery == BATTERY_CAPACITY) { 
			bulletList.add(makeBullet(shipPosition));

			ussTriangleBattery = 0;
		}


		// movement
		//
		if (model.getCodedKeys()[KeyConstants.UP]) {
			// 1.57 radians = 90 degrees
			float headingAngle = ussTriangleBody.getAngle() + 1.57f;

			Vec2 newAccelerationVector = 
					TrigHelpers.PolarToVec2(headingAngle, 5);

			ussTriangleBody.applyForceToCenter(newAccelerationVector);
		}
		if (model.getCodedKeys()[KeyConstants.RIGHT]) {
			ussTriangleBody.setAngularVelocity(-5);

		}
		if (model.getCodedKeys()[KeyConstants.LEFT]) {
			ussTriangleBody.setAngularVelocity(5);
		}


		//
		//
		Vec2 worldMouse = super.getWorldMouse(); // which is in world coordinates



		ussTriangleBattery = 
				(ussTriangleBattery == BATTERY_CAPACITY) ? 
						(ussTriangleBattery) : 
							(ussTriangleBattery + BATTERY_RECHARGE_RATE);


						if (bulletList.size() > 10) {

							IntStream.range(0, bulletList.size() - 10)
							         .forEach(i -> this.getWorld().destroyBody(bulletList.get(i)));
							
							for (int i = 0; i < bulletList.size() - 10; i ++) {
								//this.getWorld().destroyBody(bulletList.get(0));
								bulletList.remove(i);
							}

						}
	}
	
	
	
	private Body makeBullet(Vec2 shipPosition) {
		BodyDef bulletBodyDef = new BodyDef();
		bulletBodyDef.setType(BodyType.DYNAMIC);
		bulletBodyDef.setPosition(shipPosition);
		
		Body bulletBody = this.getWorld().createBody(bulletBodyDef);
	    // 1.57 radians = 90 degrees
		float headingAngle = ussTriangleBody.getAngle() + 1.57f;
	  
		Vec2 velocityVector = 
				TrigHelpers.PolarToVec2(headingAngle, 50);
		
		bulletBody.setLinearVelocity(velocityVector);
		
		
		CircleShape bulletShape = new CircleShape();
		bulletShape.setRadius(0.1f);
		
		bulletBody.createFixture(bulletShape, 50);
		return bulletBody;
	}
	
	
	/**
	 * 
	 * @param position
	 */
	private void makeUssTriangle(Vec2 position) {
		BodyDef triangleBodyDef = new BodyDef();
		triangleBodyDef.setType(BodyType.DYNAMIC);
		triangleBodyDef.setPosition(position);
		
		ussTriangleBody = this.getWorld().createBody(triangleBodyDef);
		
		// original swing shape dimensions
		//
		//double x2Points[] = {0, -10, 0, 10};			
		//double y2Points[] = {0, 35, 25, 35};
		
		Vec2[] verticiesRight = new Vec2[3];
		verticiesRight[0] = new Vec2(0, 0);
		verticiesRight[1] = new Vec2(0.5f, -1.75f);
		verticiesRight[2] = new Vec2(0, -1.25f);

		PolygonShape triangleShapeRight = new PolygonShape();
		triangleShapeRight.set(verticiesRight, verticiesRight.length);		
		
		
		Vec2[] verticiesLeft = new Vec2[3];
		verticiesLeft[0] = new Vec2(0, 0);
		verticiesLeft[1] = new Vec2(-0.5f, -1.75f);
		verticiesLeft[2] = new Vec2(0, -1.25f);

		PolygonShape triangleShapeLeft = new PolygonShape();
		triangleShapeLeft.set(verticiesLeft, verticiesLeft.length);			
	
		ussTriangleBody.createFixture(triangleShapeRight, 1);
		ussTriangleBody.createFixture(triangleShapeLeft, 1);
		
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
        		asteroidBody.createFixture(asteroidElementShape, 1);  
        		
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





