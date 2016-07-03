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
	
	public ManagedElementModel(World world) { 
		
	};
	
	public BodyDef getBodyDef() { return bodyDef; }
	public void setBodyDef(BodyDef bodyDef) { this.bodyDef = bodyDef; }

	public Body getBody() {	return body; }
	public void setBody(Body body) { this.body = body;}
	
	public Shape getShape() { return shape;	}
	public void setShape(Shape shape) {	this.shape = shape;	}
	
}
