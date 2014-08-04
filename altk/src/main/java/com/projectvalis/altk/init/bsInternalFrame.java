package com.projectvalis.altk.init;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.util.JConsole;


public class bsInternalFrame extends JInternalFrame {
	
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
		
		String importCmdS = "importCommands(" + "\"" + "/" + "\"" + ")";
		
		try {
			bsInterp.eval(importCmdS);
		} catch (EvalError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		new Thread(bsInterp).start();	
		this.getContentPane().add(bsConsole);
		
		// set up internal frame
		setFrameIcon(imgIcon1);
		setTitle("terminal");
		setClosable(true);
		setSize(450, 300);
		setLocation(50, 50);	
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setVisible(true);		
		
		// add this to the desktop
		bootstrap.gui.rootPaneWDP.add(this);	
		
		// give focus to this frame
		try {
			setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}