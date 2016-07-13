package com.projectvalis.altk.noc.ch5;

import java.awt.Graphics2D;

import org.jbox2d.common.Vec2;

/**
 * View that renders nothing. Used for invisible boundaries.
 * 
 * @author snerd
 *
 */
public class ManagedNullView extends ManagedElementView {

	@Override
	protected void renderPresentation(Graphics2D g2,
                                      Vec2 posVector,
			                          Vec2 sizeVector) {
		
		/*NOOP*/
	}

}
