package com.projectvalis.altk.algorithm;

import java.util.ArrayList;
import java.util.Hashtable;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;

import bsh.Interpreter;

/**
 * cellular automata algo that tackles TSP problems
 * @author funktapuss
 *
 */
public class holidayTSP1 {
	
	// variables populated by populateTables(), along with the keys for
	// edgeWeightHT. the buckets for edgeWeightHT are computed in the 
	// both by populateTables (for single edge weights) and TSP1 (for
	// path edge weights)
	private double minEdgeWeightD;
	private double aggEdgeWeightD;
	
	// key:= edge weight
	// bucket:= score
	private Hashtable<Double, Double> edgeWeightHT = new Hashtable<Double, Double>();
	
	
	// populated once by populateTables() so TSP1() doesn't have to
	// for every single node. while these tables don't need to be kept 
	// (only the vertex confidence table matters for the algo), this data
	// is retained for testing and debugging purposes.
	//
	// *NOTE* the per-node edge confidence information is stored in a 
	// table that's attached to the node itself. 
	
	// index:= number of nodes in path
	// Double:= score
	private ArrayList<Double> nodeScoreAL = new ArrayList<Double>();
	

	public void TSP1(Graph graph, Interpreter bsInterp) {

		// setup return arraylist
		//ArrayList<String> returnAL = new ArrayList<String>();
		
		// Edge lengths are stored in an attribute called "length" //layout.weight//
		// The length of a path is the sum of the lengths of its edges
		// The algorithm will store its results in attribute called "result"
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, "result", "length");
		        
		// initialize dijkstra algo on graph
		dijkstra.init(graph);
					
		// populate graph lookup tables
		populateTables(graph);
		
		// compute shortest path data for all nodes in graph
		for (int i = 0; i < graph.getNodeCount(); i ++) {
			
			// compute dijkstra on the node
			dijkstra.setSource(graph.getNode(i));
			dijkstra.compute();
			
			// setup node edge confidence score table
			Hashtable <String, Double> edgeConfidenceHT = new Hashtable<String, Double>();
		
			// perform 
			for (int k = 0; k < graph.getNodeCount(); k ++) {
				
				// get the shortest path object
				Path shortestPathP = dijkstra.getPath(graph.getNode(k));
				
				// grab edge count and weight
				int edgeCountI = shortestPathP.getEdgeCount();
				double pathWeightD = shortestPathP.getPathWeight("length");
				
				// if this weight hasn't been computed, add it to the
				// weight table
				if (!edgeWeightHT.containsKey(pathWeightD)) { 
					double weightScoreD = 
						(Math.abs(pathWeightD-aggEdgeWeightD) / 
								Math.abs(minEdgeWeightD-aggEdgeWeightD)) * 100;
					
					edgeWeightHT.put(pathWeightD, weightScoreD);
				}
				
				// calculate confidence score and populate table
				double edgeConfidenceScoreD = calculateConfidenceScore(edgeCountI, pathWeightD);
				String edgeNameS = shortestPathP.peekEdge().getId();
				edgeConfidenceHT.put(edgeNameS, edgeConfidenceScoreD);
			}
			
			// add table to node object
			graph.getNode(i).setAttribute("edgeConfidenceTable", edgeConfidenceHT);
						
		}
		 
	}
	
	
	// populates the lookup tables necessary for the algo to work its magic
	private void populateTables(Graph graph) {
		
		// populate node list
		int nodeCountI = graph.getNodeCount() - 1;
		
		for (int i = 1; i <= nodeCountI; i ++) {
			double scoreI = ((i - 1) / (nodeCountI - 1)) * 100;
			nodeScoreAL.add(scoreI);
		}
		
		
		// populate edge weight table
		minEdgeWeightD = 999999999999999.0;
		aggEdgeWeightD = 0;
		
		// figure out what the minimum and aggregate edge weights are
		// populate keys in edgeWeightHT
		for (Edge edge : graph.getEachEdge()) {			
			double edgeWeightD = (double)edge.getAttribute("length");
			minEdgeWeightD = (edgeWeightD < minEdgeWeightD) ? (edgeWeightD) : (minEdgeWeightD);
			aggEdgeWeightD += edgeWeightD;
			
			// put key into table along with a dummy value
			if (!edgeWeightHT.containsKey(edgeWeightHT)) {
				edgeWeightHT.put(edgeWeightD, 0.0);
			}
			
		}
		
		// now that we know min/max weight values, go through table keyset
		// and compute weight scores
		for (Double key : edgeWeightHT.keySet()) {
			double weightScoreD = 
					(Math.abs(key-aggEdgeWeightD) / 
							Math.abs(minEdgeWeightD-aggEdgeWeightD)) * 100;
			edgeWeightHT.put(key, weightScoreD);
		}
	
		
	}
	
	
	
	// calculates the confidence score based on the data in the lookup tables
	private Double calculateConfidenceScore(int nodesInPath, Double pathWeight) {
		double nodeScoreD = nodeScoreAL.get(nodesInPath) * .5;
		double weightScoreD = edgeWeightHT.get(pathWeight) * .5;
		return nodeScoreD + weightScoreD;
	}
	
	
	// prints out the edge confidence data for each node
	public void printConfidenceData(Graph graph, Interpreter bsInterp) {
		
		for (Node node : graph.getEachNode()) {
			Hashtable <String, Integer> confidenceTable = node.getAttribute("edgeConfidenceTable");
			
			bsInterp.println(node.getId());
			
			for (String keyS : confidenceTable.keySet()) {
				bsInterp.println(keyS + " " + confidenceTable.get(keyS));
			}
			
		}
		
		
	}
	
	
	
	
	/*
	 * this is dijkstra's shortest path algo -- perhaps this should be moved elsewhere
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