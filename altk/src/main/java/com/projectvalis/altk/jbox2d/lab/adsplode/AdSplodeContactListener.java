package com.projectvalis.altk.jbox2d.lab.adsplode;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

import com.projectvalis.altk.jbox2d.lab.asteroids.SquareAsteroid;
import com.projectvalis.altk.jbox2d.lab.asteroids.WorldElement;

public class AdSplodeContactListener implements ContactListener {

	@Override
	public void beginContact(Contact arg0) {
		
		Object objA = arg0.getFixtureA().getBody().getUserData();
		Object objB = arg0.getFixtureB().getBody().getUserData();

		if ((objA == null) || (objB == null)) { return; }
		
		if ((objA.getClass() == Brick.class) || 
				(objB.getClass() == Brick.class)) {
			
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
