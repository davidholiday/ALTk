package com.projectvalis.altk.noc.ch5;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import org.jbox2d.common.Vec2;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.util.Pair;

/**
 * base view manager for all world elements. 
 * 
 * @author snerd
 *
 */
public abstract class ManagedElementView {
	
	protected Stroke stroke = new BasicStroke(2);
	protected Color strokeColorC = GUI.orangeC;
	protected Color fillColorC = GUI.mustardC;

	
	public Stroke getStroke() {
		return stroke;
	}

	public void setStroke(Stroke stroke) {
		this.stroke = stroke;
	}

	public Color getStrokeColorC() {
		return strokeColorC;
	}

	public void setStrokeColorC(Color strokeColorC) {
		this.strokeColorC = strokeColorC;
	}

	public Color getFillColorC() {
		return fillColorC;
	}

	public void setFillColorC(Color fillColorC) {
		this.fillColorC = fillColorC;
	}

	
	/**
	 * method to draw the element onto the graphics context 
	 * 
	 * @param g2
	 */
	protected abstract void renderPresentation(
			Graphics2D g2, Vec2 posVector, Vec2 sizeVector);


	
}
