package com.projectvalis.altk.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import com.projectvalis.altk.algorithm.holidayTSP1;


public class TSPLIBUtils {
	
	// parses a file from the TSPLib set.
	// creates a graphstream graph object from the file.
	// returns the graph object to caller
	public static Graph loadFile(String fileName) {
		
		// create graph object and give it the same name as the file being
		// loaded.
		Graph graph = new SingleGraph(fileName);
		
		// load file
		try {
			URL fileURL = Thread.currentThread().
					getContextClassLoader().getResource("lib/TSPLIB/" + fileName);

			File file = new File(fileURL.toURI());	
			FileInputStream fis = new FileInputStream(file);

			//Construct BufferedReader from InputStreamReader
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));

			String line = null;
			boolean startB = false;

			// parse file to populate graph with vertices
			while ((line = br.readLine()) != null) {

				// don't start parsing until we've gotten past all the
				// metadata. *NOTE* there will be more metadata flags
				// to parse out -- startB should be kept as the master
				// switch. 
				if (line.contentEquals("NODE_COORD_SECTION")) {
					startB = true;
					continue;
				}

				// ok to start parsing node locations? 
				if (startB == true) {

					// parse out the line and variables
					String[] delimitedLineARR = line.split("\\s+");
					
					// no need to try and parse the EOF marker
					if ((line.contentEquals("EOF")) || (delimitedLineARR.length < 3)) {
						continue;
					}
					
					String nodeNameS = delimitedLineARR[0];
					Double xPosD = Double.parseDouble(delimitedLineARR[1]);
					Double yPosD = Double.parseDouble(delimitedLineARR[2]);

					// add node to the graph
					graph.addNode(nodeNameS);
					graph.getNode(nodeNameS).addAttribute("x", xPosD);
					graph.getNode(nodeNameS).addAttribute("y", yPosD);
					
					// show node labels
					graph.getNode(nodeNameS).addAttribute("ui.label", nodeNameS);

				}

			}

			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		// connect every node to every other node
		for (Node node : graph.getEachNode()) {
			
			for (Node otherNode : graph.getEachNode()) {
				String edgeID = node.getId() + "--" + otherNode.getId();

				try {
					
					if (!node.getId().contentEquals(otherNode.getId())) {
						graph.addEdge(edgeID, node, otherNode);
						
						double lengthD = distance(node.getId(), otherNode.getId(), graph, 2);
						graph.getEdge(edgeID).setAttribute("length", lengthD);
						//graph.getEdge(edgeID).setAttribute(holidayTSP1.STATE, holidayTSP1.STATE_D);
					}
					
				} catch (Exception e) {
					
				}
				
				
			}
			
		}
		
			
		// return graph object to caller
		return graph;
	}
	
	
	
	

	/**
	 * Distance between two nodes.
	 *
	 * @param n1
	 * first node
	 * @param n2
	 * second node
	 * @return distance between n1 and n2
	 * 
	 * 
	 * from 
	 * org.graphstream.algorithm.generator.RandomEuclideanGenerator
	 * 
	 * not sure why this wasn't exposed in the graphstream toolkit...
	 */
	public static double distance(String n1, String n2, Graph graph, int dimension) {
		double d = 0.0;
		if (dimension == 2) {
			double x1 = graph.getNode(n1).getNumber("x");
			double y1 = graph.getNode(n1).getNumber("y");
			double x2 = graph.getNode(n2).getNumber("x");
			double y2 = graph.getNode(n2).getNumber("y");
			d = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
		} else if (dimension == 3) {
			double x1 = graph.getNode(n1).getNumber("x");
			double y1 = graph.getNode(n1).getNumber("y");
			double x2 = graph.getNode(n2).getNumber("x");
			double y2 = graph.getNode(n2).getNumber("y");
			double z1 = graph.getNode(n1).getNumber("z");
			double z2 = graph.getNode(n2).getNumber("z");
			d = Math.pow(z1 - z2, 2) + Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
		} else {
			for (int i = 0; i < dimension; i++) {
				double xi1 = graph.getNode(n1).getNumber("x" + i);
				double xi2 = graph.getNode(n2).getNumber("x" + i);
				d += Math.pow(xi1 - xi2, 2);
			}
		}
		return Math.sqrt(d);
	}
	
	
	
	
	
}




