package com.projectvalis.altk.noc.ch5;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.init.internalFrameDark;
import com.projectvalis.altk.util.Jbox2dUtils;
import com.projectvalis.altk.util.Pair;



/**
 * base controller manager for all simulations using jbox2d 
 * 
 * @author snerd
 *
 */
public abstract class ManagedElementController extends internalFrameDark {
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger("ManagedElementController");
	
    private final World m_world;
    private final float m_timeStep = 1.0f / 60.f;
    private final int m_velocityIterations = 6;
    private final int m_positionIterations = 2;
    protected List<ManagedElementPair> m_managedPairList;
    
    private ManagedElementPanel m_ballPanel; 
	

    /**
     * 
     * @param gravityVector
     * @param windowSize
     * @param windowLocation
     * @param managedPairList
     */
    public ManagedElementController(Vec2 gravityVector,
    		                        Vec2 windowSize, 
    		                        Vec2 windowLocation,
    		                        List <ManagedElementPair> managedPairList) {
    	
    	// setup world and create bodies
    	m_world = new World(gravityVector);
        m_managedPairList = managedPairList;
    	
    	m_managedPairList.stream()
    	                 .parallel()
    	                 .map(ManagedElementPair::getLeft)
    	                 .forEach(x -> x.createInWorld(m_world));  
   
    	// setup internal frame and render
    	this.setLocation((int)windowLocation.x, (int)windowLocation.y);
    	this.setSize((int)windowSize.x, (int)windowSize.y);	
		this.setTitle("Bouncing Ball JBox2D Demo");
		this.setDoubleBuffered(true);
		
    	m_ballPanel = new ManagedElementPanel(m_managedPairList);
    	m_ballPanel.setDoubleBuffered(true);
	    this.add(m_ballPanel);
	    this.attach(true);
    }
	
    
    /**
     * kicks off an infinite loop that sets the simulation in motion
     */
    public void runSimulation() {		
        boolean keepOnTruckn = true;

        while (keepOnTruckn) {					     			
        	Graphics2D g2d = (Graphics2D) m_ballPanel.getGraphics();
        	
			try {								
//				Dimension screenSize = this.getSize();
//
//				Vec2 screenSizeVector = 
//						new Vec2(screenSize.width, screenSize.height);
//				
                m_world.step(m_timeStep,
                		     m_velocityIterations, 
                		     m_positionIterations);

//                List<Vec2> currentPositionsList = 
//                	m_managedPairList.stream()
//                                     .parallel()
//                                     .map(ManagedElementPair::getLeft)
//                                     .map(ManagedElementModel::getBody)
//                                     .map(Body::getPosition)
//                                     .map(x -> 
//                                         Jbox2dUtils.box2dToPixelCoordinate(
//                                    		x, screenSizeVector))
//                                     .collect(Collectors.toList());
//               
//                
//                IntStream.range(0, currentPositionsList.size())
//                         .parallel()
//                         .forEach(i -> 
//                             m_managedPairList.get(i)
//                                              .getRight()
//                                              .renderPresentation(
//                                            	  g2d, 
//                                                  currentPositionsList.get(i), 
//                                                  new Vec2(10, 10)));
				
				this.repaint();
				Thread.sleep(10);
				
				// ensure the window is still open
				keepOnTruckn = (this.isClosed) ? (false) : (true);
				
			} catch (InterruptedException e) {
				LOGGER.error("EXITING ON ERROR! ", e);
				keepOnTruckn = false;
			}
			
		}
		
    }
	
}
