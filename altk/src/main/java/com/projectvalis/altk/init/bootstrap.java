package com.projectvalis.altk.init;

import java.util.logging.*;

import javax.swing.SwingUtilities;

import com.projectvalis.altk.jbox2d.lab.TestbedRunner;


/**
 * bootstraps the program
 * 
 * <p> NOTE toolbar icons are licensed CC by 3.0 and were downloaded from 
 * www.flaticon.com. a copy of the license can be viewed at
 * http://creativecommons.org/licenses/by/3.0/
 * </p>
 * 
 * <p> if executing on a linux machine, use -Dsun.java2d.opengl=True to
 * activate graphics acceleration. 
 * </p>
 * 
 * @author funktapuss
 *
 */
public class bootstrap {
	private static final Logger LOGGER = 
			Logger.getLogger(bootstrap.class.getName());
	public static GUI gui = new GUI();
	
	public static void main (String args[]) {
		LOGGER.setLevel(Level.INFO);
	
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				// sets graphstream renderer to one that supports the entire 
				// CSS ref sheet
				System.setProperty("org.graphstream.ui.renderer",
		                "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
				
				//new TestbedRunner().bootstrapTestBedRunner();
				gui.buildGUI();	
			}
			
		});
		
	}
	
}