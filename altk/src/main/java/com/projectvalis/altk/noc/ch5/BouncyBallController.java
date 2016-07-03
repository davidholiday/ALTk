package com.projectvalis.altk.noc.ch5;


import java.util.List;

import org.jbox2d.common.Vec2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.noc.ch1.BallRunner;

public class BouncyBallController extends ManagedElementController {

	


	public BouncyBallController(Vec2 gravityVector, 
			                    Vec2 windowSize,
			                    Vec2 windowLocation, 
			                    List<ManagedElementPair> managedPairList) {
		
		super(gravityVector, windowSize, windowLocation, managedPairList);

	}

	private static final Logger LOGGER = 
			LoggerFactory.getLogger(BallRunner.class.getName());
	

	
	
	/**
	 * position and create the window and get the ball rolling -er- bouncing
	 */
//	public BouncyBallController() {
//	    Vec2 gravityVector = new Vec2(0, -10);
//		World world = new World(gravityVector);
//	    
//		setLocation(800, 200);	
//		
//		Color[] colorArr = new Color[5];
//		colorArr[0] = GUI.mustardC;
//		colorArr[1] = GUI.orangeC;
//		colorArr[2] = GUI.purpleC;
//		colorArr[3] = GUI.redC;
//		colorArr[4] = GUI.tealC;
//	
//		Random randy = new Random();
//		
//		for (int i = 0; i < 10; i ++) {
//			int diameterI = randy.nextInt(100);
//			int colorIndexI = randy.nextInt(5);		
//			Color fillColor = colorArr[colorIndexI];
//			
//			Element ball = new BouncingBall(new Vector(25 + (i * 50), 25), 
//									new Vector(0, 0), 
//									new Vector(0, 0), 
//									Color.black, 
//									fillColor, 
//									diameterI, 
//									diameterI,
//									diameterI);		
//
//			elementL.add(ball);
//			
//		}
//		
//			ballPanel = new ElementPanel(elementL);
//			add(ballPanel);	
//		
//		
//		this.attach(true);
//		this.setTitle("Bouncing Ball JBox2D Demo");
//		runSimulation();
//	}
	


	
	

}

