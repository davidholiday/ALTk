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
		
		Vec2 gravityVector = new Vec2(0, -10);
		this.getWorld().setGravity(gravityVector);
		
		
		// balls 
		//
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
        circleBody.getFixtureList().setDensity(1);
        circleBody.getFixtureList().setRestitution(0.6f);
        circleBody.getFixtureList().setFriction(0.3f);
        
        
        // the ground
        //
        BodyDef groundBodyDef = new BodyDef();
        Vec2 groundBodyPositionVector = new Vec2(0, -11);
        groundBodyDef.setPosition(groundBodyPositionVector);;
  
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(60, 0);
        
        Body groundBody = this.getWorld().createBody(groundBodyDef);
        groundBody.createFixture(groundShape, 0);
        
        
        // walls
        //
        BodyDef leftWallBodyDef = new BodyDef();
        Vec2 leftWallBodyPositionVector = new Vec2(-31, 0);
        leftWallBodyDef.setPosition(leftWallBodyPositionVector);;
  
        PolygonShape leftWallShape = new PolygonShape();
        leftWallShape.setAsBox(0, 60);
        
        Body leftWallBody = this.getWorld().createBody(leftWallBodyDef);
        leftWallBody.createFixture(leftWallShape, 0);
        
        
        BodyDef rightWallBodyDef = new BodyDef();
        Vec2 rightWallBodyPositionVector = new Vec2(31, 0);
        rightWallBodyDef.setPosition(rightWallBodyPositionVector);;
  
        PolygonShape rightWallShape = new PolygonShape();
        rightWallShape.setAsBox(0, 60);
        
        Body rightWallBody = this.getWorld().createBody(rightWallBodyDef);
        rightWallBody.createFixture(rightWallShape, 0);
        
        
        // ceiling
        //
        BodyDef ceilingBodyDef = new BodyDef();
        Vec2 ceilingBodyPositionVector = new Vec2(0, 51);
        ceilingBodyDef.setPosition(ceilingBodyPositionVector);;
  
        PolygonShape ceilingShape = new PolygonShape();
        ceilingShape.setAsBox(60, 0);
        
        Body ceilingBody = this.getWorld().createBody(ceilingBodyDef);
        ceilingBody.createFixture(ceilingShape, 0);
        
	}

	@Override
	public String getTestName() {
		return "bouncing balls test";
	}
	

}
