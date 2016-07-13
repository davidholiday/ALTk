package com.projectvalis.altk.noc.ch5;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BouncyBallRunner extends ManagedElementRunner {
    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	
	
	@Override
	public void run() {
		Vec2 gravityVector = new Vec2(0, -10);
		Vec2 windowSizeVector = new Vec2(600, 600);
		Vec2 windowPositionVector = new Vec2(800, 200);
	    float timeStep = 1.0f / 60.0f;
	    int velocityIterations = 6;
	    int positionIterations = 2;
	    
		BouncyBallController managedController = 
				new BouncyBallController(gravityVector, 
						                 windowSizeVector, 
						                 windowPositionVector, 
						                 m_managedElementList,
						                 timeStep, 
		    		                     velocityIterations, 
		    		                     positionIterations);
		
		managedController.runSimulation();
	}

	@Override
	public void populateElementList() {
		m_managedElementList = new ArrayList<ManagedElementPair>();
		
		
		// circles
		//
		Vec2 modelStartPosition = new Vec2(0, 0);
		
		ManagedCircleModel circleModel = 
				new ManagedCircleModel(modelStartPosition, 
						               1, 
						               0.6f, 
						               0.3f, 
						               1);
		
		ManagedCircleView circleView = new ManagedCircleView();
		
		ManagedElementPair circlePair = new 
				ManagedElementPair(circleModel, circleView);
		
		m_managedElementList.add(circlePair);
		
				
        // the ground
        //
        Vec2 groundBodyPositionVector = new Vec2(0, -30);
        Vec2 sizeVector = new Vec2(10, 0);
        
        ManagedEdgeWallModel groundModel = 
        		new ManagedEdgeWallModel(groundBodyPositionVector, 
        				                 0, 
        				                 0, 
        				                 1, 
        				                 sizeVector);
        
        ManagedNullView nullView = new ManagedNullView();
        
        ManagedElementPair groundPair = new
        		ManagedElementPair(groundModel, circleView);
        
        m_managedElementList.add(groundPair);
        
        
//        // walls
//        //
//        BodyDef leftWallBodyDef = new BodyDef();
//        Vec2 leftWallBodyPositionVector = new Vec2(-31, 0);
//        leftWallBodyDef.setPosition(leftWallBodyPositionVector);;
//  
//        PolygonShape leftWallShape = new PolygonShape();
//        leftWallShape.setAsBox(0, 60);
//        
//        Body leftWallBody = this.getWorld().createBody(leftWallBodyDef);
//        leftWallBody.createFixture(leftWallShape, 0);
//        
//        
//        BodyDef rightWallBodyDef = new BodyDef();
//        Vec2 rightWallBodyPositionVector = new Vec2(31, 0);
//        rightWallBodyDef.setPosition(rightWallBodyPositionVector);;
//  
//        PolygonShape rightWallShape = new PolygonShape();
//        rightWallShape.setAsBox(0, 60);
//        
//        Body rightWallBody = this.getWorld().createBody(rightWallBodyDef);
//        rightWallBody.createFixture(rightWallShape, 0);
//        
//        
//        // ceiling
//        //
//        BodyDef ceilingBodyDef = new BodyDef();
//        Vec2 ceilingBodyPositionVector = new Vec2(0, 51);
//        ceilingBodyDef.setPosition(ceilingBodyPositionVector);;
//  
//        PolygonShape ceilingShape = new PolygonShape();
//        ceilingShape.setAsBox(60, 0);
//        
//        Body ceilingBody = this.getWorld().createBody(ceilingBodyDef);
//        ceilingBody.createFixture(ceilingShape, 0);
	}

}
