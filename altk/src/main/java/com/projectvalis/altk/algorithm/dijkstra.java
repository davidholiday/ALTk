package com.projectvalis.altk.algorithm;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import bsh.Interpreter;



public class dijkstra {
	
	/*
	 * uses dijkstra's to calculate shortest path
	 * renders shortest path in graph window
	 */
	public static void showShortestPath(Graph graph, Interpreter bsInterp, String fromNode, String toNode) {

		// Edge lengths are stored in an attribute called "length" //layout.weight//
		// The length of a path is the sum of the lengths of its edges
		// The algorithm will store its results in attribute called "result"
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, "result", "length");
		dijkstra.init(graph);
		dijkstra.setSource(graph.getNode(fromNode));
		dijkstra.compute();

		// Color all the nodes on the shortest path 
		for (Node node : dijkstra.getPathNodes(graph.getNode(toNode))) {
			node.addAttribute("ui.style", "fill-color: #04756F; stroke-color: #2E0927;");
		}
					
		// Color all the edges in the shortest path 
		for (Edge edge : dijkstra.getPathEdges(graph.getNode(toNode))) {
			edge.addAttribute("ui.style", "fill-color: #04756F;");
		}
				 
		// change shape of source and target nodes
		graph.getNode(fromNode).addAttribute("ui.style", "shape: box;");
		graph.getNode(toNode).addAttribute("ui.style", "shape: box;");
		
		// Print the shortest path  
		bsInterp.print(dijkstra.getPath(graph.getNode(toNode)).getNodePath() + "\n");
		bsInterp.print(dijkstra.getPathLength(graph.getNode(toNode))+ "\n");

	}
	
}