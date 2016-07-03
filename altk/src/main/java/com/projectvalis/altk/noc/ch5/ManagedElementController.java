package com.projectvalis.altk.noc.ch5;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JInternalFrame;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.projectvalis.altk.noc.ch1.ElementPanel;
import com.projectvalis.altk.util.Pair;



/**
 * base controller manager for all simulations using jbox2d 
 * 
 * @author snerd
 *
 */
public abstract class ManagedElementController extends JInternalFrame {
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger("ManagedElementController");
	
    private World world;
	private List<Body> bodyList;
    private ElementPanel ballPanel;
	

    
    public ManagedElementController(Vec2 gravityVector,
    		                        Pair<Float, Float> windowSize, 
    		                        Pair<Float, Float> windowLocation,
    		                        List <ManagedElementPair> managedPairList) {
    	
    	world = new World(gravityVector);
    	
//    	bodyList = managedPairList.stream()
//    			                  .parallel()
//    			                  .map(ManagedElementPair::getLeft)
//    			                  .map(ManagedElementModel::getBodyDef)
//    			                  .map(world::createBody)
//    			                  .collect(Collectors.toList());
    	
    	
    }
	
    
    
    public void runSimulation() {
		Graphics2D g2d = (Graphics2D) ballPanel.getGraphics();
        boolean keepOnTruckn = true;
		while (keepOnTruckn) {					
			
			try {	


				
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
