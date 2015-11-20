package org.dongzhou.rescueTime.kmeans;

import org.dongzhou.rescueTime.Day;

public class Centroid extends Point {

	public int id;

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

	@Override
	public String toString() {
		return this.id + " " + super.toString();
	}

}
