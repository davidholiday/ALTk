package com.projectvalis.altk.jbox2d.lab;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.PolygonShape;
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
		
		Vec2 screenCenterVector = new Vec2(10, 10);
		makeAsteroid(4, screenCenterVector);
		
	}

	
	
	/**
	 * TODO doesn't work for dimensions other than 2!!
	 * 
	 * 
	 * @param dimension
	 * @param position
	 */
	private void makeAsteroid(int dimension, Vec2 position) {
		
		List<Body> asteroidBodyList = new ArrayList<>();
		Vec2 currentPosition = position.clone();
		int xInversion = 1;
		boolean xInversionChange = false;
		
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
 				currentPosition.x += (1 * xInversion);   
 				
 				if ((k % 2 == 1) && (k > 0)) {
 					//if (xInversionChange) break;
 					int currentIndex = k * (i + 1);
					
 					Vec2 anchorAngleVector = 
 						(xInversionChange) ? 
 								(new Vec2(0, 1)) : (new Vec2(xInversion, 0));
 					
 					xInversionChange = false;
 								
 					WeldJointDef weldJointDef = new WeldJointDef();
 	 				weldJointDef.bodyA = asteroidBodyList.get(currentIndex);
 	 				weldJointDef.bodyB = asteroidBodyList.get(currentIndex - 1);
 	 				weldJointDef.localAnchorA.set(anchorAngleVector);
 	 				
 	 				this.getWorld().createJoint(weldJointDef);			
 				}
				
			}
			
			//currentPosition.x = position.x;
			xInversionChange = true;
			currentPosition.x -= (1 * xInversion);
			xInversion *= -1;
			currentPosition.y -= 1;
			
			
		}
		
		
		int lastElementIndex = asteroidBodyList.size() - 1;
		WeldJointDef weldJointDef = new WeldJointDef();
		weldJointDef.bodyA = asteroidBodyList.get(lastElementIndex);
		weldJointDef.bodyB = asteroidBodyList.get(lastElementIndex - 1);
		weldJointDef.localAnchorA.set(-1, 0);
		
		this.getWorld().createJoint(weldJointDef);
		
	}
	
}





