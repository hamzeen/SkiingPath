package io.github.hamzeen.skiing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * @author Hamzeen. H.
 * @created: Thursday, 30th July 2015
 */
public class ElevationPoint {

	ElevationPoint m_elevationPoint = null;

	/** List of adjacent rise(s) */
	List<ElevationPoint> lstAdjacent;

	ElevationPoint nextDepth;

	/** List to hold the deepest path */
	LinkedList<ElevationPoint> lstDeepestPath;

	boolean visited;

	int base;

	int elevation;

	int maxDepth;

	public ElevationPoint(int rise) {
		this.lstAdjacent = new ArrayList<ElevationPoint>();
		this.base = elevation;
		this.lstDeepestPath = new LinkedList<ElevationPoint>();
		this.visited = false;
		this.elevation = rise;
		this.maxDepth = 0;
	}

	public ElevationPoint(ElevationPoint elevationPoint) {
		this.m_elevationPoint = elevationPoint;
	}
}