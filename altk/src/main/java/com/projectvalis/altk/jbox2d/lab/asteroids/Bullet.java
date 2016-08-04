package com.projectvalis.altk.jbox2d.lab.asteroids;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;


import com.projectvalis.altk.util.TrigHelpers;

public class Bullet {

	public final Body m_body;
	
	public Bullet(float headingAngle,
			      Body bulletBody) {
		
		m_body = bulletBody;
		Vec2 velocityVector = TrigHelpers.PolarToVec2(headingAngle, 50);
		m_body.setLinearVelocity(velocityVector);
		CircleShape bulletShape = new CircleShape();
		bulletShape.setRadius(0.1f);
		
		m_body.createFixture(bulletShape, 50);
	}
	
}
