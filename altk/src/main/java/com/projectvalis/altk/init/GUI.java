package com.projectvalis.altk.init;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.desktoppane.WebDesktopPane;
import com.alee.laf.toolbar.WebToolBar;
import com.alee.utils.SwingUtils;
import com.jd.swing.custom.component.button.GlossyButton;
import com.jd.swing.util.Theme;
import com.jd.swing.custom.component.button.*;

import bsh.Console;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.util.JConsole;

/**
 * creates the GUI for the program.
 * 
 * @author funktapuss
 *
 */
public class GUI extends JFrame {

	private static final Logger LOGGER = Logger.getLogger(GUI.class.getName());

	// define some custom colors
	public static final java.awt.Color purpleC = new java.awt.Color(46, 9, 39);
	public static final java.awt.Color redC = new java.awt.Color(217, 0, 0);
	public static final java.awt.Color orangeC = new java.awt.Color(255, 45, 0);
	
	public static final java.awt.Color mustardC = 
			new java.awt.Color(255, 140,0);
	
	public static final java.awt.Color tealC = 
			new java.awt.Color(4, 117, 111);
	
	public static final java.awt.Color charcoalDarkC = 
			new java.awt.Color(0, 0,10);
	
	public static final java.awt.Color charcoalC = 
			new java.awt.Color(34, 34, 34);
	
	public static final java.awt.Color charcoalLightC = 
			new java.awt.Color(102, 102, 102);

	public static final String purpleHS = "#2E0927";
	public static final String redHS = "#D90000";
	public static final String orangeHS = "#FF2D00";
	public static final String mustardHS = "#FF8C00";
	public static final String tealHS = "#04756F";
	public static final String charcoalDarkHS = "#00000A";
	public static final String charcoalHS = "#222222";
	public static final String charcoalLightHS = "#666666";

	// root pane for all the internal frames
	public static WebDesktopPane rootPaneWDP = null;

	// global position index variable used by the internal frames
	// so new ones don't accidentally plop themselves down atop
	// ones that are already there.
	public static int internalFramePosIndexI = 1;

	/**
	 * constructor that sets Nimbus as the default l/f
	 */
	public GUI() {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.

		}

		// no need to 'install' web look and feel to make use of the widgets we
		// need
		//WebLookAndFeel.install();

