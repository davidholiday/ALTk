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
		setLocation(50, 50);	
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setSize(640, 480);
		setVisible(true);	
		getContentPane().setBackground(GUI.charcoalC);
		
	}
	
	// adds this to the desktop pane and gives it focus
	public void attach() {
		
		// add this to the desktop
		GUI.rootPaneWDP.add(this);	
		
		// give focus to this frame
		try {
			setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}