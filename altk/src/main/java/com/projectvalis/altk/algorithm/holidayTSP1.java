package com.projectvalis.altk.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.DynamicAlgorithm;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;

import static org.graphstream.algorithm.Toolkit.*;

import com.projectvalis.altk.init.bootstrap;
import com.projectvalis.altk.util.mapSortUtils;

import bsh.Interpreter;

/**
 * cellular automata algo that tackles TSP problems
 * @author funktapuss
 *
 */
public class holidayTSP1 implements DynamicAlgorithm{
	
	// setup logger 
	private static final Logger LOGGER = Logger.getLogger(bootstrap.class.getName());
	
	// reference to the graph
	private static Graph graphRef;
	
	// flag to ensure termination happens
	private boolean terminateB = false;
		
	// for element attribute keys
	public static final String STATE = "state";
	public static final String UI_CLASS = "ui.class";
	
	// for node attribute keys
	public static final String CHOICE_NUM = "choiceNum";
	public static final String CHOICE_ARR = "choiceARR";
	public static final String NEIGHBOR_STATE_LIST = "neighborStateList";
	public static final String NEIGHBOR_STATE_F_LIST = "neighborStateFList";
	public static final String NEIGHBOR_STATE_I_LIST = "neighborStateIList";
	
	
	// for node state values
	public static final String STATE_I = "I";
	public static final String STATE_F = "F";
	public static final String STATE_V = "V";
	public static final String STATE_N = "N";
	
	// for edge state values
	public static final String STATE_A = "A";
	public static final String STATE_D = "D";
	
	// table to keep track of what nodes have been fully or partially 
	// processed in a cycle by virtue of the state of another node. usually,
	// this means an adjacent node is in state [F]
	private HashSet<String> processedLookupTableHS = new HashSet<String>();
	
		
	// reason variables for algorithm termination
	// used by terminate() to report back to operator	
	public static final String SUCCESS = 
					"System in stable state - no errors detected.";
	public static final String TOO_MANY_ADJACENT_FROZEN_NODES = 
					"Node has too many adjacent nodes in state [F].";
	public static final String TOO_MANY_CHOICES_MADE = 
					"Node has made more than two choices!";
	
	private String reasonForTerminationS = SUCCESS;
	
	
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

	public void init(Graph graph) {

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
	
		graphRef = graph;
		
		// Edge lengths are stored in an attribute called "length" //layout.weight//
		// The length of a path is the sum of the lengths of its edges
		// The algorithm will store its results in attribute called "result"
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, "result", "length");
		        
		// initialize dijkstra algo on graph
		dijkstra.init(graphRef);
					
