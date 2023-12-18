package org.acme.graph.model;

public class PathNode {
	/**
	 * dijkstra - coût pour atteindre le sommet
	 */
	private double cost;
	/**
	 * dijkstra - arc entrant avec le meilleur coût
	 */
	private Edge reachingEdge;
	/**
	 * dijkstra - indique si le sommet est visité
	 */
	private boolean visited;
	
	public PathNode(double cost, Edge reachingEdge, boolean visited) {
		this.cost = cost;
		this.reachingEdge = reachingEdge;
		this.visited = visited;
	}

	public double getCost() {
		return cost;
	}

	public Edge getReachingEdge() {
		return reachingEdge;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setReachingEdge(Edge reachingEdge) {
		this.reachingEdge = reachingEdge;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
}
