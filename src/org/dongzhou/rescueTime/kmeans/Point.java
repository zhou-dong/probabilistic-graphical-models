package org.dongzhou.rescueTime.kmeans;

import org.dongzhou.rescueTime.Day;

import com.mongodb.BasicDBObject;

public class Point {

	public String date;
	public Day day;
	public double totalHours;
	public double productiveHours;
	public double productivePercentage;
	public double distractingHours;
	public double distractingPercentage;
	public double neutralHours;
	public double neutralPercentage;

	public Point(Day day) {
		this.day = day;
		this.date = day.getDate();
		this.totalHours = day.getTotalHours();
		this.productiveHours = day.getProductiveHours();
		this.productivePercentage = day.getProductivePercent() / 100;
		this.distractingHours = day.getDistractingHours();
		this.distractingPercentage = day.getDistractingPercent() / 100;
		this.neutralHours = day.getNeutralHours();
		this.neutralPercentage = day.getNeutralPercent() / 100;
	}

	public void add(Point other) {
		this.totalHours += other.totalHours;
		this.productiveHours += other.productiveHours;
		this.productivePercentage += other.productivePercentage;
		this.distractingHours += other.distractingHours;
		this.distractingPercentage += other.distractingPercentage;
		this.neutralHours += other.neutralHours;
		this.neutralPercentage += other.neutralPercentage;
	}

	public Point div(double value) {
		this.totalHours /= value;
		this.productiveHours /= value;
		this.productivePercentage /= value;
		this.distractingHours /= value;
		this.distractingPercentage /= value;
		this.neutralHours /= value;
		this.neutralPercentage /= value;
		return this;
	}

	public double euclideanDistance(Point other) {
		double totalHourDistance = (other.totalHours - totalHours)
				* (other.totalHours - totalHours);
		double productiveHoursDistance = (other.productiveHours - productiveHours)
				* (other.productiveHours - productiveHours);
		double productivePercentageDistance = (other.productivePercentage - productivePercentage)
				* (other.productivePercentage - productivePercentage);
		double distractingHoursDistance = (other.distractingHours - distractingHours)
				* (other.distractingHours - distractingHours);
		double distractingPercentageDistance = (other.distractingPercentage - distractingPercentage)
				* (other.distractingPercentage - distractingPercentage);
		double neutralHoursDistance = (other.neutralHours - neutralHours)
				* (other.neutralHours - neutralHours);
		double neutralPercentageDistance = (other.neutralPercentage - neutralPercentage)
				* (other.neutralPercentage - neutralPercentage);
		return Math.sqrt(totalHourDistance + productiveHoursDistance + productivePercentageDistance
				+ distractingHoursDistance + distractingPercentageDistance + neutralHoursDistance
				+ neutralPercentageDistance);
	}

	public void clear() {
		totalHours = 0d;
		productiveHours = 0d;
		productivePercentage = 0d;
		distractingHours = 0d;
		distractingPercentage = 0d;
		neutralHours = 0d;
		neutralPercentage = 0d;
	}

	@Override
	public String toString() {
		return getJson().toString();
	}

	public BasicDBObject getJson() {
		BasicDBObject result = new BasicDBObject();
		result.append("totalHours", totalHours);
		result.append("productiveHours", productiveHours);
		result.append("productivePercentage", productivePercentage);
		result.append("distractingHours", distractingHours);
		result.append("distractingPercentage", distractingPercentage);
		result.append("neutralHours", neutralHours);
		result.append("neutralPercentage", neutralPercentage);
		return result;

	}

	public static Point createExamplePoint() {
		BasicDBObject example = new BasicDBObject();
		example.append("total_hours", random(2000));
		example.append("all_productive_hours", random(1500));
		example.append("all_productive_percentage", random(1000));
		example.append("all_distracting_hours", random(1000));
		example.append("all_distracting_percentage", random(1000));
		example.append("neutral_hours", random(500));
		example.append("neutral_percentage", random(1000));
		return new Point(new Day(example));
	}

	private static double random(int seed) {
		return Math.random() * seed;
	}

}
