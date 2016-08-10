package com.projectvalis.altk.noc.ch5.asteroids;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import com.projectvalis.altk.noc.ch5.ManagedElementModel;

public class ManagedAsteroidModel extends ManagedElementModel{

	public ManagedAsteroidModel(Vec2 startPosition, 
								Vec2 linearVelocity,
								Vec2 angularVelocity, 
								float density, 
								float restitution,
								float friction,
								float heading) {
		
		super(startPosition, 
			  linearVelocity, 
			  angularVelocity, 
			  density, 
			  restitution,
			  friction,
			  heading);
		
	}

	@Override
	protected void makeBody(World world) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void makeShape(World world) {
		// TODO Auto-generated method stub
		
	}

}
