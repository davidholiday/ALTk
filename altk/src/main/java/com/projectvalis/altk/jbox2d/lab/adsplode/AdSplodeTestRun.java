package com.projectvalis.altk.jbox2d.lab.adsplode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.testbed.framework.TestbedModel;
import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;

import com.projectvalis.altk.jbox2d.lab.asteroids.ExplosionParticle;
import com.projectvalis.altk.jbox2d.lab.asteroids.WorldElement;


public class AdSplodeTestRun extends TestbedTest {

	
	private List<Vec2> m_paddleVecList = new ArrayList<>();	
	private List<WorldElement> m_brickList = new ArrayList<>();	
	private WorldElement m_ball = null;
	
	private List<Body> m_chainBodyList = new ArrayList<>();
	private boolean m_chainDrawing = false;
	private float m_chainDistance = 0;
	
	private List<List<ExplosionParticle>> explosionList = new ArrayList<>();
	
	
	@Override
	public String getTestName() {
		return "AdSplodeTestRun";
	}

	@Override
	public void initTest(boolean arg0) {	
		resetMemberVariables();
		initializeEnvironment();		
	}
	

	@Override
	public void step(TestbedSettings settings) {
		super.step(settings);
		TestbedModel model = getModel();

		// check for explosions
		//
		for (List<ExplosionParticle> epList : explosionList) {
			
			for (ExplosionParticle ep : epList) {
				ep.minusLife();
				if (ep.m_selfDestruct) { this.getWorld().destroyBody(ep.m_body); }			
			}

		}
		
		for (WorldElement we : m_brickList) {
			
			if (we.m_selfDestruct) {
				Vec2 position = we.m_body.getPosition().clone();
				this.getWorld().destroyBody(we.m_body);
				makeExplosion(position);
			}
			
		}
	
		m_brickList = m_brickList.stream()
		            			 .filter(x -> x.m_selfDestruct == false)
		            			 .collect(Collectors.toList());
		
		
		
		// new ball
		//
		if (model.getKeys()['n']) { makeRandomBall(); }

		
		// draw paddle?
		//		
		if (super.isMouseTracing()) {
			Vec2 mouseTracerPosition = super.getMouseTracerPosition().clone();			
			
			if (!m_chainDrawing) {
				destroyPreviousChain();
				m_chainDrawing = true;
			}
			
			
			if (m_paddleVecList.size() == 2) {
				createChainLink();
				m_paddleVecList.remove(0);
			}
			else {
				m_paddleVecList.add(mouseTracerPosition);
			}		
			
		}
		else {
			m_paddleVecList.clear();
			m_chainDrawing = false;
		}
		

		checkBallLinearVelocity();
		
		
	}

	
	/**
	 * 
	 */
	private void createChainLink() {		
		ChainShape chainShape = new ChainShape();	
		Vec2[] vecArray = new Vec2[m_paddleVecList.size()];
		vecArray = m_paddleVecList.toArray(vecArray);
		
		if (!checkPositionsOutOfBounds(vecArray) && m_chainDistance < 15) {
		
			try {
				chainShape.createChain(vecArray, m_paddleVecList.size());	
				BodyDef chainBodyDef = new BodyDef();	
				Body chainBody = this.getWorld().createBody(chainBodyDef);	
				chainBody.createFixture(chainShape, 1);
				m_chainBodyList.add(chainBody);				
				m_chainDistance+= calculateChainLinkDistance(vecArray);
			} catch(RuntimeException e) {
				// probably a 'verticies of chain shape are too close together
				// this because we only save chain link pairs at the moment, so
				// if the mouse doubles back onto an existing segment, problems...
			}
		
		}

	}
	
	
	/**
	 * 
	 * @param chainArray
	 * @return
	 */
	private float calculateChainLinkDistance(Vec2[] chainArray) {
		double xSquared = Math.pow((chainArray[1].x - chainArray[0].x), 2);
		double ySquared = Math.pow((chainArray[1].y - chainArray[0].y), 2);
		double distanceD = Math.sqrt((xSquared + ySquared));
		
		return (float)distanceD;
	}
	
	
	
    /**
     * destroy-in-the-world and clear (if any) contents of m_chainBodyList
     */
	private void destroyPreviousChain() {
		
		if ((!m_chainBodyList.isEmpty())) {
			
			m_chainBodyList.stream()
						   .forEach(x -> this.getWorld().destroyBody(x));
			
			m_chainBodyList.clear();			
		}
		
		m_chainDistance = 0;
	}
	
	
	
	/**
	 * 
	 */
	private void resetMemberVariables() {
		m_paddleVecList.clear();
		m_chainBodyList.clear();
		m_brickList.clear();
		explosionList.clear();
		m_chainDrawing = false;
		m_chainDistance = 0;
		m_ball = null;
	}
	
	
	