		// to enable the dual use of Nimbus and WLAF
		WebLookAndFeel.initializeManagers();

//		 // Determine what the default GraphicsDevice can support.
//		 GraphicsEnvironment ge =
//		 GraphicsEnvironment.getLocalGraphicsEnvironment();
//		 GraphicsDevice gd = ge.getDefaultScreenDevice();
//		
//		 boolean isUniformTranslucencySupported =
//		 gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT);
//		 boolean isPerPixelTranslucencySupported =
//		 gd.isWindowTranslucencySupported(WindowTranslucency.PERPIXEL_TRANSLUCENT);
//		 boolean isShapedWindowSupported =
//		 gd.isWindowTranslucencySupported(WindowTranslucency.PERPIXEL_TRANSPARENT);
//		
//		 LOGGER.info(isUniformTranslucencySupported + " " +
//		 isPerPixelTranslucencySupported +
//		 " " + isShapedWindowSupported);

	}

	/**
	 * builds the GUI
	 */
	public void buildGUI() {

		// define basic frame attributes
		this.setMinimumSize(new Dimension(800, 600));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("ALTk");

		// load background image
		InputStream inStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("images/valis_background_bp.png");

		// create desktop pane and add background image to it
		// WebDesktopPane rootPaneWDP = new WebDesktopPane() {
		rootPaneWDP = new WebDesktopPane() {

			private Image image;
			{

				try {
					image = ImageIO.read(inStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};

		// load frame icon
		InputStream inStream2 = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("images/VALISicon_flat_rounded2.jpg");

		Image valisIcon;
		{

			try {
				valisIcon = ImageIO.read(inStream2);
				setIconImage(valisIcon);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// create toolbar
		WebToolBar toolbarWTB = new WebToolBar(WebToolBar.VERTICAL);

		toolbarWTB.setBottomBgColor(new java.awt.Color(0, 0, 0, 25));
		toolbarWTB.setTopBgColor(new java.awt.Color(0, 0, 0, 75));
		// toolbarWTB.setBackground(new java.awt.Color(0,0,0,50));
		toolbarWTB.setOpaque(false);
		toolbarWTB.setMargin(5);
		toolbarWTB.setSpacing(10);
		// toolbarWTB.setFloatable(false);

		// create toolbar buttons
		//
		// console button
		InputStream inStreamIcon = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("images/console4a.png");

		ImageIcon imgIcon = null;

		try {
			imgIcon = new ImageIcon(ImageIO.read(inStreamIcon));
			inStreamIcon.close();
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
			e.printStackTrace();
		}

		GlossyButton consoleGB = new GlossyButton("",
				ButtonType.BUTTON_ROUNDED_RECTANGLUR,
				Theme.GLOSSY_OLIVEGREEN_THEME, Theme.GLOSSY_LIGHTGREEN_THEME,
				Theme.GLOSSY_OLIVEGREEN_THEME);

		consoleGB.setIcon(imgIcon);
		consoleGB.setSize(new Dimension(64, 64));
		consoleGB.setFocusable(false);

		// consoleGB.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// new bsInternalFrame();
		//
		// }
		//
		// });

		consoleGB.addActionListener((e) -> {
			new bsInternalFrame();
		});

		// // console button
		// inStreamIcon =
		// Thread.currentThread().getContextClassLoader().
		// getResourceAsStream("images/connections1.png");
		//
		// try {
		// imgIcon = new ImageIcon(ImageIO.read(inStreamIcon));
		// inStreamIcon.close();
		// } catch (IOException e) {
		// LOGGER.severe(e.getMessage());
		// e.printStackTrace();
		// }
		//
		// GlossyButton butt2 = new GlossyButton("",
		// ButtonType.BUTTON_ROUNDED_RECTANGLUR,
		// Theme.GLOSSY_DARKRED_THEME,
		// Theme.GLOSSY_RED_THEME,
		// Theme.GLOSSY_DARKRED_THEME);
		// butt2.setIcon(imgIcon);
		// butt2.setSize(new Dimension(64, 64));
		// butt2.setFocusable(false);

		//
		// GlossyButton butt3 = new GlossyButton("",
		// ButtonType.BUTTON_ROUNDED_RECTANGLUR,
		// Theme.GLOSSY_LIME_THEME,
		// Theme.GLOSSY_LIGHTORANGE_THEME,
		// Theme.GLOSSY_ORANGE_THEME);
		// butt3.setIcon(imgIcon);
		// butt3.setSize(new Dimension(64, 64));
		// butt3.setFocusable(false);
		//
		//
		// GlossyButton butt4 = new GlossyButton("",
		// ButtonType.BUTTON_ROUNDED_RECTANGLUR,
		// Theme.GLOSSY_PURPLE_THEME,
		// Theme.GLOSSY_LAVENDER_THEME,
		// Theme.GLOSSY_PURPLE_THEME);
		// butt4.setIcon(imgIcon);
		// butt4.setSize(new Dimension(64, 64));
		// butt4.setFocusable(false);

		// add buttons to toolbar
		toolbarWTB.add(consoleGB);
		// toolbarWTB.add(butt2);
		// toolbarWTB.add(butt3);
		// toolbarWTB.add(butt4);

		// add components to their respective parent components
		this.getContentPane().add(toolbarWTB, BorderLayout.EAST);
		this.getContentPane().add(rootPaneWDP);

		// showtime!
		setVisible(true);

		// add a terminal instance
		// we have to do this after the frame is visible to ensure it is able
		// to grab focus
		new bsInternalFrame();

	}

}
