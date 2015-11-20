package org.dongzhou.rescueTime.kmeans;

import java.util.ArrayList;
import java.util.List;

import org.dongzhou.rescueTime.Day;

public class Centroid extends Point {

	private int id;

	public Centroid() {

	}

	public Centroid(int id, Day day) {
		super(day);
		this.id = id;
	}

	public Centroid(int id, Point point) {
		this(id, point.day);
		this.id = id;
	}

	private List<Point> points = new ArrayList<Point>();

	public void addPoint(Point point) {
		points.add(point);
	}

	public int getId() {
		return id;
	}

	public List<Point> getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return this.id + " " + super.toString();
	}

}
