package com.projectvalis.altk.jbox2d.lab.asteroids;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;

public class SquareAsteroid extends WorldElement {

	
	public SquareAsteroid(Body asteroidBody) {
		super(asteroidBody);
		
		PolygonShape asteroidShape = new PolygonShape();
		asteroidShape.setAsBox(2, 2);
		
		m_body.createFixture(asteroidShape, 1);
		m_body.setAngularVelocity(2);
		
		m_body.setUserData(this);
	}
	
}
