package com.projectvalis.altk.noc.ch5.bouncyball;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

import com.projectvalis.altk.noc.ch5.ManagedElementModel;
import com.projectvalis.altk.util.Jbox2dUtils;


/**
 * 
 * @author snerd
 *
 */
public class ManagedCircleModel extends ManagedElementModel {

	private float m_radius;
	
	public ManagedCircleModel(Vec2 startPosition, 
			                  Vec2 linearVelocity,
			                  float density,
			                  float restitution, 
			                  float friction, 
			                  int radius) {
		
		super(startPosition, 
			  linearVelocity, 
			  0, 
			  density, 
			  restitution, 
			  friction);
		
		m_radius = radius;
		m_jboxSizeVector = new Vec2(radius, radius);
	}

	
	public float getRadius() {
		return m_radius;
	}
	
	
	@Override
	protected void makeBody(World world) {
		BodyDef circleBodyDef = new BodyDef();
		circleBodyDef.setPosition(this.m_startPosition);
		circleBodyDef.setType(BodyType.DYNAMIC);
		
		Body circleBody = world.createBody(circleBodyDef);
		circleBody.setLinearVelocity(m_linearVelocity);
		m_body = circleBody;
	}


	@Override
	protected void makeShape(World world) {
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(m_radius);
		
        m_body.createFixture(circleShape, this.m_density);		
        m_body.getFixtureList().setRestitution(this.m_restitution);
        m_body.getFixtureList().setFriction(this.m_friction);
	}



	

}
