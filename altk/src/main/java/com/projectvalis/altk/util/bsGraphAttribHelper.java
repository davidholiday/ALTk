package com.projectvalis.altk.util;

import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class bsGraphAttribHelper {
	
	// because beanshell is being a butthole 
	// specifically, the method definition for this says 'object' and 
	// beanshell is too fucking stupid to realize that a String is a 
	// subclass of Object. 
	public static void addAttrib (SingleGraph graph, String a1, String a2) {
		graph.addAttribute(a1, a2);
	}

	public static void setAttrib (SingleGraph graph, String a1, String a2 ) {
		graph.setAttribute(a1, a2);
	}
	
	public static void setGlobalNodeAttrib (SingleGraph graph, String a1, String a2) {
		
		for (Node n: graph) {
			
			Object attribO = a2.contentEquals("LABEL") ? n.getId() : a2;
			n.setAttribute(a1, attribO);
		}
		
		
	}
	
}