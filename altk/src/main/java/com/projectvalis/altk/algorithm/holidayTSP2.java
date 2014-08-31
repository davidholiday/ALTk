package com.projectvalis.altk.algorithm;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.logging.Logger;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.DynamicAlgorithm;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.SinkAdapter;

import static org.graphstream.algorithm.Toolkit.*;

import com.projectvalis.altk.init.bootstrap;
import com.projectvalis.altk.util.mapSortUtils;

import bsh.Interpreter;

/**
 * cellular automata algo that tackles TSP problems
 * @author funktapuss
 *
 */
public class holidayTSP2 extends SinkAdapter implements DynamicAlgorithm{
	
	// setup logger 
	private static final Logger LOGGER = Logger.getLogger(holidayTSP1.class.getSimpleName());
	
	// reference to the graph
	private static Graph graphRef;
	
	// flag to ensure termination happens
	private boolean terminateRequestB = false;
	
	// flag to determine whether or not a change has occured in the current 
	// cycle
	private boolean noChangeFlagB = true;
	
	// for running dijkstra against the graph
	Dijkstra dijkstra;
	
	// for keeping track of what edges are activated
	private static HashSet<String> activeEdgesHS;
	
	// for keeping track of time steps
	private Integer stepCounterI = 0;
	public static final String STEP_COUNTER = "step";
		
	// for element attribute keys
	public static final String STATE = "state";
	public static final String UI_CLASS = "ui.class";
	
	// for node attribute keys
	public static final String CHOICE_NUM = "choiceNum";
	public static final String CHOICE_ARR = "choiceARR";
	public static final String SUCCESS_NUM = "successNum";
	public static final String NEIGHBOR_STATE_LIST = "neighborStateList";
	public static final String NEIGHBOR_STATE_F_LIST = "neighborStateFList";
	public static final String NEIGHBOR_STATE_I_LIST = "neighborStateIList";
	public static final String NEIGHBOR_STATE_I_EDGE_TABLE = "neighborStateIEdgeTable";
	public static final String OFFERS_TABLE = "offersTable";
	public static final String EDGE_CONFIDENCE_TABLE = "edgeConfidenceTable";
	public static final String ACTIVE_ADJACENT_EDGE_LIST = "activeAdjacentEdgeList";
	public static final String EDGE_DISCONNECT_CANDIDATE_LIST = "edgeDisconnectCandidateList";
	
	
	// for node state values
	public static final String STATE_I = "I";
	public static final String STATE_F = "F";
	public static final String STATE_V = "V";
	public static final String STATE_N = "N";
	
	// for referencing the edge frozen flag 
	public static final String FROZEN_F = "frozenFlag";
	
	// for edge state values
	public static final String STATE_A = "A";
	public static final String STATE_D = "D";
	
	// table to keep track of what nodes have been fully or partially 
	// processed in a cycle by virtue of the state of another node. usually,
	// this means an adjacent node is in state [F]
	private HashSet<String> processedLookupTableHS = new HashSet<String>();
	
	// keeps track of nodes who are haven't made their two connections 
	// in a given cycle
	//private HashSet<String> invalidNodeTableHS = new HashSet<String>();	
		
	// reason variables for algorithm termination
	// used by terminate() to report back to operator	
	public static final String SUCCESS = 
					"System in stable state - no errors detected.";
	public static final String TOO_MANY_ADJACENT_FROZEN_NODES = 
					"Node has too many adjacent nodes in state [F].";
	public static final String TOO_MANY_CHOICES_MADE = 
					"Node has made more than two choices!";
	public static final String TOO_MANY_ACTIVE_EDGES = 
			"Node has more than two active edges!";
	
	
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
		
		// store reference to graph object
		graphRef = graph;
		
		// set step counter
		graphRef.addAttribute(STEP_COUNTER, 0);
		
		// make it so the this algo acts as a recipient of events and will 
		// compute accordingly (?)
		graphRef.addSink(this);
		
		// Edge lengths are stored in an attribute called "length" //layout.weight//
		// The length of a path is the sum of the lengths of its edges
		// The algorithm will store its results in attribute called "result"
		dijkstra = new Dijkstra(Dijkstra.Element.EDGE, "result", "length");
		        
		// initialize dijkstra algo on graph
		dijkstra.init(graphRef);
					
		// populate graph lookup tables
		populateTables();
			
		
		// compute shortest path data for all nodes in graph
		for (int i = 0; i < graphRef.getNodeCount(); i ++) {
			
			// set node state to N (new)
			setNodeState(graphRef.getNode(i), STATE_N);
			
			// compute dijkstra on the node
			dijkstra.setSource(graphRef.getNode(i));
			dijkstra.compute();
			
			// setup node edge confidence score table
			LinkedHashMap <String, Double> edgeConfidenceLHM = new LinkedHashMap<String, Double>();
 
			// populate edge name set.
			// populate edge frozen attribute
			HashSet<String> edgeNameSet = new HashSet<String>();
			
			for (Edge edge : graphRef.getNode(i).getEachEdge()) {
				edgeNameSet.add(edge.getId());
				edge.addAttribute(holidayTSP2.FROZEN_F, false);
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
			graphRef.getNode(i).setAttribute(EDGE_CONFIDENCE_TABLE, edgeConfidenceLHM);
					
			LOGGER.finer("\nedge confidence numbers for: " + graphRef.getNode(i).getId());
			
			for (String keyS : edgeConfidenceLHM.keySet()) {
				LOGGER.finer(keyS + " " + edgeConfidenceLHM.get(keyS));
			}
					
			LOGGER.finer("*  *  *  *  *\n");
		}
		
		
	}
	

