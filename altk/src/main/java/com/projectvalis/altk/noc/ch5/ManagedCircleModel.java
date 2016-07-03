package com.projectvalis.altk.noc.ch5;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

public class ManagedCircleModel extends ManagedElementModel {

	float m_radius;
	
	public ManagedCircleModel(World world, 
			                  Vec2 startPosition, 
			                  float density,
			                  float restitution, 
			                  float friction, 
			                  int radius) {
		
		super(world, startPosition, density, restitution, friction);
		m_radius = radius;
		makeShape(world, density, restitution, friction);
	}

	
	public float getRadius() {
		return m_radius;
	}
	
	
	@Override
	protected void makeBody(World world, Vec2 startPosition) {
		BodyDef circleBodyDef = new BodyDef();
		circleBodyDef.setPosition(startPosition);
		circleBodyDef.setType(BodyType.DYNAMIC);
		
		Body circleBody = world.createBody(circleBodyDef);
		this.setBody(circleBody);
	}


	@Override
	protected void makeShape(World world, 
			                 float density, 
			                 float restitution,
			                 float friction) {
		
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(m_radius);
		
        this.getBody().createFixture(circleShape, density);		
        this.getBody().getFixtureList().setRestitution(restitution);
        this.getBody().getFixtureList().setFriction(friction);
	}



	

}
