package com.projectvalis.altk.jbox2d.lab.adsplode;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;

import com.projectvalis.altk.jbox2d.lab.asteroids.SquareAsteroid;
import com.projectvalis.altk.jbox2d.lab.asteroids.WorldElement;


public class AdSplodeTestRun extends TestbedTest {

	private List<Vec2> m_paddleVecList = new ArrayList<>();
	private List<Body> m_chainBodyList = new ArrayList<>();
	private List<WorldElement> m_brickList = new ArrayList<>();
	private boolean m_chainDrawing = false;
	
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
		
		// draw paddle?
		//
		if (super.isMouseTracing()) {

			if (!m_chainDrawing) {
				destroyPreviousChain();
				m_chainDrawing = true;
			}
			
			Vec2 mouseTracerPosition = super.getMouseTracerPosition().clone();

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
		

		
		
		
	}

	
	/**
	 * 
	 */
	private void createChainLink() {		
		ChainShape chainShape = new ChainShape();	
		Vec2[] vecArray = new Vec2[m_paddleVecList.size()];
		vecArray = m_paddleVecList.toArray(vecArray);
		
		try {
			chainShape.createChain(vecArray, m_paddleVecList.size());	
			BodyDef chainBodyDef = new BodyDef();	
			Body chainBody = this.getWorld().createBody(chainBodyDef);	
			chainBody.createFixture(chainShape, 1);
			m_chainBodyList.add(chainBody);
		} catch(RuntimeException e) {
			// probably a 'verticies of chain shape are too close together
			// this because we only save chain link pairs at the moment, so
			// if the mouse doubles back onto an existing segment, problems...
		}

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
		
	}
	
	
	
	/**
	 * 
	 */
	private void resetMemberVariables() {
		m_paddleVecList.clear();
		m_chainBodyList.clear();
		m_brickList.clear();
		m_chainDrawing = false;
	}
	
	
	
	/**
	 * 
	 */
	private void initializeEnvironment() {
		Vec2 gravityVector = new Vec2(0, 0);
		this.getWorld().setGravity(gravityVector);
		this.getWorld().setContactListener(new AdSplodeContactListener());
		
		createBoundaries();
		makeBrickLayers(8, new Vec2(-27, 49));
		

	}
	
	
	
	
	/**
	 * 
	 */
	private void createBoundaries() {
             
        // walls
        //
        BodyDef leftWallBodyDef = new BodyDef();
        Vec2 leftWallBodyPositionVector = new Vec2(-31, 0);
        leftWallBodyDef.setPosition(leftWallBodyPositionVector);;
  
        PolygonShape leftWallShape = new PolygonShape();
        leftWallShape.setAsBox(0, 45);
        
        Body leftWallBody = this.getWorld().createBody(leftWallBodyDef);
        leftWallBody.createFixture(leftWallShape, 0);
        
        
        BodyDef rightWallBodyDef = new BodyDef();
        Vec2 rightWallBodyPositionVector = new Vec2(33, 0);
        rightWallBodyDef.setPosition(rightWallBodyPositionVector);;
  
        PolygonShape rightWallShape = new PolygonShape();
        rightWallShape.setAsBox(0, 45);
        
        Body rightWallBody = this.getWorld().createBody(rightWallBodyDef);
        rightWallBody.createFixture(rightWallShape, 0);
        
        
        // ceiling
        //
        BodyDef ceilingBodyDef = new BodyDef();
        Vec2 ceilingBodyPositionVector = new Vec2(0, 51);
        ceilingBodyDef.setPosition(ceilingBodyPositionVector);;
  
        PolygonShape ceilingShape = new PolygonShape();
        ceilingShape.setAsBox(15, 0);
        
        Body ceilingBody = this.getWorld().createBody(ceilingBodyDef);
        ceilingBody.createFixture(ceilingShape, 0);
	}
	
	
	/**
	 * 
	 * @param numLayers
	 */
	private void makeBrickLayers(int dimension, Vec2 position) {
		Vec2 currentPosition = position.clone();
		
		for (int i = 0; i < dimension; i ++) {
			
			for (int k = 0; k < dimension; k ++) {	
				makeBrick(currentPosition);
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
	private void makeBrick(Vec2 position) {
		BodyDef brickBodyDef = new BodyDef();
		brickBodyDef.setType(BodyType.STATIC);
		brickBodyDef.setPosition(position);
		
		Body brickBody = this.getWorld().createBody(brickBodyDef);
		Brick brick = new Brick(brickBody);
		m_brickList.add(brick);
	}
	
	
	
	
}