	/*
	 * adjudicates the actions of the vertices 
	 * updates the graph view
	 * checks to see if the graph is in a stable state
	 * if not, iterate. if so, terminate. 
	 * 
	 * *NOTE* the cycle algorithm is purposefully written to compute node
	 * states for every node, every cycle. this way, if a need should arise 
	 * too change the graph while this algorithm is running, the algo can 
	 * handle it accordingly. 
	 */
	@SuppressWarnings("unchecked")
	public void cycle() {
	
		// reset cycle tracking tables 
		//processedLookupTableHS.clear();
		//invalidNodeTableHS.clear();
		
		// reset change flag
		noChangeFlagB = true;

		// loop through node list and adjudicate orders
		for (Node node : graphRef.getNodeSet()) {
LOGGER.info("\n processing node: [" + node.getId() + "]\n");
//LOGGER.info("potentialInvalidTable is: " + invalidNodeTableHS);
			// check to ensure this node doesn't have more than two
			// active edges
			int numActiveEdgesI = ((ArrayList<String>)node.
					getAttribute(ACTIVE_ADJACENT_EDGE_LIST)).size();
LOGGER.info("number of active edges for this node is: " + numActiveEdgesI);		

			if (numActiveEdgesI > 2) {
				requestTermination(TOO_MANY_ACTIVE_EDGES + " for node: " + node);
			}

			// figure out the state of this node
			String nodeStateS = node.getAttribute(STATE);
LOGGER.info("this node is in state: " + nodeStateS + "\n");			

				
			// if it has, and this node is in state [F], it has already 
			// been processed, so iterate
			if (nodeStateS.contentEquals(STATE_F)) {
				continue;
			}
			
			// reset edge disconnect candidate list
			resetEdgeDisconnectCandidateList(node);
		
			// figure out the state of this node's neighbors
			populateNodeNeighborTables(node);

			// reset choice variables
			resetNodeChoiceVars(node);	
			

			/*
			 * check to see if this node is in a frozen state
			 * if it is, set state and iterate
			 */
			if (node.getDegree() == 2) {
				setNodeState(node, STATE_F);
				continue;
			}

			
			// check to see if this node AND all its neighbors are in state
			// [I]. if they are then re-add them to this node's choice 
			// table and iterate. 
			ArrayList<String> adjacentI_AL = node.getAttribute(NEIGHBOR_STATE_I_LIST);
LOGGER.info("node state and adjacent state I list are: " + nodeStateS + " " + adjacentI_AL);
			if (nodeStateS.contentEquals(STATE_V) && adjacentI_AL.isEmpty()) {
				
				// get choice counter
				int choiceNumI = node.getAttribute(CHOICE_NUM);
					
				// get active edge list
				ArrayList<String> activeAdjacentEdgesAL = 
						node.getAttribute(ACTIVE_ADJACENT_EDGE_LIST);
				
				// get edge choice list
				ArrayList<String> edgeChoiceAL = node.getAttribute(CHOICE_ARR);

				for (int i = 0; i < choiceNumI; i ++) {		
					Edge edge = graphRef.getEdge(activeAdjacentEdgesAL.get(i));
					boolean frozenB = edge.getAttribute(FROZEN_F);

					// frozen edges aren't processed once they are set - 
					// so this checks that we don't mess with a frozen edge
					if (!frozenB) {
LOGGER.info("adding edge: " + edge.toString() + " to edgeChoiceAL");
						edgeChoiceAL.add(edge.getId());
						lowerAndCheckChoiceNum(node);
					}
					
				}
														
			}
			// make choices for this node
			else {
				makeNodeChoice(node);
			}
					
		}

		// set state on any nodes that haven't been fully processed yet. 
		adjudicate();

		// terminate if the graph hasn't changed this cycle
		LOGGER.info("\n\nend of cycle: " + stepCounterI + ". noChangeFlag is: " + noChangeFlagB);
		LOGGER.info("************************\n\n");
		if (noChangeFlagB) { requestTermination(SUCCESS); }
		
	}
	
	
	// for adjudicating the state of the nodes in the potentially invalid 
	// table
	//
	// *NOTE* you need to add the performance enhancement that allows 
	// nodes that are effectively processed before their turn in the loop
	// to be added to the preprocessed list and skipped!
	private void adjudicate(){
			
		// now that we've processed all of the edge choices
		// go through the node list and figure out what state everyone 
		// is in
		for (Node node : graphRef.getEachNode()) {
	
			// stage some variables
			ArrayList<String> edgeChoiceAL = 
					node.getAttribute(CHOICE_ARR);
			
			String nodeStateS = node.getAttribute(STATE);
			int choiceNumI = node.getAttribute(CHOICE_NUM);
			
			// is this node in state [F]?
			// if so, no need to process so
			// continue (frozen nodes and
			// edges are activated once then
			// left alone). 
			if (nodeStateS.contentEquals(STATE_F)) {
				continue;
			}
			
						
			// go through node choices and adjudicate
			// nodes in state [I] with one active edge 
			// should not fall into this loop. moreover, here, 
			// they should only be making one choice.
			for (int i = 0; i < edgeChoiceAL.size(); i ++) {
				Edge edgeChoice = graphRef.getEdge(edgeChoiceAL.get(i));
LOGGER.info("edge choice is: " + edgeChoice.getId());
				Node adjacentNode = edgeChoice.getOpposite(node);

				ArrayList<String> adjacentNodeChoiceAL = 
						adjacentNode.getAttribute(CHOICE_ARR);

				// activate edge if both nodes agreed to activate it
				if (adjacentNodeChoiceAL.contains(edgeChoice.getId())) {
					setEdgeState(edgeChoice.getId(), true);
				}					

			}
			
			
			// every node in state [I] should still need to consult its 
			// offer table
			if (nodeStateS.contentEquals(STATE_I)) {
				
				// get list of currently active edges
				ArrayList<String> activeEdgeAL = 
						node.getAttribute(ACTIVE_ADJACENT_EDGE_LIST);
				
				// get and sort the offers table in descending order by value
				LinkedHashMap<String, Double> offersTableLHM = 
						node.getAttribute(OFFERS_TABLE);
				
				offersTableLHM = (LinkedHashMap<String, Double>) 
						mapSortUtils.sortByValuesDescending(offersTableLHM);
	
LOGGER.info("offersTableLHM for node: [" + node.getId() + "] is: " + offersTableLHM);
				
				// figure out which of the members of the offers table
				// hasn't already been activated and activate it.
				if (!offersTableLHM.isEmpty()) {
					
					for (int i = 0; i < choiceNumI; i ++) {
						
						String edgeNameS = new String();
						
						for (String keyS : offersTableLHM.keySet()) {
							edgeNameS = keyS;
							
							if (!activeEdgeAL.contains(keyS)) {
								break;
							}
							
						}					
						
						// active the edge
						setEdgeState(edgeNameS, true);
						
					}
				
				}
				
				
			}
			
			
			// figure out results 
			ArrayList<String> activeEdgeAL = 
					node.getAttribute(ACTIVE_ADJACENT_EDGE_LIST);
			int edgeCountI = activeEdgeAL.size();
			
			
LOGGER.info("\nsetting state for node: " + node.getId());			
LOGGER.info("active edge count is: " + edgeCountI);
			// make sure we don't have too many active edges
			if (edgeCountI > 2) {requestTermination(TOO_MANY_ACTIVE_EDGES);}
			
			// check for state I
			if (edgeCountI < 2) {setNodeState(node, STATE_I);}
			
			// check for state V
			if (edgeCountI == 2) {setNodeState(node, STATE_V);}
			
		}
		
		
	}
	

	
	// called after init()
	// drives the algorithm.
	public void compute() {
		int maxStepsI = 5;
		
		// run algo until terminate is requested
		while ((stepCounterI < maxStepsI) && (!terminateRequestB)) {
			
			// iterate step counter
			stepCounterI ++;
			graphRef.setAttribute(STEP_COUNTER, stepCounterI);
			
			// cycle the system by one time step
			cycle();
			
			// pause before cycling again
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
		// end processing
		terminate();
		
	}
	
	
	// for receiving events from the graph and marking time (?)
//	public void stepBegins(String sourceId, long timeId, double step) {
//		
//	}
	
	
	
	/*
	 * processes termination requests 
	 * sets termination flag and reason variable
	 * 
	 * *NOTE* caller needs to employ a 'continue' statement after making
	 * this call if she wants the termination to happen immediately (as 
	 * opposed to waiting until the algo is finished processing this node).
	 */
	public void requestTermination(String reason) {
		terminateRequestB = true;
		reasonForTerminationS = reason;
	}
	
	/*
	 * terminates the algorithm
	 * takes action based on reason code
	 */
	@Override
	public void terminate() {
		
		// stage a reference to the cycle path
		//Path cyclePath = buildCyclePath();
				
		LOGGER.info(
				"reponse from algorith.compute() is: \n\n" + 
						reasonForTerminationS + "\n" +
						"number of cycles was:= " + stepCounterI + "\n" +
						buildReportString());
	}
	
	
	// for metric reporting after algorithm termination
	private String buildReportString() {
		
		// report string we'll be returning to caller
		String reportS;
		
		// flag indicating whether or not we have a complete cycle 
		boolean completeCycleB = false;
		
		// counter indicating how many nodes have been traversed
		int nodeCountI = 0;
		
		// index variable for the next node in the list
		int nodeIndexI = 0;
		
		// aggregate edge weight
		Double aggregateEdgeWeightD = 0.0;
		
		// list of edges we've already traversed
		ArrayList<String> traversedEdgesAL = new ArrayList<String>();
				
		// flag to terminate path traversal
		//boolean stopB = false;
		
		// traverse path to see if we've got a cycle
		for (int i = 0; i < graphRef.getNodeCount(); i ++) {
			Node node = graphRef.getNode(nodeIndexI);
			ArrayList<String> activeAdjacentEdgeAL = 
					node.getAttribute(ACTIVE_ADJACENT_EDGE_LIST);
						
			if (activeAdjacentEdgeAL.size() < 2) {
				return "CAN'T BUILD REPORT - NODE IN STATE [I] DETECTED!";
			}
			
			String edgeOneS = activeAdjacentEdgeAL.get(0);
			String edgeTwoS = activeAdjacentEdgeAL.get(1);
			
			// figure out which edge is the one we didn't use to arrive at
			// this node
			if (!traversedEdgesAL.contains(edgeOneS)) {
				// figure out weight
				double weight = graphRef.getEdge(edgeOneS).getAttribute("length");
				aggregateEdgeWeightD += weight;
				
				// add edge to traversed edge list
				traversedEdgesAL.add(edgeOneS);
				
				// figure out what the next node is
				Node oppositeNode = graphRef.getEdge(edgeOneS).getOpposite(node);
				nodeIndexI = graphRef.getNode(oppositeNode.getId()).getIndex();
				
				// if we've reached the node we've started at, stop
				//if (nodeIndexI == 0) {
					//stopB = true;
				//	break;
				//}
				// else update node count and iterate
				//else {
				//	nodeCountI++;
				//}
							
			}
			else if (!traversedEdgesAL.contains(edgeTwoS)){
				// figure out weight
				double weight = graphRef.getEdge(edgeTwoS).getAttribute("length");
				aggregateEdgeWeightD += weight;
				
				// add edge to traversed edge list
				traversedEdgesAL.add(edgeTwoS);
				
				// figure out what the next node is
				Node oppositeNode = graphRef.getEdge(edgeTwoS).getOpposite(node);
				nodeIndexI = graphRef.getNode(oppositeNode.getId()).getIndex();
				
				// if we've reached the node we've started at, stop
				//if (nodeIndexI == 0) {
				//	//stopB = true;
				//	break;
				//}
				// else update node count and iterate
				//else {
				//	nodeCountI++;
				//}
				
			}
			
			nodeCountI++;
			
			// if we've reached the node we've started at, stop
			if (nodeIndexI == 0) {
				// figure out if we've made a complete cycle
				completeCycleB = (nodeCountI == graphRef.getNodeCount()) ? (true) : (false);
				break;
			}
			
			
			
		}
		
		reportS = new String("complete circuit flag:= " + completeCycleB +
						 "\npath weight:= " + aggregateEdgeWeightD + 
						 "\nnumber of activated edges:= " + 
						 	traversedEdgesAL.size() + " / " + 
						 	graphRef.getEdgeCount());
		
		return reportS;
	}
	
	
//	// for metric reporting after algorithm termination
//	private Path buildCyclePath() {
//		Graph graphWithOnlyCycle = new SingleGraph("cycle graph");
//		Path cyclePath = null;
//		
//		// add copies of each node to the new graph
//		for (Node node : graphRef.getNodeSet()) {
//			graphWithOnlyCycle.addNode(node.getId());
//		}
//		
//		// add copies of the members of the active edges set
//		for (String edgeS : activeEdgesHS) {
//			Edge edge = graphRef.getEdge(edgeS);
//			String node0 = edge.getNode0().getId();
//			String node1 = edge.getNode1().getId();
//			edge.setAttribute("length", edge.getAttribute("length"));
//			graphWithOnlyCycle.addEdge(edge.getId(), node0, node1);
//		}
//		
//		// use dijkstra to get path data
//		Node sourceNode = graphWithOnlyCycle.getNode(0);
//		Node targetNode = sourceNode.getEdge(0).getOpposite(sourceNode);
//		
//		dijkstra.init(graphWithOnlyCycle);
//		dijkstra.setSource(sourceNode);
//		dijkstra.compute();
//System.out.println(sourceNode + " " + targetNode);
//System.out.println(graphWithOnlyCycle.getEdgeCount() + " " + graphWithOnlyCycle.getNodeCount());	
//		
//		cyclePath = dijkstra.getPath(targetNode);
//
//		// there should only be two
//
////	for (Path path : dijkstra.getAllPaths(targetNode)) {
////System.out.println(path.getEdgeCount() + " " + sourceNode + " " + targetNode);
////			if (path.getEdgeCount() > 1) {
////				cyclePath = path;
////			}
////			
////		}
//		
//		return cyclePath;
//	}
	
	
	
	// populates the lookup tables necessary for the algo to work its magic
	private static void populateTables() {
		
		// initialize active edge list
		activeEdgesHS = new HashSet<String>();
		
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
			LinkedHashMap <String, Double> confidenceTable = 
					node.getAttribute(EDGE_CONFIDENCE_TABLE);
			
			bsInterp.println(node.getId());
			
			for (String keyS : confidenceTable.keySet()) {
				bsInterp.println(keyS + " " + confidenceTable.get(keyS));
			}
			
		}
		
		
	}

	
	// utility method to handle node decision computation
	private void makeNodeChoice(Node node) {
		
		// create local references to node attributes
		LinkedHashMap<String, String> adjacentNodeStateLHM = 
				node.getAttribute(NEIGHBOR_STATE_LIST);
		
		ArrayList<Node> adjacentStateI_AL = 
				node.getAttribute(NEIGHBOR_STATE_I_LIST);
		
		ArrayList<String> activeAdjacentEdgeAL = 
				node.getAttribute(ACTIVE_ADJACENT_EDGE_LIST);
		
		ArrayList<Node> adjacentStateF_AL = 
				node.getAttribute(NEIGHBOR_STATE_F_LIST);
		
		LinkedHashMap <String, Double> confidenceTable = 
				node.getAttribute(EDGE_CONFIDENCE_TABLE);
		
		LinkedHashMap <String, Double> neighborStateIEdgeTable = 
				node.getAttribute(NEIGHBOR_STATE_I_EDGE_TABLE);
		
		ArrayList<String> edgeChoiceAL = 
				node.getAttribute(CHOICE_ARR);	
		
		// figure out the state of this node
		String nodeStateS = node.getAttribute(STATE);
		
		
		// build disconnect candidate list
		// figure out if either of the two currently active edges are connected
		// to a node in state [I] or [F] and set flag accordingly. we're doing 
		// this because disconnecting from a node in either of these states is
		// not permitted. 
		ArrayList<String> disconnectCandidatesAL = 
				node.getAttribute(EDGE_DISCONNECT_CANDIDATE_LIST);
LOGGER.info("disconnectedCandidatesAL should be empty here!: " + disconnectCandidatesAL);	
LOGGER.info("activeAdjacentEdgeAL is: " + activeAdjacentEdgeAL);
LOGGER.info("adjacentStateF_AL is: " + adjacentStateF_AL);
LOGGER.info("adjacentStateI_AL is: " + adjacentStateI_AL);
		for (int i = 0; i < activeAdjacentEdgeAL.size(); i ++) {
			Node connectedNode = 
					graphRef.getEdge(
							activeAdjacentEdgeAL.get(i)).getOpposite(node);

			if (!adjacentStateF_AL.contains(connectedNode) && 
					!adjacentStateI_AL.contains(connectedNode)) {			
				
				disconnectCandidatesAL.add(activeAdjacentEdgeAL.get(i));
			}	
				
		}
			
		// if the disconnect list has more than one entry, figure out which
		// of the two this node has less confidence in and set flag. Ensure
		// list is in ascending order of confidence score		
		if (disconnectCandidatesAL.size() > 1) {
			String candidateEdgeOneS = new String (disconnectCandidatesAL.get(0));
			String candidateEdgeTwoS = new String (disconnectCandidatesAL.get(1));
				
			double candidateEdgeOneScoreD = confidenceTable.get(candidateEdgeOneS);
			double candidateEdgeTwoScoreD = confidenceTable.get(candidateEdgeTwoS);
				
			if (candidateEdgeOneScoreD > candidateEdgeTwoScoreD) {
				disconnectCandidatesAL.clear();
				disconnectCandidatesAL.add(candidateEdgeTwoS);
				disconnectCandidatesAL.add(candidateEdgeOneS);
			}
			
		}
		
LOGGER.info("disconnectedCandidatesAL is now: " + disconnectCandidatesAL);
		
		// figure out how many options for choosing this node has
		int choiceNumI = node.getAttribute(CHOICE_NUM);
LOGGER.info("choiceNum is: " + choiceNumI + "\n");		
LOGGER.info("neighbor state [I] edge table for node: " + node + " is: " + neighborStateIEdgeTable);				
		// are any of the adjacent nodes in state [I]?
		// if true, make an offer to the neighbor node in state [I] you
		// have the most confidence in. *NOTE* the adjacentStateI list
		// has been pre-sorted by populateTables() to be in descending order. 
		//
		// *NOTE* we also only allow completely unconnected nodes to make an 
		// offer to one another. this prevents a case where several state [I]
		// nodes remain that way because they are all trying only to connect 
		// to one another in a a circle (ie - A wants only to connect to B, who
		// in turn only wants to connect to C, who wants to connect to A). 
		// therefore, this checks to see that either 
		//
		//	1) this node has state [I] neighbors AND is not in state [i]
		//  -or-
		//	2) this node has no active edges AND is in state [I]
		//
		// before proceeding in using one of its choices.
		if ( (!adjacentStateI_AL.isEmpty() && !nodeStateS.contentEquals(STATE_I)) 
				|| ( activeAdjacentEdgeAL.size()== 0 
					&& nodeStateS.contentEquals(STATE_I)) ) {
			
			// of the set of edges leading to this node's neighbors that are in
			// state [I], grab the one we're most confident in.
			Iterator<String> tableITR = neighborStateIEdgeTable.keySet().iterator();
			String edgeChoiceS = tableITR.next();		
			edgeChoiceAL.add(edgeChoiceS);
			
			// decrease choiceNum
			lowerAndCheckChoiceNum(node);
				
			// add this to the target node's offer table so it can make an informed
			// decision later on		
			Double edgeChoiceConfidenceD = neighborStateIEdgeTable.get(edgeChoiceS);
			Node chosenNode = graphRef.getEdge(edgeChoiceS).getOpposite(node);
			
LOGGER.info("attempting to add key/val pair to node: " + chosenNode + 
		"'s offers table: " + 
		edgeChoiceS + " " + 
		edgeChoiceConfidenceD);

			addToOfferTable(chosenNode, edgeChoiceS, edgeChoiceConfidenceD);
LOGGER.info("choiceNum is: " + choiceNumI + "\n");			
		}	
		// else if this node is not in state[I], make edge choices
		else if (!nodeStateS.contentEquals(STATE_I)) {
				Iterator<String> keyITR = confidenceTable.keySet().iterator();

				for (int i = 0; i < choiceNumI; i ++) {
					edgeChoiceAL.add(graphRef.getEdge(keyITR.next()).getId());	
					lowerAndCheckChoiceNum(node);
				}
				
		}
		// if we're here, it's because node is in state [I] and has one active edge
		// tell it to vote to keep that edge active and continue
		else {
			String currentActiveEdgeS = activeAdjacentEdgeAL.get(0);
			edgeChoiceAL.add(currentActiveEdgeS);	
			lowerAndCheckChoiceNum(node);
		}
		
	}
	
	
	
	
	
