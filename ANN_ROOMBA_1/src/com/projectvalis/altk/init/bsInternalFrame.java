package com.projectvalis.altk.init;

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
				Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/images/console4a.png");	
		
		ImageIcon imgIcon1 = null;
		try {
			imgIcon1 = new ImageIcon(ImageIO.read(inStream1));
		} catch (IOException e) {
			e.printStackTrace();		

		}
		

		// create a beanshell instance	
		JConsole bsConsole = new JConsole();
		Interpreter bsInterp = new Interpreter(bsConsole);	
		new Thread(bsInterp).start();	
		this.getContentPane().add(bsConsole);
		
		// set up internal frame
		setFrameIcon(imgIcon1);
		setClosable(true);
		setSize(320, 200);
		setLocation(250, 250);	
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setVisible(true);
		
		
		// add this to the desktop
		bootstrap.gui.rootPaneWDP.add(this);		
		
	}
	
}