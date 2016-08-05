package com.projectvalis.altk.jbox2d.lab.adsplode;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.projectvalis.altk.jbox2d.lab.asteroids.WorldElement;
import com.projectvalis.altk.util.TrigHelpers;

public class Ball extends WorldElement {

	public Ball(float headingAngle, Body ballBody) {
		super(ballBody);

		Vec2 velocityVector = TrigHelpers.PolarToVec2(headingAngle, 50);
		m_body.setLinearVelocity(velocityVector);
		CircleShape ballShape = new CircleShape();
		ballShape.setRadius(1f);

		m_body.createFixture(ballShape, 10);
		m_body.getFixtureList().setRestitution(1);
		m_body.getFixtureList().setFriction(0);
		m_body.setUserData(this);
	}
	
}