	// utility method to set a node's decision explicitly. 
	// used by nodes in state [I] to make an offer to connect to an adjacent 
	// node in state [I]
//	private void makeNodeChoice (Node node, String edge, boolean fromAdjudicator) {
//
//		// set node choice
//		setNodeChoice(node, edge, fromAdjudicator);
//	}
	


	// utility method to handle node choice action.
	//
	// performs check to sees if node has been processed this
	// cycle prior to doing anything else. this because this 
	// method may be called upon explicitly by a node adjacent 
	// to the target node - meaning the target node (the one 
	// being sent to this method) may not have been processed
	// by the cycle() loop yet. 
	//
	// boolean fromAdjudicator, when true, prevents method from removing items
	// from the potentialInvalidNodes table, and thus prevents a Concurrent
	// Modification Exception as that method is iterating over the same list. 
	// not to worry, though, the list will be cleared after the adjudicator is 
	// finished. 
//	private void setNodeChoice(Node node, String edgeChoice, boolean fromAdjudicator) {
//LOGGER.info("setting choice: " + edgeChoice + " for node: " + node.getId() + "\n");
//
//		// pre stage some variables
//		int choiceNum = node.getAttribute(CHOICE_NUM);
//		ArrayList<String> edgeChoiceAL = 
//				node.getAttribute(CHOICE_ARR);
//		
//		// make sure this node hasn't already processed this choice
//		// usually happens when adjacent nodes are in state [F]
//		if (edgeChoiceAL.contains(edgeChoice)) {
//LOGGER.info("rejecting attempt to add edge: " + edgeChoice + 
//		" because it's already in the list!");
//			return;
//		}
//		
//		// add edge to node's list of choices
//		edgeChoiceAL.add(edgeChoice);
//	
//		// update choice counter
//		choiceNum--;
//		node.setAttribute(CHOICE_NUM, choiceNum); 
//				
//		// check for case where node makes too many choices
//		if (choiceNum < 0) {
//			requestTermination(TOO_MANY_CHOICES_MADE + " for node: " + node);
//		}
//	
//		
//	}

	
	
