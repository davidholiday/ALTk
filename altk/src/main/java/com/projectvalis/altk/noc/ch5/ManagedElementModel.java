package com.projectvalis.altk.noc.ch5;


import org.jbox2d.collision.shapes.Shape;
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
			                   float density, 
			                   float restitution, 
			                   float friction) {
		
		if ((bodyDef == null) || (shape == null)) {
			throw new IllegalStateException(
				"implementors of ManagedElementModel must contain bodydef and "
				+ "shape definitions!");
		}
		
		body = world.createBody(bodyDef);
		body.createFixture(shape, density);
		body.getFixtureList().setRestitution(restitution);
		body.getFixtureList().setFriction(friction);
	};
	
	
	public BodyDef getBodyDef() { return bodyDef; }
	public void setBodyDef(BodyDef bodyDef) { this.bodyDef = bodyDef; }

	public Body getBody() {	return body; }
	public void setBody(Body body) { this.body = body;}
	
	public Shape getShape() { return shape;	}
	public void setShape(Shape shape) {	this.shape = shape;	}

	public float getDensity() { return density;	}
	public void setDensity(float density) {	this.density = density;	}

	public float getRestitution() {	return restitution;	}
	public void setRestitution(float restitution) {
		this.restitution = restitution;	}

	public float getFriction() { return friction; }
	public void setFriction(float friction) { this.friction = friction; }
	
}
