package org.dongzhou.rescueTime.kmeans;

import java.util.ArrayList;
import java.util.List;

public class Centroid extends Point {

	public Centroid() {
	}

	public Centroid(Point point) {
		super(point);
	}

	private List<Point> points = new ArrayList<Point>();

	public void addPoint(Point point) {
		points.add(point);
	}

	public List<Point> getPoints() {
		return points;
	}

}
