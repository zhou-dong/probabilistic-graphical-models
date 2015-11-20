package org.dongzhou.rescueTime.kmeans;

import java.util.ArrayList;
import java.util.List;

import org.dongzhou.rescueTime.Day;
import org.dongzhou.rescueTime.RescueTime;

public class KMeans {

	public static double errors = 0d;
	public static List<Point> points = new ArrayList<Point>();
	public static List<Centroid> centroids = new ArrayList<>();

	public static void main(String args[]) {
		List<Day> days = RescueTime.getDays();
		for (Day day : days) {
			points.add(new Point(day));
		}
		for (int i = 0; i < 2; i++) {
			double d = Math.random();

		}
		int t = (int) (d * 100);
		System.out.println(t);
	}

}
