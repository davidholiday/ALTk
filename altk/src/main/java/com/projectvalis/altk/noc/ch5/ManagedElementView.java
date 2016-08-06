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
	
	protected Stroke m_stroke;
	protected Color m_strokeColor;
	protected Color m_fillColor;

	public ManagedElementView(int strokeWidth,
			                  Color strokeColor,
			                  Color fillColor) {
		
		m_stroke = new BasicStroke(strokeWidth);
		m_strokeColor = strokeColor;
		m_fillColor = fillColor;
	}
	
	public Stroke getStroke() {
		return m_stroke;
	}

	public void setStroke(Stroke stroke) {
		m_stroke = stroke;
	}

	public Color getStrokeColorC() {
		return m_strokeColor;
	}

	public void setStrokeColorC(Color strokeColorC) {
		this.m_strokeColor = strokeColorC;
	}

	public Color getFillColorC() {
		return m_fillColor;
	}

	public void setFillColorC(Color fillColorC) {
		this.m_fillColor = fillColorC;
	}

	
	/**
	 * method to draw the element onto the graphics context 
	 * 
	 * @param g2
	 */
	protected abstract void renderPresentation(
			Graphics2D g2, Vec2 posVector, Vec2 sizeVector);


	
}
