package com.projectvalis.altk.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.graphstream.algorithm.APSP;
import org.graphstream.algorithm.APSP.APSPInfo;
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
	private static double minEdgeWeightD;
	private static double aggEdgeWeightD;
	
	// key:= edge weight
	// bucket:= score
	private static Hashtable<Double, Double> edgeWeightHT = new Hashtable<Double, Double>();
	
	
	// populated once by populateTables() so TSP1() doesn't have to
	// for every single node. while these tables don't need to be kept 
	// (only the vertex confidence table matters for the algo), this data
	// is retained for testing and debugging purposes.
	//
	// *NOTE* the per-node edge confidence information is stored in a 
	// table that's attached to the node itself. 
	
	// index:= number of edges - nodes in path
	// Double:= score
	//private static ArrayList<Double> edgeNumScoreAL = new ArrayList<Double>();
	private static ArrayList<Double> nodeNumScoreAL = new ArrayList<Double>();

	public static void TSP1(Graph graph, Interpreter bsInterp) {

//		// built in algo that computes shortest path data for all nodes
//		// ostensibly better performance than running a dijkstra loop
//		//
//		// initialize and compute shortest path on all nodes 
//		APSP apsp = new APSP();
//		apsp.init(graph);
//		apsp.setDirected(false);
//		apsp.setWeightAttributeName("length");
//		apsp.compute();
//		
//		// populate graph lookup tables
//		populateTables(graph);
//		
//		// populate graph nodes with edge confidence data. 
//		for (Node node : graph.getEachNode()) {
//			
//			// setup node edge confidence score table
//			Hashtable <String, Double> edgeConfidenceHT = new Hashtable<String, Double>();
//			
//			// get APSP info for this node
//			APSPInfo apspInfo = node.getAttribute(APSPInfo.ATTRIBUTE_NAME);
//			
//			
//		}
//		
		
		
		
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
		
//		bsInterp.println("node count to score list:: ");
//		for (int i = 0; i < nodeNumScoreAL.size(); i ++) {
//			bsInterp.println(i + " " + nodeNumScoreAL.get(i));
//		}
		
		
		
		
		// compute shortest path data for all nodes in graph
		for (int i = 0; i < graph.getNodeCount(); i ++) {
			
			// compute dijkstra on the node
			dijkstra.setSource(graph.getNode(i));
			dijkstra.compute();
			
			// setup node edge confidence score table
			Hashtable <String, Double> edgeConfidenceHT = new Hashtable<String, Double>();
			
			// populate edge name set
			HashSet<String> edgeNameSet = new HashSet<String>();
			
			for (Edge edge : graph.getNode(i).getEachEdge()) {
				edgeNameSet.add(edge.getId());
			}

			// add confidence score data to each node
//bsInterp.println("confidence :: path :: pathweight");
			for (int k = 0; k < graph.getNodeCount(); k ++) {
				
				// get the shortest path object

				Path shortestPathP = dijkstra.getPath(graph.getNode(k));
				
				
				// check all shortest paths to ensure we select the one with
				// the highest number of path edges
				for (Path p : dijkstra.getAllPaths(graph.getNode(k))) {
//bsInterp.println(p.getNodePath() + " " + shortestPathP.getNodePath());
//bsInterp.println(p.getEdgeCount() + " " + shortestPathP.getEdgeCount());
					shortestPathP = 
						(shortestPathP.getEdgeCount() >= p.getEdgeCount()) ? 
								(shortestPathP) : (p);		
				}
				
				
				
				
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
				
				// calculate confidence score 
				double edgeConfidenceScoreD = calculateConfidenceScore(edgeCountI, pathWeightD);

//bsInterp.println(edgeConfidenceScoreD + " " + shortestPathP.getNodePath() + " " + pathWeightD);
				// populate confidence table
				// remove edge from edgeList
				if (shortestPathP.getNodeCount() > 1) {
					//String edgeNameS = shortestPathP.peekEdge().getId();
					String edgeNameS = shortestPathP.getEdgePath().get(0).getId();
				
					// if the confidence table doesn't already have an entry 
					// for this edge, add it. 
					// else, check to see if the new score is higher than
					// what's in the table. if it is, add it to the table. 
					if (!edgeConfidenceHT.containsKey(edgeNameS)) {
						edgeConfidenceHT.put(edgeNameS, edgeConfidenceScoreD);			
					}
					else if (edgeConfidenceScoreD > edgeConfidenceHT.get(edgeNameS)) {
						edgeConfidenceHT.remove(edgeNameS);
						edgeConfidenceHT.put(edgeNameS, edgeConfidenceScoreD);
					}
									
					edgeNameSet.remove(shortestPathP.peekEdge().getId());		
				}
								
				
			}
			
			// add to the confidence table any edges that weren't a part of a 
			// shortest path			
			for (String edgeNameS : edgeNameSet) {
				Edge edge = graph.getEdge(edgeNameS);		
				double edgeConfidenceScoreD = 
						calculateConfidenceScore(1, edge.getAttribute("length"));	
				edgeConfidenceHT.put(edgeNameS, edgeConfidenceScoreD);
			}
			
			// add table to node object
			graph.getNode(i).setAttribute("edgeConfidenceTable", edgeConfidenceHT);
			
			
			bsInterp.println("\nedge confidence numbers for: " + graph.getNode(i).getId());
			for (String keyS : edgeConfidenceHT.keySet()) {
				bsInterp.println(keyS + " " + edgeConfidenceHT.get(keyS));
			}
					
			bsInterp.println("*  *  *  *  *\n");
		}
		
		
	}
	

	
	
	// populates the lookup tables necessary for the algo to work its magic
	private static void populateTables(Graph graph) {
		
		// populate node list
		int nodeCountI = graph.getNodeCount();
//System.out.println(nodeCountI + " " + graph);
		
		for (int i = 1; i <= nodeCountI; i ++) {
			double scoreI = (((double)i - 1.0) / ((double)nodeCountI - 1.0)) * 100;
//System.out.println(scoreI + " - " + i + " - " + nodeCountI);
			nodeNumScoreAL.add(scoreI);
		}
		
		
//		// populate edge list
//		int edgeCountI = graph.getEdgeCount();
//		
//		for (int i = 1; i <= edgeCountI; i++) {
//			double scoreI = ((i - 1) / (edgeCountI - 1)) * 100;
//			edgeNumScoreAL.add(scoreI);
//		}
		

		
		
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
	private static Double calculateConfidenceScore(int nodesInPath, Double pathWeight) {
		double nodeScoreD = nodeNumScoreAL.get(nodesInPath) * .5;
		double weightScoreD = edgeWeightHT.get(pathWeight) * .5;
		return nodeScoreD + weightScoreD;
	}
	
	
	// prints out the edge confidence data for each node
	public static void printConfidenceData(Graph graph, Interpreter bsInterp) {
		
		for (Node node : graph.getEachNode()) {
			Hashtable <String, Integer> confidenceTable = node.getAttribute("edgeConfidenceTable");
			
			bsInterp.println(node.getId());
			
			for (String keyS : confidenceTable.keySet()) {
				bsInterp.println(keyS + " " + confidenceTable.get(keyS));
			}
			
		}
		
		
	}
	
	
	
	

	
	
	
	
}