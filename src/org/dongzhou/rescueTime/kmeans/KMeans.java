package org.dongzhou.rescueTime.kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dongzhou.rescueTime.Day;
import org.dongzhou.rescueTime.RescueTime;

public class KMeans {

	private static Logger logger = Logger.getLogger(KMeans.class.getName());

	public static double error = 0;
	public static double miniError = Double.MAX_VALUE;
	public static int centroidSize = 3;
	public static List<Point> points = new ArrayList<Point>();
	public static List<Centroid> centroids = new ArrayList<>();

	public static void showResult() {
		for (Centroid centroid : centroids) {
			System.out.println(centroid);
		}
	}

	public static void train() {
		while (true) {
			for (Point point : points) {
				double minimize = Double.MAX_VALUE;
				Centroid miniCentroid = null;
				for (Centroid centroid : centroids) {
					double distance = point.euclideanDistance(centroid);
					if (distance < minimize) {
						minimize = distance;
						miniCentroid = centroid;
					}
				}
				miniCentroid.addPoint(point);
				error += minimize;
			}

			logger.info(miniError);
			logger.info(error);

			if (error < miniError)
				miniError = error;
			else if (error == miniError)
				break;
			error = 0;

			for (Centroid centroid : centroids) {
				Point newCentroid = new Point();
				for (Point point : centroid.getPoints())
					newCentroid.add(point);
				newCentroid.div(centroid.getPoints().size());
				centroid = new Centroid(newCentroid);
			}
		}
	}

	private static void setPoints() {
		List<Day> days = RescueTime.getDays();
		for (int i = 0; i < 10; i++) // test get more data
			for (Day day : days) {
				points.add(new Point(day));
			}
	}

	private static void setCentroids() {
		for (int i = 0; i < centroidSize; i++) {
			int random = getRandom(points.size());
			centroids.add(new Centroid(points.get(random)));
		}
	}

	static Map<Integer, Integer> check = new HashMap<Integer, Integer>();

	private static int getRandom(int size) {
		int random = (int) (Math.random() * size);
		if (check.containsKey(random))
			return getRandom(size);
		else {
			check.put(random, 1);
			return random;
		}
	}

	public static void main(String args[]) {
		setPoints();
		setCentroids();
		train();
		showResult();
	}

}