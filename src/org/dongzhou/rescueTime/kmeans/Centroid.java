package org.dongzhou.rescueTime.kmeans;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dongzhou.rescueTime.Day;

import com.mongodb.BasicDBList;

public class Centroid extends Point {

	private static Logger logger = Logger.getLogger(Centroid.class.getName());

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

	public void displayPoints() {
		logger.info("points size is: " + points.size());
		BasicDBList list = new BasicDBList();
		for (Point point : points)
			list.add(point.getJson());
		logger.info(list);
	}

}
