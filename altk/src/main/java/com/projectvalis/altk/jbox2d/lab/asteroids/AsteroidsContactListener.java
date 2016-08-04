package com.projectvalis.altk.jbox2d.lab.asteroids;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AsteroidsContactListener implements ContactListener {

	public static final Logger LOGGER = 
			LoggerFactory.getLogger(AsteroidsContactListener.class);
	
	@Override
	public void beginContact(Contact arg0) {
		
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
