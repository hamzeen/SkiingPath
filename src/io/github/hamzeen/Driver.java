package io.github.hamzeen;

import io.github.hamzeen.skiing.ElevationPoint;
import io.github.hamzeen.skiing.SkiingPathHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Scanner;

/**
 * Driver to test the best path for given elevations.
 * 
 * @author Hamzeen. H.
 * @created: Thursday, 30th July 2015
 */
public class Driver {

	/** holds an instance of Random to generate random numbers */
	public static final Random RANDOM = new Random();

	/** Class holding the constants of this application */
	public class Constants {
		public static final String DATA_FILE_PATH = "data/map_data.txt";
	}

	/**
	 * The main method execution starts here..
	 */
	public static void main(String[] args) {

		ElevationPoint[][] elevationPoints = null;

		File dataSource = new File(Constants.DATA_FILE_PATH);
		elevationPoints = readElevationMap(dataSource);

		if(elevationPoints!=null) { // in case if elevation points weren't set
			SkiingPathHelper skiingHelper = new SkiingPathHelper(
					elevationPoints);
			skiingHelper.displayBestPath();
		}
	}

	/**
	 * A helper method to generate random numbers between 0 - 1500.
	 * 
	 * @return
	 */
	public static int randomInt() {
		return RANDOM.nextInt((1500) + 1);
	}

	/**
	 * reads the elevation map from the given data file.
	 * Expects a text or CSV file.
	 * 
	 * @param dataFile
	 */
	private static ElevationPoint[][] readElevationMap(File dataFile) {
		try {
			ElevationPoint[][] elevationPoints = null;
			Scanner sc = new Scanner(dataFile);

			String[] data = null;
			int[] size = new int[2];
			if (sc.hasNextLine()) {
				data = sc.nextLine().split(" ");

				if (data.length != 2) {
					throw new IllegalArgumentException("Incompatible First Line: expected [cols] [rows]");
				} else {
					for (int i = 0; i < data.length; i++)
						size[i] = Integer.parseInt(data[i]);
					elevationPoints = new ElevationPoint[size[0]][size[1]];
				}
			}

			int idxRow = 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.isEmpty())
					break;
				data = line.split(" ");
				if (data.length != size[1])
					throw new IllegalArgumentException(
							"There was a mismatch in columns count");

				for (int colIndex = 0; colIndex < data.length; colIndex++)
					elevationPoints[idxRow][colIndex] = new ElevationPoint(
							Integer.parseInt(data[colIndex]));
				idxRow++;
			}

			if (idxRow != size[0])
				throw new IllegalArgumentException(
						"You have chosen an invalid or an unformatted data-set");

			return elevationPoints;
		} catch (Exception e) {
			System.out
					.println("Couldn't get the data file, check whether the path & filename is correct"
							+ e.getMessage());
			return null;
		}
	}
}