		// populate graph lookup tables
		populateTables();
			
		
		// compute shortest path data for all nodes in graph
		for (int i = 0; i < graphRef.getNodeCount(); i ++) {
			
			// set node state to I (initial)
			graphRef.getNode(i).addAttribute(STATE, STATE_I);
			
			// compute dijkstra on the node
			dijkstra.setSource(graphRef.getNode(i));
			dijkstra.compute();
			
			// setup node edge confidence score table
			LinkedHashMap <String, Double> edgeConfidenceLHM = new LinkedHashMap<String, Double>();
 
			// populate edge name set
			HashSet<String> edgeNameSet = new HashSet<String>();
			
			for (Edge edge : graphRef.getNode(i).getEachEdge()) {
				edgeNameSet.add(edge.getId());
			}

			// add confidence score data to each node
			LOGGER.finer("confidence :: path :: pathweight");
			
			for (int k = 0; k < graphRef.getNodeCount(); k ++) {
				
				// get the shortest path object

				Path shortestPathP = dijkstra.getPath(graphRef.getNode(k));
				
				
				// check all shortest paths to ensure we select the one with
				// the highest number of path edges
				for (Path p : dijkstra.getAllPaths(graphRef.getNode(k))) {
					LOGGER.finer(p.getNodePath() + " " + shortestPathP.getNodePath());
					LOGGER.finer(p.getEdgeCount() + " " + shortestPathP.getEdgeCount());
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
				LOGGER.finer(edgeConfidenceScoreD + " " + shortestPathP.getNodePath() + " " + pathWeightD);
				
				// populate confidence table
				// remove edge from edgeList
				if (shortestPathP.getNodeCount() > 1) {
					//String edgeNameS = shortestPathP.peekEdge().getId();
					String edgeNameS = shortestPathP.getEdgePath().get(0).getId();
				
					// if the confidence table doesn't already have an entry 
					// for this edge, add it. 
					// else, check to see if the new score is higher than
					// what's in the table. if it is, add it to the table. 
					if (!edgeConfidenceLHM.containsKey(edgeNameS)) {
						edgeConfidenceLHM.put(edgeNameS, edgeConfidenceScoreD);			
					}
					else if (edgeConfidenceScoreD > edgeConfidenceLHM.get(edgeNameS)) {
						edgeConfidenceLHM.remove(edgeNameS);
						edgeConfidenceLHM.put(edgeNameS, edgeConfidenceScoreD);
					}
									
					edgeNameSet.remove(shortestPathP.peekEdge().getId());		
				}
								
				
			}
			
			// add to the confidence table any edges that weren't a part of a 
			// shortest path			
			for (String edgeNameS : edgeNameSet) {
				Edge edge = graphRef.getEdge(edgeNameS);		
				double edgeConfidenceScoreD = 
						calculateConfidenceScore(1, edge.getAttribute("length"));	
				edgeConfidenceLHM.put(edgeNameS, edgeConfidenceScoreD);
			}
			
			
			// sort table by value in descending order
			edgeConfidenceLHM = 
					(LinkedHashMap<String, Double>) mapSortUtils.
						sortByValuesDescending(edgeConfidenceLHM);
			
				
			// add table to node object
			graphRef.getNode(i).setAttribute("edgeConfidenceTable", edgeConfidenceLHM);
					
			LOGGER.finer("\nedge confidence numbers for: " + graphRef.getNode(i).getId());
			
			for (String keyS : edgeConfidenceLHM.keySet()) {
				LOGGER.finer(keyS + " " + edgeConfidenceLHM.get(keyS));
			}
					
			LOGGER.finer("*  *  *  *  *\n");
		}
		
		
	}
	

	
	// adjudicates the actions of the vertices 
	// updates the graph view
	// checks to see if the graph is in a stable state
	// if not, iterate. if so, terminate. 
	@SuppressWarnings("unchecked")
	@Override 
	public void compute() {
		
		while(!terminateB) {
			
			// reset processed table
			processedLookupTableHS.clear();
			
			// update step counter
			/*
			 * 
			 * 
			 * 
			 */
		
			// loop through node list and adjudicate orders
			for (Node node : graphRef.getNodeSet()) {
				
				// figure out the state of this node
				String nodeStateS = node.getAttribute(STATE);
				
				// figure out whether or not this node has already been partially
				// or fully processed. 
				// if it hasn't, reset choice variables
				if (!processedLookupTableHS.contains(node.getId())) {
					resetNodeChoiceVars(node);		
				}
					
		
				// figure out the state of this node's neighbors
				populateNodeNeighborTables(node);
	
							
				/*
				 * check to see if this node is in a frozen state
				 * if it is, set state and iterate
				 */
				if (node.getDegree() == 2) {
					setNodeState(node, STATE_F);
					continue;
				}
					
	
				/*
				 * check to see if any of this node's neighbors are in 
				 * state [F].
				 * if found, it forces this node to vote for the adjoining edge 
				 * (thus activating it). 
				 * if more than two edges are activated in this way, terminate.
				 */
//				node.addAttribute(CHOICE_NUM, (2 - adjacentStateF_AL.size()));
//				
//				if ((Integer)node.getAttribute(CHOICE_NUM) < 0) {
//					reasonForTerminationS = TOO_MANY_ADJACENT_FROZEN_NODES;
//					break;
//				}
				
				
				
				/*
				 * check to see if any of this node's neighbors are in state [I].
				 * if they are, pick the highest one and offer to connect.
				 * 
				 */
				
	
				
			}
			
			// kills the algo if we get here prior to the natural termination 
			// of the loop
			terminate();
		
		}
		
	}
	
	
	
	
	/*
	 * terminates the algorithm
	 * takes action based on reason code
	 */
	@Override
	public void terminate() {
		LOGGER.info(
				"reponse from algorith.compute() is: \n" + 
						reasonForTerminationS);
		terminateB = true;
	}
	
	
	
	
	
