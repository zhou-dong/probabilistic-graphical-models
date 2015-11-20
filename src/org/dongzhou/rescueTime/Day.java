package org.dongzhou.rescueTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.mongodb.DBObject;

public class Day {

	private static Logger logger = Logger.getLogger(Day.class.getName());

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
	private static SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE", Locale.US);

	private DBObject day;

	@Override
	public String toString() {
		return day.toString();
	}

	public Day(DBObject day) {
		this.day = day;
	}

	public DBObject getDay() {
		return day;
	}

	public double getProductivePercent() {
		return (double) day.get("all_productive_percentage");
	}

	public double getDistractingPercent() {
		return (double) day.get("all_distracting_percentage");
	}

	public double getNeutralPercent() {
		return (double) day.get("neutral_percentage");
	}

	public double getProductiveHours() {
		return (double) day.get("all_productive_hours");
	}

	public double getDistractingHours() {
		return (double) day.get("all_distracting_hours");
	}

	public double getNeutralHours() {
		return (double) day.get("neutral_hours");
	}

	public double getTotalHours() {
		return (double) day.get("total_hours");
	}

	public String getDate() {
		return (String) day.get("date");
	}

	public String getWeek() {
		String date = getDate();
		try {
			return weekFormat.format(dateFormat.parse(date));
		} catch (ParseException e) {
			logger.error(e);
			return "";
		}
	}

}
