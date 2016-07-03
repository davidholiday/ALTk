package com.projectvalis.altk.noc.ch5;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.init.internalFrameDark;
import com.projectvalis.altk.noc.ch1.BallRunner;
import com.projectvalis.altk.noc.ch1.BouncingBall;
import com.projectvalis.altk.noc.ch1.Element;
import com.projectvalis.altk.noc.ch1.ElementPanel;
import com.projectvalis.altk.noc.ch1.Vector;

public class BallRunner2 extends internalFrameDark {

	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(BallRunner.class.getName());
	
	private List<Element> elementL = 
			Collections.synchronizedList(new ArrayList<Element>());
	
	private ElementPanel ballPanel;
	
	
	/**
	 * position and create the window and get the ball rolling -er- bouncing
	 */
	public BallRunner2() {
	    Vec2 gravityVector = new Vec2(0, -10);
		World world = new World(gravityVector);
	    
		setLocation(800, 200);	
		
		Color[] colorArr = new Color[5];
		colorArr[0] = GUI.mustardC;
		colorArr[1] = GUI.orangeC;
		colorArr[2] = GUI.purpleC;
		colorArr[3] = GUI.redC;
		colorArr[4] = GUI.tealC;
	
		Random randy = new Random();
		
		for (int i = 0; i < 10; i ++) {
			int diameterI = randy.nextInt(100);
			int colorIndexI = randy.nextInt(5);		
			Color fillColor = colorArr[colorIndexI];
			
			Element ball = new BouncingBall(new Vector(25 + (i * 50), 25), 
									new Vector(0, 0), 
									new Vector(0, 0), 
									Color.black, 
									fillColor, 
									diameterI, 
									diameterI,
									diameterI);		

			elementL.add(ball);
			
		}
		
			ballPanel = new ElementPanel(elementL);
			add(ballPanel);	
		
		
		this.attach(true);
		this.setTitle("Bouncing Ball JBox2D Demo");
		animate();
	}
	
	
	

	private void animate() {
		
	}

	
	

}

