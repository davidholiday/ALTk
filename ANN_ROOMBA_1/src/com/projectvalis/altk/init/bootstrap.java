package com.projectvalis.altk.init;


import java.util.logging.*;

import javax.swing.SwingUtilities;


/**
 * bootstraps the program
 * 
 * <p> NOTE toolbar icons are licensed CC by 3.0 and were downloaded from 
 * www.flaticon.com. a copy of the license can be viewed at
 * http://creativecommons.org/licenses/by/3.0/
 * <p>
 * 
 * @author funktapuss
 *
 */
public class bootstrap {
	private static final Logger LOGGER = Logger.getLogger(bootstrap.class.getName());
	public static GUI gui = new GUI();
	
	public static void main (String args[]) {
		LOGGER.setLevel(Level.INFO);
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				gui.buildGUI();	
			}
			
		});
		
	}
	
}