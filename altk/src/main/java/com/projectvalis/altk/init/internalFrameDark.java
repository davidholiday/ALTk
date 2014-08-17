package com.projectvalis.altk.init;

import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;

/*
 * standard internal frame with a charcoal content pane
 */
public class internalFrameDark extends JInternalFrame {
	
	public internalFrameDark() {
				
		// set up internal frame
		setClosable(true);
		//setLocation(50, 50);	
		setDropLocation();
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setSize(640, 480);
		setVisible(true);	
		getContentPane().setBackground(GUI.charcoalC);
		
	}
	
	// adds this to the desktop pane and gives it focus
	public void attach(boolean giveFocus) {
		
		// add this to the desktop
		GUI.rootPaneWDP.add(this);	
		
		// give focus to this frame
		try {
			setSelected(giveFocus);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// checks GUI.internalFramePosIndexI to get the position multiplier
	// that will dictate where to put this frame so it doesn't completely
	// block one that's already there.
	private void setDropLocation() {
		
		int offsetI = 25 * GUI.internalFramePosIndexI;
		int positionI = offsetI + 50;
		
		// update position indexing variable
		int newIndexI = GUI.internalFramePosIndexI + 1;
		GUI.internalFramePosIndexI = (positionI == 550) ? (0) : (newIndexI);

		// set drop location
		setLocation(positionI, positionI);		
	}
	
	
	
}