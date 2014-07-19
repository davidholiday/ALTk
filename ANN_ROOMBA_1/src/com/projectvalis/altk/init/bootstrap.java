package com.projectvalis.altk.init;


import java.util.logging.*;


/**
 * bootstraps the program
 * 
 * @author funktapuss
 *
 */
public class bootstrap {
	private static final Logger LOGGER = Logger.getLogger(bootstrap.class.getName());
	public static GUI gui = new GUI();
	
	public static void main (String args[]) {
		LOGGER.setLevel(Level.INFO);
		gui.buildGUI();	
	}
	
}