package com.projectvalis.altk.noc.ch5.asteroids;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

import com.projectvalis.altk.noc.ch5.ManagedElementModel;

public class ManagedAsteroidModel extends ManagedElementModel{
	
	public ManagedAsteroidModel(Vec2 startPosition, 
								Vec2 linearVelocity,
								Vec2 shapeSize,
								float angularVelocity, 
								float density, 
								float restitution,
								float friction) {
		
		super(startPosition, 
			  linearVelocity, 
			  angularVelocity, 
			  density, 
			  restitution,
			  friction);
		
		m_jboxSizeVector = shapeSize;
	}

	@Override
	protected void makeBody(World world) {
		BodyDef asteroidBodyDef = new BodyDef();
		asteroidBodyDef.setPosition(this.m_startPosition);
		asteroidBodyDef.setType(BodyType.DYNAMIC);
		
		Body asteroidBody = world.createBody(asteroidBodyDef);
		asteroidBody.setLinearVelocity(m_linearVelocity);
		asteroidBody.setAngularVelocity(m_angularVelocity);
		m_body = asteroidBody;
		m_body.setUserData(this);
	}

	@Override
	protected void makeShape(World world) {
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(m_jboxSizeVector.x, m_jboxSizeVector.y);
		
        m_body.createFixture(polygonShape, this.m_density);		
        m_body.getFixtureList().setRestitution(this.m_restitution);
        m_body.getFixtureList().setFriction(this.m_friction);
		
	}

}
