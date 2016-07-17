package com.projectvalis.altk.noc.ch5;

import java.util.Collections;
import java.util.List;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.init.internalFrameDark;



/**
 * base controller manager for all simulations using jbox2d 
 * 
 * @author snerd
 *
 */
public abstract class ManagedElementController extends internalFrameDark {
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger("ManagedElementController");
	
    protected final World m_world;
    private final float m_timeStep;
    private final int m_velocityIterations;
    private final int m_positionIterations;
    protected List<ManagedElementPair> m_managedPairList;
    
    protected final ManagedElementPanel m_ballPanel; 

    /**
     * root controller class for all elements managed by jbox2d
     * 
     * @param gravityVector
     * @param windowSize
     * @param windowLocation
     * @param managedPairList
     */
    public ManagedElementController(
    		        Vec2 gravityVector,	                        
    		        Vec2 windowSize, 
    		        Vec2 windowLocation,
    		        List <ManagedElementPair> managedPairList,
    		        float timeStep, 
    		        int velocityIterations, 
    		        int positionIterations) {
    	
    	// setup world and create bodies
    	m_world = new World(gravityVector);
    	m_timeStep = timeStep;
    	m_velocityIterations = velocityIterations;
    	m_positionIterations = positionIterations;
    	
        m_managedPairList = Collections.synchronizedList(managedPairList);
    	
    	m_managedPairList.stream()
    	                 .map(ManagedElementPair::getLeft)
    	                 .forEach(x -> x.createInWorld(m_world));  
   
    	// setup internal frame and render
    	this.setLocation((int)windowLocation.x, (int)windowLocation.y);
    	this.setSize((int)windowSize.x, (int)windowSize.y);	
		this.setTitle("Bouncing Ball JBox2D Demo");
		
    	m_ballPanel = new ManagedElementPanel(m_managedPairList);
	    this.add(m_ballPanel);
	    this.attach(true);
    }
	
    
    /**
     * kicks off an infinite loop that sets the simulation in motion
     */
    public void runSimulation() {		
        boolean keepOnTruckn = true;

        while (keepOnTruckn) {					     			
	
			try {	
                m_world.step(m_timeStep,
                		     m_velocityIterations, 
                		     m_positionIterations);
                
                checkInputFlags();
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
	
    
    /**
     * for handling user input
     */
    public abstract void checkInputFlags();
    
}
