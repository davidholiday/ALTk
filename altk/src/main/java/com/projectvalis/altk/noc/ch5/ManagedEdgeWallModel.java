package com.projectvalis.altk.noc.ch5;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;


/**
 * Model for the static wall that will cause dynamic bodies to bounce off the 
 * edge of the window
 * 
 * @author snerd
 *
 */
public class ManagedEdgeWallModel extends ManagedElementModel {

	
	
	/**
	 * 
	 * @param startPosition
	 * @param density
	 * @param restitution
	 * @param friction
	 */
	public ManagedEdgeWallModel(Vec2 startPosition, 
			                    float density,
			                    float restitution, 
			                    float friction, 
			                    Vec2 jboxSizeVector) {
		
		super(startPosition, density, restitution, friction);	
		this.m_jboxSizeVector = jboxSizeVector;
	}

	
	/**
	 * 
	 * @param world
	 */
	@Override
	protected void makeBody(World world) {
        BodyDef wallBodyDef = new BodyDef();
        wallBodyDef.setPosition(this.m_startPosition);
        wallBodyDef.setType(BodyType.STATIC);
        this.m_body = world.createBody(wallBodyDef);
	}

	
	/**
	 * 
	 * @param world
	 */
	@Override
	protected void makeShape(World world) {
		PolygonShape edgeWallShape = new PolygonShape();
		
        edgeWallShape.setAsBox(
        		this.m_jboxSizeVector.x, this.m_jboxSizeVector.y);
        
        this.m_body.createFixture(edgeWallShape, 0);	
	}

	
}
