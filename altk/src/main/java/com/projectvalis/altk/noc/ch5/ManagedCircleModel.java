package com.projectvalis.altk.noc.ch5;

import org.jbox2d.dynamics.World;

public class ManagedCircleModel extends ManagedElementModel {

	public ManagedCircleModel(World world, 
			                  float density, 
			                  float restitution,
			                  float friction) {
		
		super(world, density, restitution, friction);
	}



}
