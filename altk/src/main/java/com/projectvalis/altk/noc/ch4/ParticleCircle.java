package com.projectvalis.altk.noc.ch4;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.noc.ch1.Element;
import com.projectvalis.altk.noc.ch1.Vector;
import com.projectvalis.altk.noc.ch3.UssTriangle;

public class ParticleCircle extends ParticleAbstract {

	private static final Logger LOGGER = 
			LoggerFactory.getLogger(ParticleCircle.class.getName());
	
	public ParticleCircle() {
		this.massD = widthD;
		this.heightD = widthD;		
		this.widthD = 50;
	}

	
	/**
	 * full control constructor. controls size and color of the shape
	 * 
	 * @param location
	 * 		where the thing starts in the window
	 * @param velocity
	 * 		what the rate of change of location for the thing should be
	 * @param strokeColor
	 * 		color of the outline of the circle
	 * @param fillColor
	 * 		color of the interior of the circle
	 * @param width
	 * 		horizontal size of the circle
	 * @param height
	 * 		vertical size of the circle
	 * @param mass
	 * 		indicator of mass in the physics sense
	 * @param lifeForce
	 * 		sets the default value for how much life this particle has to live.
	 * 
	 */
	public ParticleCircle(Vector location, 
						  Vector velocity, 
						  Vector acceleration, 
						  Color strokeColor, 
						  Color fillColor, 
						  double width,
						  double height, 
						  double mass,
						  int lifeForce) {
		
		this.locationV = location;
		this.velocityV = velocity;
		this.accelerationV = acceleration;
		this.strokeColorC = strokeColor;
		this.fillColorC = fillColor;
		this.widthD = width;
		this.heightD = height;
		this.massD = mass;	
		this.setLifeForce(lifeForce);
	}
	
	public void update(int panelWidth, int panelHeight) {
		super.update(panelWidth, panelHeight);
		this.subtractFromLifeForce(5);		
	}
	
	@Override
	protected void checkEdges(int panelWidth, int panelHeight) { /* noop */ }

	
	@Override
	protected void renderPresentation(Graphics2D g2) {
		if (!this.getIsAliveFlag()) { return; }
		
		double ballLocationX_D = this.getLocation().getLeft();
		double ballLocationY_D = this.getLocation().getRight();
				
		Ellipse2D ballE2D = new Ellipse2D.Double(ballLocationX_D, 
				ballLocationY_D,
				this.widthD,
				this.heightD);

		// update center point variable
		this.centerV = 
				new Vector(ballE2D.getCenterX(), ballE2D.getCenterY());
		
		g2.setColor(this.fillColorC);
		g2.fill(ballE2D);
		
		g2.setColor(this.strokeColorC);
		g2.setStroke(new BasicStroke(4));

		//g2.draw(ballE2D);
		//return ballE2D;
	}

}
