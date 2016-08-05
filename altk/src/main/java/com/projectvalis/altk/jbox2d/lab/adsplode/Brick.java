package com.projectvalis.altk.jbox2d.lab.adsplode;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.projectvalis.altk.jbox2d.lab.asteroids.WorldElement;

public class Brick extends WorldElement {

	public Brick(Body body, Vec2 shapeSize) {
		super(body);
		PolygonShape brickShape = new PolygonShape();
		brickShape.setAsBox(shapeSize.x, shapeSize.y);
		m_body.createFixture(brickShape, 1);		
		m_body.setUserData(this);
	}

}
