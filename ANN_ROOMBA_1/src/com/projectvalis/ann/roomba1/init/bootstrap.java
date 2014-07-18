package com.projectvalis.ann.roomba1.init;


import java.util.logging.*;


public class bootstrap {
	private static final Logger LOGGER = Logger.getLogger(bootstrap.class.getName());
	public static GUI gui = new GUI();
	
	public static void main (String args[]) {
		LOGGER.setLevel(Level.INFO);
		gui.buildGUI_WLAF();
		//gui.buildGUI_stockJava();
		
		
	}
	
}