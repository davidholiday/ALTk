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
	
	private BodyDef bodyDef;
	private Body body;
	
	private Shape shape;
	
	private float density;
	private float restitution;
	private float friction;
	
	
	public ManagedElementModel(World world, 
			                   Vec2 startPosition,
			                   float density, 
			                   float restitution, 
			                   float friction) {
		
		makeBody(world, startPosition);
	};
	
	protected abstract void makeBody(World world, Vec2 startPosition);
	
	protected abstract void makeShape(World world, 
			                          float density, 
			                          float restitution, 
			                          float friction);
	
	
	protected BodyDef getBodyDef() { return bodyDef; }
	protected void setBodyDef(BodyDef bodyDef) { this.bodyDef = bodyDef; }

	public Body getBody() {	return body; }
	protected void setBody(Body body) { this.body = body;}
	
	protected Shape getShape() { return shape;	}
	protected void setShape(Shape shape) {	this.shape = shape;	}

	public float getDensity() { return density;	}
	protected void setDensity(float density) {	this.density = density;	}

	public float getRestitution() {	return restitution;	}
	protected void setRestitution(float restitution) {
		this.restitution = restitution;	}

	public float getFriction() { return friction; }
	protected void setFriction(float friction) { this.friction = friction; }
	
}