	/**
	 * 
	 */
	private void initializeEnvironment() {
		Vec2 gravityVector = new Vec2(0, 0);
		this.getWorld().setGravity(gravityVector);
		this.getWorld().setContactListener(new AdSplodeContactListener());
		
		createBoundaries();
		makeBrickLayers(8, new Vec2(-27, 55));
		makeBrick(new Vec2(1, 61), new Vec2(32, 3));

	}
	
	
	
	
	/**
	 * 
	 */
	private void createBoundaries() {
             
        // walls
        //
        BodyDef leftWallBodyDef = new BodyDef();
        Vec2 leftWallBodyPositionVector = new Vec2(-31, 9);
        leftWallBodyDef.setPosition(leftWallBodyPositionVector);;
  
        PolygonShape leftWallShape = new PolygonShape();
        leftWallShape.setAsBox(0, 55);
        
        Body leftWallBody = this.getWorld().createBody(leftWallBodyDef);
        leftWallBody.createFixture(leftWallShape, 0);
        
        
        BodyDef rightWallBodyDef = new BodyDef();
        Vec2 rightWallBodyPositionVector = new Vec2(33, 9);
        rightWallBodyDef.setPosition(rightWallBodyPositionVector);;
  
        PolygonShape rightWallShape = new PolygonShape();
        rightWallShape.setAsBox(0, 55);
        
        Body rightWallBody = this.getWorld().createBody(rightWallBodyDef);
        rightWallBody.createFixture(rightWallShape, 0);
        
        
        // ceiling
        //
        BodyDef ceilingBodyDef = new BodyDef();
        Vec2 ceilingBodyPositionVector = new Vec2(1, 64);
        ceilingBodyDef.setPosition(ceilingBodyPositionVector);;
  
        PolygonShape ceilingShape = new PolygonShape();
        ceilingShape.setAsBox(32, 0);
        
        Body ceilingBody = this.getWorld().createBody(ceilingBodyDef);
        ceilingBody.createFixture(ceilingShape, 0);
	}
	
	
	/**
	 * 
	 * @param numLayers
	 */
	private void makeBrickLayers(int dimension, Vec2 position) {
		Vec2 currentPosition = position.clone();
		Vec2 size = new Vec2(4, 2);
		
		for (int i = 0; i < dimension; i ++) {
			
			for (int k = 0; k < dimension; k ++) {	
				makeBrick(currentPosition, size);
        		currentPosition.x += 8;			
			}
			
			currentPosition.x = position.x;
			currentPosition.y -= 2;	
		}
		
	}
	
	
	
	/**
	 * 
	 * @param position
	 */
	private void makeBrick(Vec2 position, Vec2 size) {
		BodyDef brickBodyDef = new BodyDef();
		brickBodyDef.setType(BodyType.STATIC);
		brickBodyDef.setPosition(position);
		
		Body brickBody = this.getWorld().createBody(brickBodyDef);
		
		Brick brick = new Brick(brickBody, size);
		m_brickList.add(brick);
	}
	
	
	/**
	 * 
	 * @param mousePosition
	 * @return
	 */
	private boolean checkPositionsOutOfBounds(Vec2[] positionArray) {
				
		for (Vec2 position : positionArray) {
			
			if ((position.y > 20) || (position.x < -31) || (position.x > 34)) {		
				return true;
			}
		
		}
		
		return false;
		
	}
	
	
	/**
	 * 
	 */
	private void makeRandomBall() {
		float randomHeading = ThreadLocalRandom.current().nextFloat();
		randomHeading += ThreadLocalRandom.current().nextInt(2);

		BodyDef bodyDef = new BodyDef();
		bodyDef.setType(BodyType.DYNAMIC);
		Body ballBody = this.getWorld().createBody(bodyDef);
		
		if (m_ball != null) {
			this.getWorld().destroyBody(m_ball.m_body);
		}
		
		m_ball = new Ball(randomHeading, ballBody);
	}
	
	
	
	
	/**
	 * 
	 * @param location
	 */
	private void makeExplosion(Vec2 location) {
		ArrayList<ExplosionParticle> particleList = new ArrayList<>();
		
		for (int i = 0; i < 25; i ++) {
			BodyDef circleBodyDef = new BodyDef();
			circleBodyDef.setPosition(location);
			circleBodyDef.setType(BodyType.DYNAMIC);
						
			Body circleBody = this.getWorld().createBody(circleBodyDef);
			particleList.add(new ExplosionParticle(circleBody));
		}
		
		explosionList.add(particleList);
	}
	
	

	/**
	 * ensures the ball doesn't endlessly bounce between walls
	 * 
	 */
	private void checkBallLinearVelocity() {
		if (m_ball == null) { return;}
		
		if ((m_ball.m_body.getLinearVelocity().y < 1) &&
				(m_ball.m_body.getLinearVelocity().y > -1)) {
			
			Vec2 newLinearVelocity = m_ball.m_body.getLinearVelocity().clone();
			newLinearVelocity.y += 3;
			m_ball.m_body.setLinearVelocity(newLinearVelocity);
		}
	}
	
}








