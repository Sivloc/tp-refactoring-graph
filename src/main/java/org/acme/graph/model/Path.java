package org.acme.graph.model;

import java.util.ArrayList;
import java.util.List;

public class Path {
	List<Edge> edges;
	
	public List<Edge> getEdges() {
		return edges;
	}

	public Path() {
		edges = new ArrayList<>();
	}
	
	public Path(List<Edge> edges) {
		this.edges = edges;
	}
	
	public double getLength() {
		double length = 0;
		for(Edge edge : edges) {
			length += edge.getGeometry().getLength();
		}
		return length;
	}
}
