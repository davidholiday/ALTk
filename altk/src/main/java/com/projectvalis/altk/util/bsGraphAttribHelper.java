package com.projectvalis.altk.util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.DefaultView;
import org.graphstream.ui.swingViewer.View;

public class bsGraphAttribHelper {
	
	// because beanshell is being a butthole 
	// specifically, the method definition for this says 'object' and 
	// beanshell is too fucking stupid to realize that a String is a 
	// subclass of Object. 
	public static void addAttrib (SingleGraph graph, String a1, String a2) {
		graph.addAttribute(a1, a2);
	}

	
	public static void addAttrib (Edge edge, String a1, Integer a2) {
		edge.addAttribute(a1, a2);
	}
	
	
	public static void setAttrib (Edge edge, String a1, String a2 ) {
		edge.setAttribute(a1, a2);
	}
	
	public static void setAttrib (SingleGraph graph, String a1, String a2 ) {
		graph.setAttribute(a1, a2);
	}
	
	
	/*
	 * there's probably a better way to do this...
	 */
	public static void setGlobalNodeAttrib (SingleGraph graph, String a1, String a2) {
		
		for (Node n: graph) {
			
			Object attribO = a2.contentEquals("LABEL") ? n.getId() : a2;
			n.setAttribute(a1, attribO);
			
		}
		
	}
	
	// for locating CSS files for the graph object -- the API seems to only accept URLs
	public static String fileName2URL(String fileName) {
		URL url = Thread.currentThread().getContextClassLoader().getResource("lib/" + fileName);
		return url.toString();
	}
	
	
	
	
}