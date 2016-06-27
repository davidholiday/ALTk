package com.projectvalis.altk.jbox2d.lab;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.testbed.framework.TestbedTest;

public class BouncingBallsTest extends TestbedTest {

	@Override
	public void initTest(boolean deserialized) {
		
		// balls 
		//
		Vec2 gravityVector = new Vec2(0, -10);
		//this.getWorld().setGravity(new Vec2());
		this.getWorld().setGravity(gravityVector);
		
		BodyDef circleBodyDef = new BodyDef();
		Vec2 screenCenterVector = new Vec2(10, 10);
		// TODO create coordPixesToWorld analog (or just import it from box2d
		//
		circleBodyDef.setPosition(screenCenterVector);
		circleBodyDef.setType(BodyType.DYNAMIC);
		
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(1);
		
		Body circleBody = this.getWorld().createBody(circleBodyDef);
        circleBody.createFixture(circleShape, 100);		
        circleBody.getFixtureList().setRestitution(0.6f);
        
        
        // the ground
        //
        BodyDef groundBodyDef = new BodyDef();
        Vec2 groundBodyPositionVector = new Vec2(0, -10);
        groundBodyDef.setPosition(groundBodyPositionVector);;
  
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(50, 10);
        
        Body groundBody = this.getWorld().createBody(groundBodyDef);
        groundBody.createFixture(groundShape, 0);
        
	}

	@Override
	public String getTestName() {
		return "bouncing balls test";
	}
	

}