	// utility method to handle node states
	@SuppressWarnings("unchecked")
	private void setNodeState(
			Node node, String state) {
LOGGER.fine("setting state: " + state + " for node: " + node);	
		// create local references to node neighbor tables
		// setup lookup table and lists for the states adjacent nodes.
		LinkedHashMap<String, String> adjacentNodeStateLHM = node.getAttribute(NEIGHBOR_STATE_LIST);
		ArrayList<Node> adjacentStateI_AL = node.getAttribute(NEIGHBOR_STATE_I_LIST);
		ArrayList<Node> adjacentStateF_AL = node.getAttribute(NEIGHBOR_STATE_F_LIST);
		
		// get node state
//		String nodeStateS = node.getAttribute(STATE);
		
		// check for state change before proceeding.
		// if state didn't change, no need to re-set it.
//		if (nodeStateS != null && state.contentEquals(nodeStateS)) {
//			return;
//		}
//		// otherwise, note that the graph state has changed ad proceed. 
//		else {
//			noChangeFlagB = false;
//		}
		
		switch(state) {
		

			case STATE_I: 
				
				// set state [I] for this node
				node.addAttribute(STATE, STATE_I);
				node.addAttribute(UI_CLASS, STATE_I);	
				break;
		
				
				
			case STATE_V: 
				
				// set state [V] for this node
				node.addAttribute(STATE, STATE_V);
				node.addAttribute(UI_CLASS, STATE_V);
				break;
				
				
				
			case STATE_N:
				
				// set state [N] for this node
				node.addAttribute(STATE, STATE_N);
				node.addAttribute(UI_CLASS, STATE_N);
				node.addAttribute(CHOICE_NUM, 2);
				
				// initialize active_adjacent_edge_list
				// done here and not in resetNodeChoiceVars() because that 
				// method gets called every cycle and this list needs to 
				// persist
				node.addAttribute(ACTIVE_ADJACENT_EDGE_LIST, new ArrayList<String>());
				
				// initialize offer table
				resetOfferTable(node);
				
				break;
				
				
		
			case STATE_F:
				
				// set state [F] for this node
				node.addAttribute(STATE, STATE_F);
				node.addAttribute(UI_CLASS, STATE_F);
				node.addAttribute(CHOICE_NUM, 0);
				
				/*
				 * activate edges between frozen node and neighbors.
				 * update adjacent node's choice variables
				 */
				for (String adjacentNodeS : adjacentNodeStateLHM.keySet()) {
					
					// get graph elements
					Node adjacentNodeN = graphRef.getNode(adjacentNodeS);
					Edge adjacentEdgeE = node.getEdgeToward(adjacentNodeS);
					
					// figure out how many active edges the adjacent node has
					ArrayList<String> adjNodeActiveEdgeAL = node.getAttribute(ACTIVE_ADJACENT_EDGE_LIST);
					int adjNodeActiveEdgeCountI = adjNodeActiveEdgeAL.size();
													
					// set edge state to active and set the frozen flag
					setEdgeState(adjacentEdgeE.getId(), true);
					
					// set frozen flag on edges
					adjacentEdgeE.addAttribute(FROZEN_F, true);
					
					
				}
			
			break;
				
		}
		
	}


