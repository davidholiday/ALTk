package com.projectvalis.altk.jbox2d.lab.asteroids;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public class UssTriangle extends WorldElement {
	
	public UssTriangle(Body ussTriangleBody) {
		super(ussTriangleBody);

		
		// original swing shape dimensions
		//
		//double x2Points[] = {0, -10, 0, 10};			
		//double y2Points[] = {0, 35, 25, 35};
		
		Vec2[] verticiesRight = new Vec2[3];
		verticiesRight[0] = new Vec2(0, 0);
		verticiesRight[1] = new Vec2(0.5f, -1.75f);
		verticiesRight[2] = new Vec2(0, -1.25f);

		PolygonShape triangleShapeRight = new PolygonShape();
		triangleShapeRight.set(verticiesRight, verticiesRight.length);		
		
		
		Vec2[] verticiesLeft = new Vec2[3];
		verticiesLeft[0] = new Vec2(0, 0);
		verticiesLeft[1] = new Vec2(-0.5f, -1.75f);
		verticiesLeft[2] = new Vec2(0, -1.25f);

		PolygonShape triangleShapeLeft = new PolygonShape();
		triangleShapeLeft.set(verticiesLeft, verticiesLeft.length);			
	
		m_body.createFixture(triangleShapeRight, 1);
		m_body.createFixture(triangleShapeLeft, 1);	
		
		m_body.setUserData(this);
	}
	
}
