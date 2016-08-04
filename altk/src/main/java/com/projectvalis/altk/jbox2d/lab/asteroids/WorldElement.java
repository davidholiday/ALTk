package com.projectvalis.altk.jbox2d.lab.asteroids;

import org.jbox2d.dynamics.Body;

public abstract class WorldElement {

	public final Body m_body;
	public boolean m_selfDestruct = false;
	
	public WorldElement(Body body) {
		m_body = body;
	}
	
}
