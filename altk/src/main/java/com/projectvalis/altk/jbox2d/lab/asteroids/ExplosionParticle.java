package com.projectvalis.altk.jbox2d.lab.asteroids;

import java.util.concurrent.ThreadLocalRandom;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;



public class ExplosionParticle extends WorldElement {

	private int life;
	
	public ExplosionParticle(Body body) {
		super(body);
		
		life = ThreadLocalRandom.current().nextInt(25, 75);
		
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(0.1f);
		m_body.createFixture(circleShape, 0.1f);
		
		float randomLinearVelocityX = getRandomFloat();
		float randomLinearVelocityY = getRandomFloat();
					
		Vec2 linearVelocity = 
				new Vec2(randomLinearVelocityX, randomLinearVelocityY);
		
		body.setLinearVelocity(linearVelocity);
		
	}

	
	public void minusLife() {
		if (life > 0) { life-= 1; }
		else { this.m_selfDestruct = true; }
	}
	
	
	/**
	 * returns (.nextFloat() * 10 * 2) * (1/2 chance of *= -1)
	 * 
	 * @return
	 */
	private static float getRandomFloat() {
		float randomFloat = ThreadLocalRandom.current().nextFloat() * 10 * 2;
		float coinFlip = ThreadLocalRandom.current().nextInt(2);
		if (coinFlip > 0) { randomFloat *= -1; }
		return randomFloat;
		
	}
	
}