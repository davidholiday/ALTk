package com.projectvalis.altk.noc.ch4;

import com.projectvalis.altk.noc.ch1.Element;

public abstract class ParticleAbstract extends Element {
	private int lifeForceI = 100; 
	private boolean isAliveB = true;
	
	public int getLifeForce() {
		return lifeForceI;
	}
	
	public void subtractFromLifeForce(int subtractBy) {
		lifeForceI -= subtractBy;
		checkLifeForce();
	}
	
	public void addToLifeForce(int addBy) {
		lifeForceI += addBy;
		checkLifeForce();
	}
	
	public void setLifeForce(int newLifeForceI) {
		lifeForceI = newLifeForceI;
		checkLifeForce();
	}

	public boolean getIsAliveFlag() {
		return isAliveB;
	}


	private void checkLifeForce() {
		isAliveB = (lifeForceI > 0) ? (true) : (false);
	}
	
	
	
}
