package com.projectvalis.altk.noc.ch5;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * root class for all simulation runners. implementors create the desired
 * Model and View Objects, then kick off the simulation by creating the desired
 * controller object.
 * 
 * @author snerd
 *
 */
public abstract class ManagedElementRunner implements Runnable {
    Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    protected List<ManagedElementPair> m_managedElementList;
    protected ManagedElementController m_managedController;
    
    public ManagedElementRunner() {
    	populateElementList();
    }
	
    public abstract void populateElementList();
    
}
