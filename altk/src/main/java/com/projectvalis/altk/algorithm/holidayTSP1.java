package com.projectvalis.altk.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;


public class holidayTSP1 {
	
	/*
	 * copied from graphstream api: 
	 * http://graphstream-project.org/api/gs-algo/
	 */
	public static void computeDijkstra(Graph graph) {

		 // Edge lengths are stored in an attribute called "length" //weight//
		 // The length of a path is the sum of the lengths of its edges
		 // The algorithm will store its results in attribute called "result"
		 Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, "result", "layout.weight");
		        
		 // Compute the shortest paths in g from A to all nodes
		 dijkstra.init(graph);
		 dijkstra.setSource(graph.getNode("A"));
		 dijkstra.compute();
		        
		 // Print the lengths of all the shortest paths
		 for (Node node : graph)
		     System.out.printf("%s->%s:%6.2f%n", dijkstra.getSource(), node, dijkstra.getPathLength(node));
		        
		 // Color in blue all the nodes on the shortest path form A to B
		 for (Node node : dijkstra.getPathNodes(graph.getNode("B")))
		     node.addAttribute("ui.style", "fill-color: blue;");
		        
		 // Color in red all the edges in the shortest path tree
		 for (Edge edge : dijkstra.getTreeEdges())
		     edge.addAttribute("ui.style", "fill-color: red;");
		 
		 // Print the shortest path from A to B
		 System.out.println(dijkstra.getPath(graph.getNode("B")));
		 
		 // Build a list containing the nodes in the shortest path from A to B
		 // Note that nodes are added at the beginning of the list
		 // because the iterator traverses them in reverse order, from B to A
		 List <Node> list1 = new ArrayList<Node>();
		 for (Node node : dijkstra.getPathNodes(graph.getNode("B")))
		     list1.add(0, node);
		 
		 // A shorter but less efficient way to do the same thing
		 List<Node> list2 = dijkstra.getPath(graph.getNode("B")).getNodePath();
		 
	}
	
}