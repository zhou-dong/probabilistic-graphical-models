package org.dongzhou.rescueTime.kmeans;

import org.dongzhou.rescueTime.Day;

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

	public Point() {
	}

	public Point(Day day) {
		this.day = day;
		this.date = day.getDate();
		this.totalHours = day.getTotalHours();
		this.productiveHours = day.getProductiveHours();
		this.productivePercentage = day.getProductivePercent();
		this.distractingHours = day.getDistractingHours();
		this.distractingPercentage = day.getDistractingPercent();
		this.neutralHours = day.getNeutralHours();
		this.neutralPercentage = day.getNeutralPercent();
	}

	public Point add(Point other) {
		this.totalHours = other.totalHours;
		this.productiveHours = other.productiveHours;
		this.productivePercentage = other.productivePercentage;
		this.distractingHours = other.distractingHours;
		this.distractingPercentage = other.distractingPercentage;
		this.neutralHours = other.neutralHours;
		this.neutralPercentage = other.distractingPercentage;
		return this;
	}

	public Point div(long value) {
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
		return day.toString();
	}

}
