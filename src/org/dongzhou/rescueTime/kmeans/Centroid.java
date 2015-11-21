package org.dongzhou.rescueTime.kmeans;

import java.util.ArrayList;
import java.util.List;

import org.dongzhou.rescueTime.Day;

public class Centroid extends Point {

	public Centroid(Day day) {
		super(day);
	}

	private List<Point> points = new ArrayList<Point>();

	public void resetPoints() {
		points = new ArrayList<>();
	}

	public void addPoint(Point point) {
		points.add(point);
	}

	public List<Point> getPoints() {
		return points;
	}

}
