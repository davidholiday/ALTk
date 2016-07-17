package com.projectvalis.altk.noc.ch5;

import java.util.ArrayList;
import java.util.Collections;

import org.jbox2d.common.Vec2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.init.GUI;

public class BouncyBallRunner extends ManagedElementRunner {
	
    Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void run() {
		Vec2 gravityVector = new Vec2(0, 0);
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

		
        // the ground
        //
        Vec2 groundBodyPositionVector = new Vec2(0, -57);
        Vec2 floorCeilingSizeVector = new Vec2(60, 0);
        
        ManagedEdgeWallModel groundModel = 
        		new ManagedEdgeWallModel(groundBodyPositionVector, 
        				                 0, 
        				                 0, 
        				                 1, 
        				                 floorCeilingSizeVector);
        
        ManagedNullView nullView = new ManagedNullView(0, null, null);
        
        ManagedRectangleView rectangleView = 
        		new ManagedRectangleView(1, GUI.redC, GUI.redC);
        
        ManagedElementPair groundPair = new
        		ManagedElementPair(groundModel, rectangleView);
        
        m_managedElementList.add(groundPair);
        
        
        // walls
        //
        Vec2 leftWallBodyPositionVector = new Vec2(-60, 0);
        Vec2 wallSizeVector = new Vec2(0, 60);
        
        ManagedEdgeWallModel leftWallModel = 
        		new ManagedEdgeWallModel(leftWallBodyPositionVector, 
        				                 0, 
        				                 0, 
        				                 1, 
        				                 wallSizeVector);
        

        ManagedElementPair leftWallPair = new
        		ManagedElementPair(leftWallModel, rectangleView);
        
        m_managedElementList.add(leftWallPair);      
        
        
        Vec2 rightWallBodyPositionVector = new Vec2(60, 0);
        
        ManagedEdgeWallModel rightWallModel = 
        		new ManagedEdgeWallModel(rightWallBodyPositionVector, 
        				                 0, 
        				                 0, 
        				                 1, 
        				                 wallSizeVector);
        
        ManagedElementPair rightWallPair = new
        		ManagedElementPair(rightWallModel, rectangleView);
        
        m_managedElementList.add(rightWallPair);
        
        
        // ceiling
        //
        Vec2 ceilingBodyPositionVector = new Vec2(0, 57);
        
        ManagedEdgeWallModel ceilingModel = 
        		new ManagedEdgeWallModel(ceilingBodyPositionVector, 
        				                 0, 
        				                 0, 
        				                 1, 
        				                 floorCeilingSizeVector);
        

        ManagedElementPair ceilingPair = new
        		ManagedElementPair(ceilingModel, rectangleView);
        
        m_managedElementList.add(ceilingPair);        
        

	}

}
