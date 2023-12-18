package org.acme.graph.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathTree {

	private static final Logger log = LogManager.getLogger(PathTree.class);
	
	private Map<Vertex, PathNode> nodes;

	public PathTree(Graph graph, Vertex origin) {
		log.trace("initGraph{})", origin);
		nodes = new HashMap<>();
		for (Vertex vertex : graph.getVertices()) {
			PathNode pathnode = new PathNode(origin == vertex ? 0.0 : Double.POSITIVE_INFINITY, null, false);
			nodes.put(vertex, pathnode);
		}
	}

	public Path getPath(Vertex destination) {
		Path result = new Path();
		Edge current = getNode(destination).getReachingEdge();
		do {
			result.edges.add(current);
			current = getNode(current.getSource()).getReachingEdge();
		} while (current != null);
		Collections.reverse(result.edges);
		return result;
	}

	public PathNode getNode(Vertex vertex) {
		return nodes.get(vertex);
	}
}
