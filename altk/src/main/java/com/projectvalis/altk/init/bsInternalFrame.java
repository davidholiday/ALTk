package com.projectvalis.altk.init;

import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.util.JConsole;


public class bsInternalFrame extends internalFrameDark {
	
	public bsInternalFrame() {
		
		// load image for use as internal frame icon
		InputStream inStream1 = 
				Thread.currentThread().getContextClassLoader().getResourceAsStream("images/console4a.png");	
		
		ImageIcon imgIcon1 = null;
		try {
			imgIcon1 = new ImageIcon(ImageIO.read(inStream1));
		} catch (IOException e) {
			e.printStackTrace();		

		}
		

		// create a beanshell instance	
		enhancedJConsole bsConsole = new enhancedJConsole();
		Interpreter bsInterp = new Interpreter(bsConsole);	
		
		// import scripts and set classpath
		String importCmdS = "importCommands(" + "\"" + "/" + "\"" + ")";
		String importPkgS = "import com.projectvalis.altk.init.*";
		
		try {
			bsInterp.eval(importCmdS);
			bsInterp.eval(importPkgS);
			
			// setup the beanshell shared hashtable if it isn't already initialized.
			// this will enable sharing of variables accross shell instances.
			if (bsInterp.get("bsh.shared.varHT") == null) {
				bsInterp.set("bsh.shared.varHT", new Hashtable());
			}
			
		} catch (EvalError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		// give interpreter its own thread and add console to internal frame
		new Thread(bsInterp).start();	
		this.getContentPane().add(bsConsole);
		
	
		
		// add icon and title to frame
		setFrameIcon(imgIcon1);
		setTitle("terminal");
		
		// add to desktop pane and give it focus
		attach();
		
	}
	
}