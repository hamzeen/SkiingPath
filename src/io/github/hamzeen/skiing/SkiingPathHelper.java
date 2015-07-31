package io.github.hamzeen.skiing;

import java.util.List;

/**
 * This class defines an algorithm along with some helper methods to find the
 * path that will ensure the best skiing experience for a given map of data.
 * 
 * It attempts to find this path with the highest steep one could maneuver for
 * the given data-set.
 * 
 * @author Hamzeen. H.
 * @created: Thursday, 30th July 2015
 */
public class SkiingPathHelper {

	ElevationPoint[][] arryElevations;

	int mapWidth, mapHeight;

	public SkiingPathHelper(ElevationPoint[][] data) {
		this.arryElevations = data;
		this.mapWidth = data.length;
		this.mapHeight = data[0].length;
		this.setupData();
	}

	/**
	 * Prints out the best path based on highest height difference & longest
	 * node length
	 */
	public void displayBestPath() {
		ElevationPoint elevation = getHighestEP();
		String path = "";
		int length = 0;

		while (elevation.nextDepth != null) {
			path += elevation.elevation + " >> ";
			elevation = elevation.nextDepth;
			length++;
		}

		path += elevation.elevation;
		length++;

		System.out.println(":: The Best Path ::");
		System.out.println(path);
		System.out.println("\n:: Length of the Path: " + length);
		System.out.println(":: Map Dimensions: [" +  this.mapWidth + " x " + this.mapHeight+"]");
	}

	/**
	 * Finds the greatest steep from all the points in the data-set.
	 */
	private ElevationPoint getHighestEP() {
		ElevationPoint highest = arryElevations[0][0];
		for (int i = 0; i < mapWidth; i++) {
			for (int j = 0; j < mapHeight; j++) {
				if (arryElevations[i][j].maxDepth < highest.maxDepth) {
					continue;
				} else if (arryElevations[i][j].maxDepth > highest.maxDepth) {
					highest = arryElevations[i][j];
				} else {
					highest = getDeepestEP(highest, arryElevations[i][j]);
				}
			}
		}
		return highest;
	}

	/**
	 * This method marks neighboring steep nodes 
	 * for all the points in the data map & then 
	 * marks their depth nodes.
	 * 
	 * This method is precondition to predict the best-path.
	 */
	private void setupData() {

		// marks neighbor steep nodes
		for (int i = 0; i < this.mapWidth; i++) {

			for (int j = 0; j < this.mapHeight; j++) {

				int tempElevation = this.arryElevations[i][j].elevation;
				List<ElevationPoint> currentAdjcnt = this.arryElevations[i][j].lstAdjacent;

				if (isValidBounds(i, j - 1) // north
						&& this.arryElevations[i][j - 1].elevation < tempElevation)
					currentAdjcnt.add(this.arryElevations[i][j - 1]);

				if (isValidBounds(i, j + 1) // south
						&& this.arryElevations[i][j + 1].elevation < tempElevation)
					currentAdjcnt.add(this.arryElevations[i][j + 1]);

				if (isValidBounds(i - 1, j) // west
						&& this.arryElevations[i - 1][j].elevation < tempElevation)
					currentAdjcnt.add(this.arryElevations[i - 1][j]);

				if (isValidBounds(i + 1, j) // east
						&& this.arryElevations[i + 1][j].elevation < tempElevation)
					currentAdjcnt.add(this.arryElevations[i + 1][j]);
			}
		}
		
		// marks depths of all the elevation points.
		for (int i = 0; i < mapWidth; i++) {
			for (int j = 0; j < mapHeight; j++) {
				getDepthEP(arryElevations[i][j]);
			}
		}
	}

	/**
	 * checks whether the given index in the data map is valid.
	 */
	private boolean isValidBounds(int i, int j) {
		boolean result = false;
		if (i >= 0 && j >= 0 && i < mapWidth && j < mapHeight)
			result = true;

		return result;
	}

	/**
	 * This method finds the depth of a node & 
	 * updates it maximum depth.
	 * 
	 * @param ep
	 */
	private ElevationPoint getDepthEP(ElevationPoint ep) {

		if (ep.visited)
			return ep;

		ep.visited = true;
		List<ElevationPoint> adjSteepNodes = ep.lstAdjacent;

		if (adjSteepNodes.isEmpty()) {
			ep.maxDepth = 0;
			ep.nextDepth = null;
		} else {
			ElevationPoint maxElevation = getDepthEP(adjSteepNodes.get(0));
			for (int i = 1; i < adjSteepNodes.size(); i++) {
				ElevationPoint nextElevation = getDepthEP(adjSteepNodes
						.get(i));
				if (maxElevation.maxDepth < nextElevation.maxDepth)
					maxElevation = nextElevation;
				else {
					if (nextElevation.maxDepth == maxElevation.maxDepth) {
						maxElevation = getDeepestEP(nextElevation, maxElevation);
					}
				}

			}

			ep.maxDepth = maxElevation.maxDepth;
			ep.nextDepth = maxElevation;
			ep.base = maxElevation.base;
		}

		ep.maxDepth++;
		return ep;
	}

	/**
	 * Compares two given <code>ElevationPoint</code> objects to determine the
	 * deepest.
	 */
	private ElevationPoint getDeepestEP(ElevationPoint source, ElevationPoint ref) {
		boolean comparison = source.elevation - source.base > ref.elevation
				- ref.base;
		return (comparison) ? source : ref;
	}
}
