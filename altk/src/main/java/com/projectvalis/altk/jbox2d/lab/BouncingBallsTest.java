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
        circleBody.createFixture(circleShape, 5);		
        
        
        // edge box
        //
        BodyDef edgeBoxBodyDef = new BodyDef();
        Vec2 edgeCenterVector = new Vec2(0, 0);
        edgeBoxBodyDef.setPosition(edgeCenterVector);
        edgeBoxBodyDef.setType(BodyType.STATIC);
        
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(20, 20);
        
        Body edgeBody = this.getWorld().createBody(edgeBoxBodyDef);
        edgeBody.createFixture(boxShape, 0);
        
        
	}

	@Override
	public String getTestName() {
		return "bouncing balls test";
	}
	

}
