package com.projectvalis.altk.noc.ch5.bouncyball;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;

import com.projectvalis.altk.noc.ch5.ManagedElementModel;


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
		
		super(startPosition, null, null, density, restitution, friction);	
		m_jboxSizeVector = jboxSizeVector;
	}

	
	/**
	 * 
	 * @param world
	 */
	@Override
	protected void makeBody(World world) {
        BodyDef wallBodyDef = new BodyDef();
        wallBodyDef.setPosition(this.m_startPosition);
        m_body = world.createBody(wallBodyDef);
	}

	
	/**
	 * 
	 * @param world
	 */
	@Override
	protected void makeShape(World world) {
		PolygonShape edgeWallShape = new PolygonShape();
		
        edgeWallShape.setAsBox(
        		m_jboxSizeVector.x, m_jboxSizeVector.y);
        
        m_body.createFixture(edgeWallShape, 0);	
	}

	
}