	// for activating/deactivating edges
	// updates the activatedEdgesList for the nodes conjoined by this edge.
	// updates cycle change flag.
	private void setEdgeState(String edge, boolean activate) {
				
		// grab references to the nodes conjoined by this edge.
		Node node0 = graphRef.getEdge(edge).getNode0();
		ArrayList<String> node0_AL= node0.getAttribute(ACTIVE_ADJACENT_EDGE_LIST);
		
		Node node1 = graphRef.getEdge(edge).getNode1();
		ArrayList<String> node1_AL= node1.getAttribute(ACTIVE_ADJACENT_EDGE_LIST);
				
		if (activate) {		
			// activate the edges
			graphRef.getEdge(edge).addAttribute(STATE, STATE_A);
			graphRef.getEdge(edge).setAttribute(UI_CLASS, STATE_A);		
						
			// update the node lists and cycle change flag
			if (!node0_AL.contains(edge)) {
				node0_AL.add(edge);
				noChangeFlagB = false;
			}
			
			if (!node1_AL.contains(edge)) {
				node1_AL.add(edge);
				noChangeFlagB = false;
			}
			
			// update activeEdgesList
			activeEdgesHS.add(edge);
			
			
			// check to see if something needs to be deactivated
			checkForDisconnect(node0);
			checkForDisconnect(node1);
			
		}
		else {
			// deactivate the edges
			graphRef.getEdge(edge).addAttribute(STATE, STATE_D);
			graphRef.getEdge(edge).setAttribute(UI_CLASS, STATE_D);		
			
			// update the node lists and cycle change flag
			if (node0_AL.contains(edge)) {
				node0_AL.remove(edge);
				noChangeFlagB = false;
			}
			
			if (node1_AL.contains(edge)) {
				node1_AL.remove(edge);
				noChangeFlagB = false;
			}
			
			// figure out which node is put in state [I] as a result of this
			// operation and make it happen. 
			//if (node0_AL.size() != 2) { setNodeState(node0, STATE_I); }
			//if (node1_AL.size() != 2) { setNodeState(node1, STATE_I); }
			
			// update activeEdgesList
			activeEdgesHS.remove(edge);
			
		}
			
	}
	
	

	
	
	

