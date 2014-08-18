package com.projectvalis.altk.algorithm;

import java.util.ArrayList;
import java.util.Hashtable;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import bsh.EvalError;
import bsh.Interpreter;


public class holidayTSP1 {

	public static void computeDijkstra(Graph graph, Interpreter bsInterp) {

		// setup return arraylist
		ArrayList<String> returnAL = new ArrayList<String>();
		
		// Edge lengths are stored in an attribute called "length" //layout.weight//
		// The length of a path is the sum of the lengths of its edges
		// The algorithm will store its results in attribute called "result"
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, "result", "length");
		        
		// initialize dijkstra algo on graph
		dijkstra.init(graph);
						
		// compute shortest path data for all nodes in graph
		for (int i = 0; i < graph.getNodeCount(); i ++) {
			dijkstra.setSource(graph.getNode(i));
			dijkstra.compute();
			//bsInterp.print("\n");
			
			for (int k = 0; k < graph.getNodeCount(); k ++) {
				String outS = (dijkstra.getSource() + 
					" -> " + 
					graph.getNode(k) + 
					" : " + 
					dijkstra.getPathLength(graph.getNode(k)) + 
						"\n");
	
				returnAL.add(outS);
				//bsInterp.print(outS);
			}
						
		}
		
		
		// store result in shared var table
		try {
			((Hashtable) bsInterp.get("bsh.shared.varHT")).put("dkResult", dijkstra);
		} catch (EvalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		        
//		// Print the lengths of all the shortest paths
//		for (Node node : graph) {		 
//			dijkstra.setSource(node);
//			dijkstra.compute();
//			String outS = (dijkstra.getSource() + 
//					" -> " + 
//					node + 
//					" : " + 
//					dijkstra.getPathLength(node) + 
//					"/n");
//			//bsInterp.print(outS);
//			returnAL.add(outS);
//			//System.out.printf("%s->%s:%6.2f%n", dijkstra.getSource(), node, dijkstra.getPathLength(node)); 
//		}
		 

		
		 
//		 // Color in blue all the nodes on the shortest path form A to B
//		 for (Node node : dijkstra.getPathNodes(graph.getNode("B")))
//		     node.addAttribute("ui.style", "fill-color: blue;");
//		        
//		 // Color in red all the edges in the shortest path tree
//		 for (Edge edge : dijkstra.getTreeEdges())
//		     edge.addAttribute("ui.style", "fill-color: red;");
		 
//		 // Print the shortest path from A to B
//		 System.out.println(dijkstra.getPath(graph.getNode("B")));
//		 
//		 // Build a list containing the nodes in the shortest path from A to B
//		 // Note that nodes are added at the beginning of the list
//		 // because the iterator traverses them in reverse order, from B to A
//		 List <Node> list1 = new ArrayList<Node>();
//		 for (Node node : dijkstra.getPathNodes(graph.getNode("B")))
//		     list1.add(0, node);
//		 
//		 // A shorter but less efficient way to do the same thing
//		 List<Node> list2 = dijkstra.getPath(graph.getNode("B")).getNodePath();
		 
	}
	
	
	public static void showShortestPath(Graph graph, Interpreter bsInterp, String fromNode, String toNode) {

		// retrieve dijkstra object from shared hash table
		Hashtable varHT = null;
		try {
			varHT = (Hashtable)bsInterp.get("bsh.shared.varHT");
		} catch (EvalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Dijkstra dijkstra = (Dijkstra) varHT.get("dkResult");
		dijkstra.setSource(graph.getNode(fromNode));
		
		/*
		 * not really sure why i have to recompute - the data is already stored 
		 * in the graph nodes, no? is this method smart enough to check to see
		 * if it's necessary for it to compute the shortest path???
		 */
		dijkstra.compute();

		// Color in blue all the nodes on the shortest path 
		for (Node node : dijkstra.getPathNodes(graph.getNode(toNode))) {
			node.addAttribute("ui.style", "fill-color: #04756F; stroke-color: #2E0927;");
		}
					
		// Color in red all the edges in the shortest path 
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