package com.projectvalis.altk.jbox2d.lab.asteroids;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
	
	private boolean gameOver = false;
	
	private UssTriangle ussTriangle = null;
	private int ussTriangleBattery = BATTERY_CAPACITY;
	private List<Bullet> bulletList = new ArrayList<>();
	private List<SquareAsteroid> asteroidList = new ArrayList<>();
	
	private List<List<ExplosionParticle>> explosionList = new ArrayList<>();
	
	@Override
	public String getTestName() {
		return "asteroids test run";
	}

	
	
	@Override
	public void initTest(boolean arg0) {
		Vec2 gravityVector = new Vec2(0, 0);
		this.getWorld().setGravity(gravityVector);
		
		this.getWorld().setContactListener(new AsteroidsContactListener());
		
		bulletList.clear();
		gameOver = false;
		
//		makeJointedCompoundAsteroid(8, new Vec2(10, 10));		
//		makeCompoundAsteroid(3, new Vec2(0, 0));
		makeSquareAsteroid(new Vec2(0, 0));
		makeUssTriangle(new Vec2(-5, -5));
	
	}
	
	

	
	@Override
	public void step(TestbedSettings settings) {
		super.step(settings);
		
		if (gameOver) { return; }

		ussTriangle.m_body.setAngularVelocity(0);

		TestbedModel model = getModel();
		Vec2 shipPosition = ussTriangle.m_body.getPosition();
		
		
		// check for destruct flags
		// TODO : this is more than a little inefficient...
		//
		if (ussTriangle.m_selfDestruct) { 
			this.getWorld().destroyBody(ussTriangle.m_body);
			makeExplosion(ussTriangle.m_body.getPosition());
			ussTriangle = null;
			gameOver = true;
		}
		
		for (WorldElement we : asteroidList) {
			
			if (we.m_selfDestruct) {
				Vec2 position = we.m_body.getPosition().clone();
				this.getWorld().destroyBody(we.m_body);
				makeExplosion(position);
			}
			
		}
	
		asteroidList = asteroidList.stream()
		            			   .filter(x -> x.m_selfDestruct == false)
		            			   .collect(Collectors.toList());
		

		for (Bullet b : bulletList) {
			
			if (b.m_selfDestruct) {
				this.getWorld().destroyBody(b.m_body);
			}
			
		}
		
		
		// new asteroid
		//
		if (model.getKeys()['n']) { 
			if (asteroidList.size() == 0) { makeSquareAsteroid(new Vec2(0, 0)); }
		}
		
		
		if (gameOver) { return; }
		

		// fire!
		//
		if (model.getKeys()['x'] && ussTriangleBattery == BATTERY_CAPACITY) { 
			Bullet bullet = makeBullet(shipPosition);
			bulletList.add(bullet);

			ussTriangleBattery = 0;
		}


		// movement
		//
		if (model.getCodedKeys()[KeyConstants.UP]) {
			// 1.57 radians = 90 degrees
			float headingAngle = ussTriangle.m_body.getAngle() + 1.57f;

			Vec2 newAccelerationVector = 
					TrigHelpers.PolarToVec2(headingAngle, 5);

			ussTriangle.m_body.applyForceToCenter(newAccelerationVector);
		}
		if (model.getCodedKeys()[KeyConstants.RIGHT]) {
			ussTriangle.m_body.setAngularVelocity(-5);

		}
		if (model.getCodedKeys()[KeyConstants.LEFT]) {
			ussTriangle.m_body.setAngularVelocity(5);
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
							         .forEach(i -> this.getWorld().destroyBody(bulletList.get(i).m_body));
							
							for (int i = 0; i < bulletList.size() - 10; i ++) {
								bulletList.remove(i);
							}

						}
	}
	
	
	
	private Bullet makeBullet(Vec2 shipPosition) {
		Vec2 torpedoLauncerPosition = shipPosition.clone();
		torpedoLauncerPosition.y += 0.1;
		
		BodyDef bulletBodyDef = new BodyDef();
		bulletBodyDef.setType(BodyType.DYNAMIC);
		bulletBodyDef.setPosition(torpedoLauncerPosition);
		
		Body bulletBody = this.getWorld().createBody(bulletBodyDef);
	    // 1.57 radians = 90 degrees
		float headingAngle = ussTriangle.m_body.getAngle() + 1.57f;
		
		return new Bullet(headingAngle, bulletBody);
	}
	
	
	/**
	 * 
	 * @param position
	 */
	private void makeUssTriangle(Vec2 position) {
		BodyDef triangleBodyDef = new BodyDef();
		triangleBodyDef.setType(BodyType.DYNAMIC);
		triangleBodyDef.setPosition(position);
		
		Body ussTriangleBody = this.getWorld().createBody(triangleBodyDef);
		ussTriangle = new UssTriangle(ussTriangleBody);
	}
	
	
	
	/**
	 * 
	 * @param position
	 */
	private void makeSquareAsteroid(Vec2 position) {
		BodyDef asteroidBodyDef = new BodyDef();
		asteroidBodyDef.setType(BodyType.DYNAMIC);
		asteroidBodyDef.setPosition(position);
		
		Body asteroidBody = this.getWorld().createBody(asteroidBodyDef);
		SquareAsteroid asteroid = new SquareAsteroid(asteroidBody);
		asteroidList.add(asteroid);
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
	
	
	
	/**
	 * 
	 * @param location
	 */
	private void makeExplosion(Vec2 location) {
		ArrayList<ExplosionParticle> particleList = new ArrayList<>();
		
		for (int i = 0; i < 25; i ++) {
			BodyDef circleBodyDef = new BodyDef();
			circleBodyDef.setPosition(location);
			circleBodyDef.setType(BodyType.DYNAMIC);
						
			Body circleBody = this.getWorld().createBody(circleBodyDef);
			particleList.add(new ExplosionParticle(circleBody));
		}
		
		explosionList.add(particleList);
	}
	
	
}





