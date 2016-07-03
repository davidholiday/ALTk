package com.projectvalis.altk.init;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.util.JConsole;
import bsh.util.NameCompletionTable;

public class bsInternalFrame extends internalFrameDark {

	public bsInternalFrame() {

		// load image for use as internal frame icon
		InputStream inStream1 = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("images/console4a.png");

		ImageIcon imgIcon1 = null;
		try {
			imgIcon1 = new ImageIcon(ImageIO.read(inStream1));
		} catch (IOException e) {
			e.printStackTrace();

		}

		// create a beanshell instance
		enhancedJConsole bsConsole = new enhancedJConsole();

		Interpreter bsInterp = new Interpreter(bsConsole);

		//
		// probably there is a better way to do this
		//

		// import scripts and set classpath
		String importCmdS = "importCommands(" + "\"" + "/scripts" + "\"" + ")";
		String importPkg1S = "import com.projectvalis.altk.init.*";
		String importPkg2S = "import com.projectvalis.altk.util.*";
		String importPkg3S = "import com.projectvalis.altk.algorithm.*";
		String importPkg4S = "import com.projectvalis.altk.noc.ch1.*";
		String importPkg5S = "import com.projectvalis.altk.noc.ch2.*";
		String importPkg6S = "import com.projectvalis.altk.noc.ch3.*";
		String importPkg7s = "import com.projectvalis.altk.noc.ch4.*";
		String importPkg8s = "import com.projectvalis.altk.noc.ch5.*";

		try {
			bsInterp.eval(importCmdS);
			bsInterp.eval(importPkg1S);
			bsInterp.eval(importPkg2S);
			bsInterp.eval(importPkg3S);
			bsInterp.eval(importPkg4S);
			bsInterp.eval(importPkg5S);
			bsInterp.eval(importPkg6S);
			bsInterp.eval(importPkg7s);
			bsInterp.eval(importPkg8s);

			// setup the beanshell shared hashtable if it isn't already
			// initialized.
			// this will enable sharing of variables accross shell instances.
			if (bsInterp.get("bsh.shared.varHT") == null) {
				bsInterp.set("bsh.shared.varHT", new Hashtable());
			}

			bsInterp.set("console", bsConsole);

			// setup tab-to-complete (doesn't work - womp womp)

			// bsInterp.eval("setupNameCompletion()");
			// System.out.println(bsConsole.nameCompletion);
			// bsConsole.setNameCompletion(bsConsole.nameCompletion);
			// System.out.println(bsConsole.nameCompletion);
			//
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