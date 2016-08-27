package com.projectvalis.altk.jbox2d.lab.asteroids;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AsteroidsTestContactListener implements ContactListener {

	public static final Logger LOGGER = 
			LoggerFactory.getLogger(AsteroidsTestContactListener.class);
	
	@Override
	public void beginContact(Contact arg0) {
		
		Object objA = arg0.getFixtureA().getBody().getUserData();
		Object objB = arg0.getFixtureB().getBody().getUserData();

		if ((objA == null) || (objB == null)) { return; }
		
		if ((objA.getClass() == SquareAsteroid.class) || 
				(objB.getClass() == SquareAsteroid.class)) {
			
			((WorldElement)objA).m_selfDestruct = true;
			((WorldElement)objB).m_selfDestruct = true;		
		}
		
	}

	@Override
	public void endContact(Contact arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub
		
	}

}
