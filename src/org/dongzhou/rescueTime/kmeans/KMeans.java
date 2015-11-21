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
	public static int centroidSize = 2;
	public static List<Point> points = new ArrayList<Point>();
	public static List<Centroid> centroids = new ArrayList<>();

	public static void showResult() {
		for (int i = 0; i < centroids.size(); i++) {
			logger.info("size of clouster " + i + ": " + centroids.get(i).getPoints().size());
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

			logIterator();

			if (miniError - error < 1)
				break;
			else if (error < miniError)
				miniError = error;
			else if (error >= miniError)
				break;
			error = 0;

			for (Centroid centroid : centroids) {
				centroid.clear();
				List<Point> points = centroid.getPoints();
				for (Point point : points)
					centroid.add(point);
				centroid.div(points.size());
				centroid.resetPoints();
			}

		}
	}

	protected static void setTestPoints(int size) {
		for (int i = 0; i < size; i++)
			points.add(Point.createExamplePoint());
	}

	protected static void setPoints() {
		List<Day> days = RescueTime.getDays();
		for (Day day : days) {
			points.add(new Point(day));
		}
	}

	private static void setCentroids() {
		for (int i = 0; i < centroidSize; i++) {
			int random = getRandom(points.size());
			centroids.add(new Centroid(points.get(random).day));
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

	private static void logIterator() {
		StringBuffer logBuffer = new StringBuffer();
		logBuffer.append("before: ").append((int) miniError);
		logBuffer.append(" end: ").append((int) error);
		logger.info(logBuffer.toString());
	}

	public static void main(String args[]) {
		// setTestPoints(8);
		setPoints();
		setCentroids();
		train();
		showResult();
	}

}