	// for populating a node's tables with info about the state of its 
	// neighbors
	private void populateNodeNeighborTables(Node node) {
		
		// setup lookup table and lists for the states adjacent nodes.
		LinkedHashMap<String, String> adjacentNodeStateLHM = new LinkedHashMap<String, String>();
		LinkedHashMap<String, Double> adjacentState_I_EdgesLHM = new LinkedHashMap<String, Double>();
		ArrayList<Node> adjacentStateI_AL = new ArrayList<Node>();
		ArrayList<Node> adjacentStateF_AL = new ArrayList<Node>();
		
		// create reference to this node's edge confidence table
		LinkedHashMap <String, Double> confidenceTable = 
				node.getAttribute(EDGE_CONFIDENCE_TABLE);
		
		// figure out the state of this node's neighbors
		for (Edge adjacentEdge : node.getEdgeSet()) {
			Node adjacentNode = adjacentEdge.getOpposite(node);
			adjacentNodeStateLHM.put(adjacentNode.getId(), adjacentNode.getAttribute(STATE));
			
			// put nodes in state [I] and [F] in their own tables for easy 
			// access
			if (adjacentNode.getAttribute(STATE) == STATE_I) {
				String adjacentEdgeNameS = node.getEdgeToward(adjacentNode).getId();
				Double edgeConfidenceD = confidenceTable.get(adjacentEdgeNameS);
				adjacentState_I_EdgesLHM.put(adjacentEdgeNameS, edgeConfidenceD);
				adjacentStateI_AL.add(adjacentNode);
				
			}
			else if (adjacentNode.getAttribute(STATE) == STATE_F) {
				adjacentStateF_AL.add(adjacentNode);
			}
			
		}
		
		// sort the state [I] table
		adjacentState_I_EdgesLHM = 
				(LinkedHashMap<String, Double>) 
					mapSortUtils.
						sortByValuesDescending(adjacentState_I_EdgesLHM);
 
		// add tables to the node
		node.addAttribute(NEIGHBOR_STATE_LIST, adjacentNodeStateLHM);
		node.addAttribute(NEIGHBOR_STATE_I_EDGE_TABLE, adjacentState_I_EdgesLHM);
		node.addAttribute(NEIGHBOR_STATE_I_LIST, adjacentStateI_AL);
		node.addAttribute(NEIGHBOR_STATE_F_LIST, adjacentStateF_AL);
		
	}


