package org.acme.graph.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
 
import org.acme.graph.errors.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.locationtech.jts.geom.Coordinate;

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
		Graph graph = new Graph();
				
		Vertex a = graph.createVertex(new Coordinate(0.0,0.0), "a");
		graph.getVertices().add(a);

		Vertex b = graph.createVertex(new Coordinate(1.0,0.0), "b");
		graph.getVertices().add(b);

		Vertex c = graph.createVertex(new Coordinate(2.0,0.0), "c");
		graph.getVertices().add(c);

		Vertex d = graph.createVertex(new Coordinate(1.0,1.0), "d");
		graph.getVertices().add(d);

		Edge ab = graph.createEdge(a, b, "ab");
		graph.getEdges().add(ab);

		Edge bc = graph.createEdge(b, c, "bc");
		graph.getEdges().add(bc);

		Edge ad = graph.createEdge(a, d, "ad");
		graph.getEdges().add(ad);
		
		for (Vertex vertex : graph.getVertices()) {
			PathNode pathnode = new PathNode(origin == vertex ? 0.0 : Double.POSITIVE_INFINITY, null, false);
			nodes.put(vertex, pathnode);
		}
		this.origin = origin;
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
