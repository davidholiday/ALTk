package com.projectvalis.ann.roomba1.init;

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

import javafx.scene.paint.Color;

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

public class GUI extends JFrame {
	
	private static final Logger LOGGER = Logger.getLogger(GUI.class.getName());
	
	
	
	
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
		
		
		//WebLookAndFeel.install();
		
	}
	
	
	public void buildGUI_WLAF() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		radialPanel rp = new radialPanel();
        getRootPane().setGlassPane(rp);  
        //rp.setVisible(true); 

		//com.sun.awt.AWTUtilities.setWindowOpacity(this, 0.5f);
		
		java.awt.Color purpleC = new java.awt.Color(46, 9, 39);
		java.awt.Color redC = new java.awt.Color(217, 0, 0);
		java.awt.Color orangeC = new java.awt.Color(255, 45, 0);
		java.awt.Color mustardC = new java.awt.Color(255, 140, 0);
		java.awt.Color tealC = new java.awt.Color(4, 117, 111);
		java.awt.Color charcoalDarkC = new java.awt.Color(0, 0, 10);
		java.awt.Color charcoalC = new java.awt.Color (34,34,34);
		
		InputStream inStream = 
					Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/images/valis_background_bp.png");
		
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
		
		
		
	
		JInternalFrame jif1 = new JInternalFrame("test");
		
		
		
		jif1.setClosable(true);
		jif1.getContentPane().setBackground(charcoalC);
		jif1.setSize(320, 200);
		jif1.setLocation(250, 250);
		
		
		
		InputStream inStream1 = 
				Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/images/images.jpeg");
		
		ImageIcon imgIcon1 = null;
		
		try {
			imgIcon1 = new ImageIcon(ImageIO.read(inStream1));
		} catch (IOException e) {
			e.printStackTrace();		

		}
				
		jif1.setFrameIcon(imgIcon1);
		jif1.setIconifiable(true);
		jif1.setMaximizable(true);
		jif1.setResizable(true);
		jif1.setVisible(true);
		

			
		//rootPaneWDP.setVisible(true);
		//rootPaneWDP.setOpaque(false);
        final WebInternalFrame internalFrame = new WebInternalFrame ( "Web frame", true, true, true, true );
        final WebInternalFrame internalFrame1 = new WebInternalFrame ( "Web frame", true, true, true, true );
        final WebInternalFrame internalFrame2 = new WebInternalFrame ( "Web frame", true, true, true, true );
        final WebInternalFrame internalFrame3 = new WebInternalFrame ( "Web frame", true, true, true, true);
        //internalFrame.setOpaque(false);
        
		InputStream inStream2 = 
				Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/images/github-icon.png");
		
		ImageIcon imgIcon = null;
		
		try {
			imgIcon = new ImageIcon(ImageIO.read(inStream2));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		
//		WebDynamicMenu wdMenu = new WebDynamicMenu();
//		wdMenu.addItem(new WebDynamicMenuItem(imgIcon));
//		wdMenu.setWindowOpaque(false);
	
		
		
	
        internalFrame.setFrameIcon (imgIcon);
        internalFrame.setVisible(true);
        internalFrame.setSize(320, 200);
        internalFrame.getContentPane().setBackground(charcoalC);
        internalFrame.setBackground(redC);
        
        internalFrame.setOpaque(false);
        // makes the title bar go away --v
        //((javax.swing.plaf.basic.BasicInternalFrameUI)internalFrame.getUI()).setNorthPane(null);
        internalFrame.setLocation(300, 300);
        
        internalFrame1.setFrameIcon (imgIcon);
        internalFrame1.setVisible(true);
        internalFrame1.setSize(320, 200);
        internalFrame1.getContentPane().setBackground(charcoalC);
        internalFrame1.setBackground(mustardC);
        
        internalFrame2.setFrameIcon(imgIcon);
        internalFrame2.setVisible(true);
        internalFrame2.setSize(320, 200);
        internalFrame2.getContentPane().setBackground(charcoalC);
        internalFrame2.setBackground(orangeC);
        
        internalFrame3.setFrameIcon(imgIcon);
        internalFrame3.setVisible(true);
        internalFrame3.setSize(320, 200);
        internalFrame3.getContentPane().setBackground(charcoalC);
        internalFrame3.setBackground(tealC);
        
        

//        WebInternalFrameUI ifUI = internalFrame.getWebUI();
//        UIManager.put("MenuBar.background", java.awt.Color.orange);
//        SwingUtilities.updateComponentTreeUI(internalFrame);
//        
//        
//try {
//	com.projectvalis.ann.roomba1.util.UIManagerColorKeys.getKeys();
//} catch (Exception e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
//        
        //JLabel label = new JLabel ( "Just an empty frame", JLabel.CENTER );
        //label.setBackground(charcoalDarkC);
        //label.setOpaque ( false );
        //internalFrame.add ( label );
		//rootPaneWDP.add(internalFrame);
		rootPaneWDP.add(jif1);
		//rootPaneWDP.add(internalFrame1);
		//rootPaneWDP.add(internalFrame2);
		//rootPaneWDP.add(internalFrame3);
		
		
//		rootPaneWDP.addMouseListener(new MouseAdapter () {
//			
//			public void mousePressed (final MouseEvent e) {
//				
//				
//				if (SwingUtils.isRightMouseButton(e)) {
//					rp.setVisible(true);
//					wdMenu.showMenu(e.getComponent(), e.getPoint() );
//				}
//				
//			}
//			
//		});
		

		
		
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
		
		WebButton butt = new WebButton();
		butt.setIcon(imgIcon);
		butt.setSize(new Dimension(64, 64));
		butt.setFocusable(false);
		butt.setBottomBgColor(redC);
		butt.setTopBgColor(redC);
		
		
		WebButton butt1 = new WebButton();
		butt1.setIcon(imgIcon);
		butt1.setSize(new Dimension(64, 64));
		butt1.setFocusable(false);
		butt1.setBottomBgColor(orangeC);
		butt1.setTopBgColor(orangeC);
		
		
		WebButton butt2 = new WebButton();
		butt2.setIcon(imgIcon);
		butt2.setSize(new Dimension(64, 64));
		butt2.setFocusable(false);
		butt2.setBottomBgColor(mustardC);
		butt2.setTopBgColor(mustardC);
		
		
		WebButton butt3 = new WebButton();
		butt3.setIcon(imgIcon);
		butt3.setSize(new Dimension(64, 64));
		butt3.setFocusable(false);
		butt3.setBottomBgColor(tealC);
		butt3.setTopBgColor(tealC);
		
		toolbarWTB.add(butt);
		toolbarWTB.add(butt1);
		toolbarWTB.add(butt2);
		toolbarWTB.add(butt3);
		
		toolbarWTB.setMargin(5);
		toolbarWTB.setSpacing(10);
		toolbarWTB.setFloatable(false);
		
		
		//toolbarWTB.setUndecorated(true);
//		
//		
//toolbarWTB.setBorderPainted(false);
//toolbarWTB.setBorder(BorderFactory.createEmptyBorder());
//		toolbarWTB.setBottomBgColor(purpleC);
//		toolbarWTB.setTopBgColor(purpleC);
//
		this.getContentPane().add(toolbarWTB, BorderLayout.EAST);
		
		this.getContentPane().add(rootPaneWDP);	
		setVisible(true);
		
		
		
	
		
	}
	
	
	
	
	public void buildGUI_stockJava() {
		
		java.awt.Color purpleC = new java.awt.Color(46, 9, 39);
		java.awt.Color redC = new java.awt.Color(217, 0, 0);
		java.awt.Color orangeC = new java.awt.Color(255, 45, 0);
		java.awt.Color mustardC = new java.awt.Color(255, 140, 0);
		java.awt.Color tealC = new java.awt.Color(4, 117, 111);
		java.awt.Color charcoalDarkC = new java.awt.Color(0, 0, 10);
		java.awt.Color charcoalC = new java.awt.Color (34,34,34);
		
		
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//JDesktopPane rootPaneJDP = new JDesktopPane();
		
		
		InputStream inStream = 
				Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/images/valis_background_bp.png");
	
		JDesktopPane rootPaneJDP = new JDesktopPane() {
			
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
		
		
		rootPaneJDP.setVisible(true);
		
		JInternalFrame jif1 = new JInternalFrame("test");
		jif1.setSize(320, 200);
		jif1.setLocation(250, 250);
		
		
		
		InputStream inStream1 = 
				Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/images/images.jpeg");
		
		ImageIcon imgIcon = null;
		
		try {
			imgIcon = new ImageIcon(ImageIO.read(inStream1));
		} catch (IOException e) {
			e.printStackTrace();		

		}
				
		jif1.setFrameIcon(imgIcon);
		jif1.setIconifiable(true);
		jif1.setMaximizable(true);
		jif1.setResizable(true);
		jif1.setVisible(true);
		
		//JInternalFrame jif2 = new JInternalFrame();
		//jif2.setSize(320, 200);
		//jif2.setVisible(true);
		
		
		rootPaneJDP.add(jif1);
		//rootPaneJDP.add(jif2)
		
		
		JToolBar jtoolb = new JToolBar() {
			
            @Override //<-- Paint background
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getSize().width, getSize().height);
                super.paintComponent(g);
            }
        };
        
        jtoolb.setOpaque(false);   //<-- Toolbar is non-opaque
        jtoolb.setBackground(new java.awt.Color(0, 0, 0, 80)); //<-- Background color
        jtoolb.setForeground(new java.awt.Color(0,0,0,60));
        
		JButton butt = new JButton();
        butt.setIcon(imgIcon);
		
		JPopupMenu jPop = new JPopupMenu();
		JMenuItem item = new JMenuItem("meow");
		jPop.add(item);
		jPop.add(new JMenuItem("tuna?"));
		JPanel farts = new JPanel();
		farts.add(jPop);
		butt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Component b=(Component)e.getSource();
				Point p=b.getLocationOnScreen();
				jPop.setLocation(p.x - b.getHeight(), p.y);
				
				jPop.setVisible(true);
				
			}
			
		});
		
		jtoolb.add(butt);
		jtoolb.setVisible(true);
		//rootPaneJDP.add(jtoolb, BorderLayout.EAST);
		
		//setContentPane(rootPaneJDP);
		getContentPane().add(rootPaneJDP);
		getContentPane().add(jtoolb, BorderLayout.EAST);
		
		setVisible(true);
	}
	
}