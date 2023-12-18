package org.acme.graph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;

/**
 * 
 * Un sommet dans un graphe
 * 
 * @author MBorne
 *
 */
public class Vertex {

	/**
	 * Identifiant du sommet
	 */
	private String id;

	/**
	 * Position du sommet
	 */
	private Coordinate coordinate;

	/**
	 * Bords entrant et sortant
	 */
	
	@JsonIgnore
	List<Edge> inEdges;

	@JsonIgnore
	List<Edge> outEdges;

	Vertex() {
		inEdges = new ArrayList<>();
		outEdges = new ArrayList<>();
	}

	public List<Edge> getInEdges() {
		return inEdges;
	}

	public List<Edge> getOutEdges() {
		return outEdges;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	@Override
	public String toString() {
		return id;
	}

}
