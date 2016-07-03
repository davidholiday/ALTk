package com.projectvalis.altk.noc.ch5;

import java.awt.Color;
import java.awt.Graphics2D;

import com.projectvalis.altk.init.GUI;

/**
 * base view manager for all world elements. 
 * 
 * @author snerd
 *
 */
public abstract class ManagedElementView {
	
	public Color strokeColorC = GUI.orangeC;
	public Color fillColorC = GUI.mustardC;

	/**
	 * method to draw the element onto the graphics context 
	 * 
	 * @param g2
	 */
	protected abstract void renderPresentation(Graphics2D g2);

}