	// resets the node choice variables
	private void resetNodeChoiceVars(Node node) {
LOGGER.info("resetting choice variables for node: [" + node.getId() + "]");

		// figure out how many adjacent nodes are in state [F].
		// figure out this node's number of choices by subtracting
		// the number of adjacent frozen nodes from two
		ArrayList<Node> adjacentStateF_AL = 
				node.getAttribute(NEIGHBOR_STATE_F_LIST);
		
		int choiceNumI = 2;
		
		if (adjacentStateF_AL != null) {
			choiceNumI -= adjacentStateF_AL.size();
		}
		
		node.addAttribute(SUCCESS_NUM, 0);
		node.addAttribute(CHOICE_NUM, choiceNumI);
		node.addAttribute(CHOICE_ARR, new ArrayList<String>());
	}
	
	
	// clear a node's disconnect candidate list 
	private void resetEdgeDisconnectCandidateList(Node node) {
		// populated when nodes are trying to figure out their choices for the 
		// current cycle and have neighbors that are in state [I]. this way, if
		// the offer to one of those nodes is accepted, we know which edge to
		// disconnect so that the node always has no more than two active 
		// connections. 
		// *NOTE* this list is populated in ascending order of confidence score 
		node.addAttribute(EDGE_DISCONNECT_CANDIDATE_LIST, new ArrayList<String>());
	}
	
	
	// clears a node's offer table
	// called during adjudicate()
	private void resetOfferTable(Node node) {
		// offers table is used by nodes in state [I] to keep track of what
		// edges are being offered and with what confidence
		node.addAttribute(OFFERS_TABLE, new LinkedHashMap<String, Double>());
	}
	
	
	// utility method to handle additions to an adjacent node's offer table 
	private void addToOfferTable(Node targetNode, 
			String edgeName, Double edgeConfidence) {
						
		LinkedHashMap<String, Double> offersTableLHM = 
				targetNode.getAttribute(OFFERS_TABLE);
		
		// reject attempts to add already active edges to a node's offers table
		ArrayList<String> activeAdjacentEdgeAL = 
				targetNode.getAttribute(ACTIVE_ADJACENT_EDGE_LIST);
		
		if (activeAdjacentEdgeAL.contains(STATE_A)) {
LOGGER.info("edge " + edgeName + " already active! returning to caller...");	
			return;
		}
		
				
LOGGER.info("added key/val pair to node: " + targetNode + "'s offers table: " + 
		edgeName + " " + edgeConfidence);			
		
		offersTableLHM.put(edgeName, edgeConfidence);	
	}

	
	// checks to see if a node has three edges activated. if so, we grab the 
	// disconnect candidate list and deactivate the root element of that list.
	private void checkForDisconnect(Node node) {
		
		// grab the disconnect list from the node connected to the 
		// winning edge so if need be we can deactivate 
		// the activated edge it has the least confidence in. 
		ArrayList<String> disconnectAL = node.getAttribute(EDGE_DISCONNECT_CANDIDATE_LIST);
		
		// return to caller if this table is null
		if (disconnectAL == null) { return; }
		
		@SuppressWarnings("unchecked")
		int nodeActiveEdgeListSizeI = 
				((ArrayList<String>)
						node.
						getAttribute(ACTIVE_ADJACENT_EDGE_LIST)).
						size();
LOGGER.info("about to deactivate node [" + node.getId() + "]'s edge from disconnect list! disconnectAL is: " + disconnectAL);
LOGGER.info("adjacent node active edge count is: " + nodeActiveEdgeListSizeI);
		if (!disconnectAL.isEmpty() && nodeActiveEdgeListSizeI == 3) {
			String edgeS = disconnectAL.get(0);
			setEdgeState(edgeS, false);
		}
		else if (nodeActiveEdgeListSizeI == 3) {
			requestTermination(TOO_MANY_ACTIVE_EDGES + " for node: " + node.getId());
		}
		
	}
	
	
	/*
	 * utility method for setting choice num variable for a node. 
	 * checks to ensure this node doens't make more than two choices. 
	 * if it does, terminate flag is set.
	 */
	private void lowerAndCheckChoiceNum (Node node) {
		// get current choice num
		int choiceNumI = node.getAttribute(CHOICE_NUM);
		choiceNumI --;
		
		// check that this node hasn't made too many choices
		// before proceeding. 
		if (choiceNumI < 0) {
			requestTermination(TOO_MANY_CHOICES_MADE + 
					" for node: [" + node.getId() + "]");
		}
		else {
			node.setAttribute(CHOICE_NUM, choiceNumI);
		}
		
	}
	
	
}


