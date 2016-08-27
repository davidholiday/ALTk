package com.projectvalis.altk.noc.ch5.asteroids;

import java.util.List;

import org.jbox2d.common.Vec2;
import org.slf4j.Logger;

import com.projectvalis.altk.noc.ch5.ManagedElementController;
import com.projectvalis.altk.noc.ch5.ManagedElementPair;
import com.projectvalis.altk.util.EdgeDetectors;

public class AsteroidsController extends ManagedElementController {

    public AsteroidsController(Vec2 gravityVector, 
                               Vec2 windowSize,
                               Vec2 windowLocation, 
                               List<ManagedElementPair> managedPairList,
                               float timeStep, 
                               int velocityIterations, 
                               int positionIterations) {

        super(gravityVector, 
              windowSize, 
              windowLocation, 
              managedPairList,
              timeStep, 
              velocityIterations, 
              positionIterations);
        
        this.m_world.setContactListener(new AsteroidsContactListener());

    }

    @Override
    public void checkInputFlags() { /* noop */ }

    @Override
    public void checkEdges() {

//        synchronized(m_managedPairList) {
        
        m_managedPairList.parallelStream()
                         .map(ManagedElementPair::getLeft)
                         .forEach(x -> 
                             EdgeDetectors.loopEdges(m_windowSizeInBox, x));

//        }
        
    }

}