	// populates the lookup tables necessary for the algo to work its magic
	private static void populateTables() {
		
		// populate node list
		int nodeCountI = graphRef.getNodeCount();
		
		for (int i = 1; i <= nodeCountI; i ++) {
			double scoreI = 
					(((double)i - 1.0) / ((double)nodeCountI - 1.0)) * 100;
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
		for (Edge edge : graphRef.getEachEdge()) {		
			double edgeWeightD = (double)edge.getAttribute("length");
			minEdgeWeightD = 
					(edgeWeightD < minEdgeWeightD) ? 
							(edgeWeightD) : (minEdgeWeightD);
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
	private static Double calculateConfidenceScore(
			int nodesInPath, Double pathWeight) {
		double nodeScoreD = nodeNumScoreAL.get(nodesInPath) * .5;
		double weightScoreD = edgeWeightHT.get(pathWeight) * .5;
		return nodeScoreD + weightScoreD;
	}
	
	
	// prints out the edge confidence data for each node
	public static void printConfidenceData(Graph graph, Interpreter bsInterp) {
		
		for (Node node : graph.getEachNode()) {
			Hashtable <String, Integer> confidenceTable = 
					node.getAttribute("edgeConfidenceTable");
			
			bsInterp.println(node.getId());
			
			for (String keyS : confidenceTable.keySet()) {
				bsInterp.println(keyS + " " + confidenceTable.get(keyS));
			}
			
		}
		
		
	}



	// utility method to handle node choice action
	private void makeNodeChoice(Node node, Edge edgeChoice) {
		
		// add edge to adjacent node's list of choices
		ArrayList<String> adjacentNodeChoiceARR = 
				node.getAttribute(CHOICE_ARR);
		adjacentNodeChoiceARR.add(edgeChoice.getId());
	
		// update choice counter
		int choiceNum = node.getAttribute(CHOICE_NUM);
		node.setAttribute(CHOICE_NUM, choiceNum--); 
		
		// update lookup table
		processedLookupTableHS.add(node.getId());
		
		// check for case where node makes too many choices
		if (choiceNum < 0) {
			reasonForTerminationS = TOO_MANY_CHOICES_MADE;
			terminate();
		}
		
	}

	
	
	// utility method to handle node states
	@SuppressWarnings("unchecked")
	private void setNodeState(
			Node node, String state) {
		
		// create local references to node neighbor tables
		// setup lookup table and lists for the states adjacent nodes.
		LinkedHashMap<String, String> adjacentNodeStateLHM = node.getAttribute(NEIGHBOR_STATE_LIST);
		ArrayList<Node> adjacentStateI_AL = node.getAttribute(NEIGHBOR_STATE_I_LIST);
		ArrayList<Node> adjacentStateF_AL = node.getAttribute(NEIGHBOR_STATE_F_LIST);
		
		switch(state) {
			
			case STATE_F:
				
				// set state [F] for this node
				node.addAttribute(STATE, STATE_F);
				node.addAttribute(UI_CLASS, STATE_F);
				node.addAttribute(CHOICE_NUM, 0);
				
				
				/*
				 * activate edges between frozen node and neighbors.
				 * update graph CSS
				 * update adjacent node's choice variables
				 * 
				 */
				for (String adjacentNodeS : adjacentNodeStateLHM.keySet()) {
					
					// get graph elements
					Node adjacentNodeN = graphRef.getNode(adjacentNodeS);
					Edge adjacentEdgeE = node.getEdgeToward(adjacentNodeS);
					
					// figure out whether or not this node has already been partially
					// or fully processed. 
					// if it hasn't, reset choice variables
					if (!processedLookupTableHS.contains(node.getId())) {
						resetNodeChoiceVars(adjacentNodeN);		
					}
					
					// update edge presentation
					adjacentEdgeE.addAttribute(UI_CLASS, STATE_A);
					
					// direct neighbor node to also select this edge
					makeNodeChoice(adjacentNodeN, adjacentEdgeE);	
					
					// check to see if this has caused the adjacent node to
					// change state
					if ((Integer)adjacentNodeN.getAttribute(CHOICE_NUM) == 0) {
						
						// populate node neighbor tables
						populateNodeNeighborTables(adjacentNodeN);
						
						String choiceOneS = ((ArrayList<String>)
								adjacentNodeN.getAttribute(CHOICE_ARR)).get(0);
						
						String choiceTwoS = ((ArrayList<String>)
								adjacentNodeN.getAttribute(CHOICE_ARR)).get(1);
						
						String choiceOneStateS = graphRef.getNode(choiceOneS).getAttribute(STATE);
						String choiceTwoStateS = graphRef.getNode(choiceTwoS).getAttribute(STATE);
						
						// if true, node is in frozen state. 
						if ((choiceOneStateS + choiceTwoStateS).contentEquals(STATE_F + STATE_F)) {
							setNodeState(adjacentNodeN, STATE_F);
						}
						// else, node is in valid state 
						else {
							setNodeState(adjacentNodeN, STATE_V);
						}
									
					}
					
				}
			
			break;
				
		}
		
	}




	// for populating a node's tables with info about the state of its 
	// neighbors
	private void populateNodeNeighborTables(Node node) {
		
		// setup lookup table and lists for the states adjacent nodes.
		LinkedHashMap<String, String> adjacentNodeStateLHM = new LinkedHashMap<String, String>();
		ArrayList<Node> adjacentStateI_AL = new ArrayList<Node>();
		ArrayList<Node> adjacentStateF_AL = new ArrayList<Node>();
		
		// figure out the state of this node's neighbors
		for (Edge adjacentEdge : node.getEdgeSet()) {
			Node adjacentNode = adjacentEdge.getOpposite(node);
			adjacentNodeStateLHM.put(adjacentNode.getId(), adjacentNode.getAttribute(STATE));
			
			// put nodes in state [I] and [F] in their own tables for easy 
			// access
			if (adjacentNode.getAttribute(STATE) == STATE_I) {
				adjacentStateI_AL.add(adjacentNode);
			}
			else if (adjacentNode.getAttribute(STATE) == STATE_F) {
				adjacentStateF_AL.add(adjacentNode);
			}
			
		}
		
		node.addAttribute(NEIGHBOR_STATE_LIST, adjacentNodeStateLHM);
		node.addAttribute(NEIGHBOR_STATE_I_LIST, adjacentStateI_AL);
		node.addAttribute(NEIGHBOR_STATE_F_LIST, adjacentStateF_AL);
		
		
	}


	// resets the node choice variables
	private void resetNodeChoiceVars(Node node) {
		node.addAttribute(CHOICE_NUM, 2);
		node.addAttribute(CHOICE_ARR, new ArrayList<String>());
	}
	

	

	
}