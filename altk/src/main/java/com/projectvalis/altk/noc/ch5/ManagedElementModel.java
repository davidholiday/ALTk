package com.projectvalis.altk.noc.ch5;


import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;



/**
 * base model manager for all world elements managed by the physics engine. 
 * provides definition for BodyDefinition and Shape. 
 * 
 * @author snerd
 *
 */
public abstract class ManagedElementModel {
	
	protected BodyDef m_bodyDef;
	protected Body m_body;
	protected Shape m_shape;	
	protected Vec2 m_startPosition;	
	protected Vec2 m_jboxSizeVector;
	protected Vec2 m_linearVelocity;
	protected Vec2 m_angularVelocity;
	protected float m_density;
	protected float m_restitution;
	protected float m_friction;
	

	public ManagedElementModel(Vec2 startPosition,
			                   Vec2 linearVelocity,
			                   Vec2 angularVelocity,
			                   float density, 
			                   float restitution, 
			                   float friction) {
		
	    m_startPosition = startPosition;
	    m_linearVelocity = linearVelocity;
	    m_angularVelocity = angularVelocity;
	    m_density = density;
	    m_restitution = restitution;
	    m_friction = friction;
	};
	
	protected void createInWorld(World world) {
	    makeBody(world);
	    makeShape(world);
	}
	
	protected abstract void makeBody(World world);
	protected abstract void makeShape(World world);
	
	
	protected BodyDef getBodyDef() { return m_bodyDef; }
	protected void setBodyDef(BodyDef bodyDef) { this.m_bodyDef = bodyDef; }

	public Body getBody() {	return m_body; }
	protected void setBody(Body body) { this.m_body = body;}
	
	protected Shape getShape() { return m_shape;	}
	protected void setShape(Shape shape) {	this.m_shape = shape;	}

	public float getDensity() { return m_density;	}
	protected void setDensity(float density) {	this.m_density = density;	}

	public float getRestitution() {	return m_restitution;	}
	protected void setRestitution(float restitution) {
		this.m_restitution = restitution;	}

	public float getFriction() { return m_friction; }
	protected void setFriction(float friction) { this.m_friction = friction; }
	
}
