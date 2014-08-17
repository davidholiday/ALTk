package com.projectvalis.altk.init;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import bsh.EvalError;
import bsh.Interpreter;


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
		String importPkg1S = "import com.projectvalis.altk.init.*";
		String importPkg2S = "import com.projectvalis.altk.util.*";
		String importPkg3S = "import com.projectvalis.altk.algorithm.*";
		
		try {
			bsInterp.eval(importCmdS);
			bsInterp.eval(importPkg1S);
			bsInterp.eval(importPkg2S);
			bsInterp.eval(importPkg3S);
			
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
		
		// add to desktop pane
		attach(true);
		
	}
	
}