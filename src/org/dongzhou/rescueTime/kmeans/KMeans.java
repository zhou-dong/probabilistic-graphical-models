package org.dongzhou.rescueTime.kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dongzhou.rescueTime.Day;
import org.dongzhou.rescueTime.RescueTime;

public class KMeans {

	public static double error = 0;
	public static double miniError = Double.MAX_VALUE;
	public static int centroidSize = 3;
	public static List<Point> points = new ArrayList<Point>();
	public static List<Centroid> centroids = new ArrayList<>();
	private static Map<Integer, Integer> randoms = new HashMap<Integer, Integer>();

	public static void main(String args[]) {
		setPoints();
		setCentroids();

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

			if (error == miniError) {
				break;
			}

			int index = 0;
			for (Centroid centroid : centroids) {
				Point newCentroid = new Point();
				for (Point point : centroid.getPoints())
					newCentroid.add(point);
				newCentroid.div(centroid.getPoints().size());
				centroid = new Centroid(index, newCentroid);
				index++;
			}
		}

	}

	private static void setPoints() {
		List<Day> days = RescueTime.getDays();
		for (Day day : days) {
			points.add(new Point(day));
		}
	}

	private static void setCentroids() {
		for (int i = 0; i < centroidSize; i++) {
			int random = getRandom(points.size());
			centroids.add(new Centroid(i, points.get(random)));
		}
	}

	private static int getRandom(int size) {
		int random = (int) (Math.random() * points.size());
		if (randoms.containsKey(randoms))
			getRandom(size);
		else
			randoms.put(random, 1);
		return random;
	}
}
