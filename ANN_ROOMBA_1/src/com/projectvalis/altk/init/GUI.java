package com.projectvalis.altk.init;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.*;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JInternalFrame.JDesktopIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.alee.extended.menu.WebDynamicMenu;
import com.alee.extended.menu.WebDynamicMenuItem;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.desktoppane.WebDesktopPane;
import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.laf.desktoppane.WebInternalFrameUI;
import com.alee.laf.toolbar.ToolbarStyle;
import com.alee.laf.toolbar.WebToolBar;
import com.alee.utils.SwingUtils;
import com.jd.swing.custom.component.button.GlossyButton;
import com.jd.swing.util.Theme;

import com.jd.swing.custom.component.button.*;


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
	public static final java.awt.Color mustardC = new java.awt.Color(255, 140, 0);
	public static final java.awt.Color tealC = new java.awt.Color(4, 117, 111);
	public static final java.awt.Color charcoalDarkC = new java.awt.Color(0, 0, 10);
	public static final java.awt.Color charcoalC = new java.awt.Color (34,34,34);
	
	
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
		    // If Nimbus is not available, you can set the GUI to another look and feel.
			
		}
		
		// no need to 'install' web look and feel to make use of the widgets we need
		// WebLookAndFeel.install();
		
		// to enable the dual use of Nimbus and WLAF
		WebLookAndFeel.initializeManagers();
		
	}
	
	
	/**
	 * builds the GUI
	 */
	public void buildGUI() {
		
		// define basic frame attributes
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		// load background image
		InputStream inStream = 
					Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/images/valis_background_bp.png");
		
		// create desktop pane and add background image to it
		WebDesktopPane rootPaneWDP = new WebDesktopPane() {
			
		    private Image image; {
		    	
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
		
		
		// load test image for use as frame icon
		InputStream inStream1 = 
				Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/images/images.jpeg");	
		ImageIcon imgIcon1 = null;
		
		try {
			imgIcon1 = new ImageIcon(ImageIO.read(inStream1));
		} catch (IOException e) {
			e.printStackTrace();		

		}
		
		// create test internal frame and add it to the root pane
		JInternalFrame jif1 = new JInternalFrame("test");		
		jif1.setFrameIcon(imgIcon1);
		jif1.setClosable(true);
		jif1.getContentPane().setBackground(charcoalC);
		jif1.setSize(320, 200);
		jif1.setLocation(250, 250);	
		jif1.setIconifiable(true);
		jif1.setMaximizable(true);
		jif1.setResizable(true);
		jif1.setVisible(true);
		rootPaneWDP.add(jif1);
		
		
		// create toolbar 
		WebToolBar toolbarWTB = new WebToolBar( WebToolBar.VERTICAL ) {
            
			@Override //<-- Paint background
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getSize().width, getSize().height);
                super.paintComponent(g);
            }	
			
		};
		
		//toolbarWTB.setBottomBgColor(new java.awt.Color(0,0,0,100));
		toolbarWTB.setTopBgColor(new java.awt.Color(0,0,0,75));
		toolbarWTB.setOpaque(false);
		
		
		// create test icon for test toolbar buttons
		InputStream inStream2 = 
				Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/images/github-icon.png");
		
		ImageIcon imgIcon = null;
		
		try {
			imgIcon = new ImageIcon(ImageIO.read(inStream2));
		} catch (IOException e) {
			e.printStackTrace();
		}


		toolbarWTB.setMargin(5);
		toolbarWTB.setSpacing(10);
		toolbarWTB.setFloatable(false);

		
		// create test buttons for toolbar
		//WebButton butt = new WebButton();	
		GlossyButton butt = new GlossyButton("", Theme.GLOSSY_DARKRED_THEME, ButtonType.BUTTON_ROUNDED_RECTANGLUR);
		butt.setIcon(imgIcon);
		butt.setSize(new Dimension(64, 64));
		butt.setFocusable(false);
		//butt.setBottomBgColor(redC);
		//butt.setTopBgColor(redC);
		
		
		//WebButton butt1 = new WebButton();
		GlossyButton butt1 = new GlossyButton("", Theme.GLOSSY_GREEN_THEME, ButtonType.BUTTON_ROUNDED_RECTANGLUR);
		butt1.setIcon(imgIcon);
		butt1.setSize(new Dimension(64, 64));
		butt1.setFocusable(false);
		//butt1.setBottomBgColor(orangeC);
		//butt1.setTopBgColor(orangeC);
			
		
		//WebButton butt2 = new WebButton();
		GlossyButton butt2 = new GlossyButton("", Theme.GLOSSY_ORANGE_THEME, ButtonType.BUTTON_ROUNDED_RECTANGLUR);
		butt2.setIcon(imgIcon);
		butt2.setSize(new Dimension(64, 64));
		butt2.setFocusable(false);
		//butt2.setBottomBgColor(mustardC);
		//butt2.setTopBgColor(mustardC);
		
		
		//WebButton butt3 = new WebButton();
		GlossyButton butt3 = new GlossyButton("", Theme.GLOSSY_METALIC_BLUE_THEME);
		butt3.setIcon(imgIcon);
		butt3.setSize(new Dimension(64, 64));
		butt3.setFocusable(false);
		//butt3.setBottomBgColor(tealC);
		//butt3.setTopBgColor(tealC);
		
		
		//add buttons to toolbar
		toolbarWTB.add(butt);
		toolbarWTB.add(butt1);
		toolbarWTB.add(butt2);
		toolbarWTB.add(butt3);
		
		// add components to their respective parent components
		this.getContentPane().add(toolbarWTB, BorderLayout.EAST);
		this.getContentPane().add(rootPaneWDP);	
		
		
		
		// showtime! 
		setVisible(true);
		
		
	}
	
	
}




