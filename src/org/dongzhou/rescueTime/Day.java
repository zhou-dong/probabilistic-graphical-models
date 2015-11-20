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

	public Day(DBObject day) {
		this.day = day;
	}

	public DBObject getDay() {
		return day;
	}

	public Double getProductiveHours() {
		return (Double) day.get("all_productive_hours");
	}

	public Double getDistractingHours() {
		return (Double) day.get("all_distracting_hours");
	}

	public Double getNeutralHours() {
		return (Double) day.get("neutral_hours");
	}

	public Double getTotalHours() {
		return (Double) day.get("total_hours");
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
