package org.acme.graph.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.acme.graph.errors.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathTree {

	private static final Logger log = LogManager.getLogger(PathTree.class);
	
	private Map<Vertex, PathNode> nodes;
		
	private Vertex origin;

	public PathTree(Graph graph, Vertex origin) {
		log.trace("initGraph{})", origin);
		nodes = new HashMap<>();
		for (Vertex vertex : graph.getVertices()) {
			PathNode pathnode = new PathNode(origin == vertex ? 0.0 : Double.POSITIVE_INFINITY, null, false);
			nodes.put(vertex, pathnode);
		}
		this.origin = origin;
	}
	
	public PathTree(Vertex origin) {
		log.trace("initGraph{})", origin);
		nodes = new HashMap<>();
		
	}

	public Path getPath(Vertex destination) {
		Path result = new Path();
		Edge current = getNode(destination).getReachingEdge();
		do {
			result.edges.add(current);
			current = getNode(current.getSource()).getReachingEdge();
		}
		while (current != null);
		Collections.reverse(result.edges);
		if(isReached(destination)) {
			return result;
		}throw new NotFoundException(String.format("Sommet non atteint"));
		
	}

	public PathNode getNode(Vertex vertex) {
		return nodes.get(vertex);
	}
	
	public boolean isReached(Vertex destination) {
		return getNode(destination).getReachingEdge() != null;
	}
	
	public PathNode getOrCreateNode(Vertex vertex) {
		if(nodes.get(vertex)!=null) {
			return nodes.get(vertex);
		}
		PathNode node = new PathNode(origin == vertex ? 0.0 : Double.POSITIVE_INFINITY, null, false);
		nodes.put(vertex,node);
		return node;
	}
	
	public Collection<Vertex> getReachedVertices(){
		Collection<Vertex> result = new ArrayList<>();
		for(Vertex vertex : nodes.keySet()) {
			if(isReached(vertex)) {
				result.add(vertex);
			}
		}
		return result;
	}
}
