package com.projectvalis.altk.noc.ch2;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;

import com.projectvalis.altk.init.GUI;
import com.projectvalis.altk.noc.ch1.Element;
import com.projectvalis.altk.noc.ch1.ElementPanel;
import com.projectvalis.altk.noc.ch1.Vector;

public class GravityBallPanel extends ElementPanel {

	public GravityBallPanel(List<Element> elementL) {
		super(elementL);
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int lastElementIndexI = this.elementL.size() - 1;
		Element element = this.elementL.get(lastElementIndexI);
		//if (!element.getClass().getName().contains("GravityBall")) return;
		
		
		// if the ball color has changed, then we know the mouse is over
		// the ball at click-time
		if (element.fillColorC == GUI.orangeC) {
			GravityBall gravityBall = (GravityBall)element;

			// get the vector betwixt the point at which the mouse was pressed
			// and the current mouse position
			Point p = this.getMousePosition();
			
			if (p == null) return;
			
			Vector currentMousePositionV = new Vector(p.getX(), p.getY());
			
			Vector newBallPositionV = currentMousePositionV.clone();
			newBallPositionV.subtract(mousePressPositionV);
			//newBallPositionV.normalize();
			//newBallPositionV.multiply(0.5);
			
			gravityBall.addToLocationVector(newBallPositionV);
			mousePressPositionV = currentMousePositionV;
			
		}		
	}

}
