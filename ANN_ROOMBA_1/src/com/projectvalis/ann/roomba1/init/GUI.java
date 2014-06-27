package com.projectvalis.ann.roomba1.init;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.desktoppane.WebDesktopPane;
import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.laf.desktoppane.WebInternalFrameUI;

public class GUI extends JFrame {
	
	private static final Logger LOGGER = Logger.getLogger(GUI.class.getName());
	
	public GUI() {
		/*
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
		*/
		
		WebLookAndFeel.install();
		
	}
	
	
	public void buildGUI_WLAF() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		InputStream inStream = 
					Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/images/VALIS_background.jpg");
		
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
		
		
		
		
		//rootPaneWDP.setVisible(true);
		//rootPaneWDP.setOpaque(false);
        final WebInternalFrame internalFrame = new WebInternalFrame ( "Web frame", true, true, true, true );
        internalFrame.setOpaque(false);
        
		InputStream inStream2 = 
				Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/images/images.jpeg");
		
		ImageIcon imgIcon = null;
		
		try {
			imgIcon = new ImageIcon(ImageIO.read(inStream2));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		internalFrame.setBackground(java.awt.Color.red);
        //internalFrame.setFrameIcon (imgIcon);
        internalFrame.setVisible(true);
        internalFrame.setSize(320, 200);


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
        JLabel label = new JLabel ( "Just an empty frame", JLabel.CENTER );
        label.setOpaque ( false );
        internalFrame.add ( label );
		rootPaneWDP.add(internalFrame);
		
		this.getContentPane().add(rootPaneWDP);	
		setVisible(true);
		
	}
	
	
	
	
	public void buildGUI_stockJava() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JDesktopPane rootPaneJDP = new JDesktopPane();
		rootPaneJDP.setVisible(true);
		
		JInternalFrame jif1 = new JInternalFrame("test");
		jif1.setSize(320, 200);
		jif1.setLocation(250, 250);
		
		
		
		InputStream inStream = 
				Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/images/images.jpeg");
		
		ImageIcon imgIcon = null;
		
		try {
			imgIcon = new ImageIcon(ImageIO.read(inStream));
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
		//rootPaneJDP.add(jif2);
		
		
		JToolBar jtoolb = new JToolBar();
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