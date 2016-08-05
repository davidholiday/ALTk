package com.projectvalis.altk.jbox2d.lab.adsplode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;


public class AdSplodeTestRun extends TestbedTest {

	private List<Vec2> paddleVecList = new ArrayList<>();
	
	@Override
	public String getTestName() {
		return "AdSplodeTestRun";
	}

	@Override
	public void initTest(boolean arg0) {
		Vec2 gravityVector = new Vec2(0, -10);
		this.getWorld().setGravity(gravityVector);
		this.getWorld().setContactListener(new AdSplodeContactListener());
		
		
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
	public void step(TestbedSettings settings) {
		super.step(settings);
		
		//
		//
		//Vec2 worldMouse = super.getWorldMouse(); 
		
		List<Vec2> boundaryVerticies = new ArrayList<>();
		
		if (super.isMouseTracing()) {
			Vec2 mouseTracerPosition = super.getMouseTracerPosition().clone();
		
//System.out.println(mouseTracerPosition);	

			if (paddleVecList.size() == 1) {
System.out.println(paddleVecList.get(0) + " " + mouseTracerPosition);				
				if ( !paddleVecList.get(0).equals(mouseTracerPosition) ) {
					paddleVecList.add(mouseTracerPosition);
					System.out.println("here!");
				}
				
			}
			else if (paddleVecList.size() == 2) {
				BodyDef chainBodyDef = new BodyDef();
				Body chainBody = this.getWorld().createBody(chainBodyDef);
			
				ChainShape chainShape = new ChainShape();	
				Vec2[] vecArray = new Vec2[paddleVecList.size()];
				vecArray = paddleVecList.toArray(vecArray);
System.out.println(Arrays.toString(vecArray));
				chainShape.createChain(vecArray, paddleVecList.size());
				paddleVecList.remove(0);
				
				chainBody.createFixture(chainShape, 1);
			}
			else {
				paddleVecList.add(mouseTracerPosition);
			}



			
			
		}

		
	}

}




