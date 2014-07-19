package com.projectvalis.altk.init;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class radialPanel extends JPanel {
	
    private static final Color BG_COLOR = new Color(0, 0, 0, 90);  
  
    public radialPanel() {  
        setLayout(null);  
        setOpaque(false);  
    } 
	
    @Override  
    protected void paintComponent(Graphics g) {  
        g.setColor(BG_COLOR);  
        g.fillRect(0, 0, getWidth(), getHeight());    
        super.paintComponent(g);  
    } 
	
}