package org.dongzhou.interview;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class TimeUtil {

	public static SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getTimeByTimeZone(TimeZone timeZone) {
		return getTimeByTimeZone(System.currentTimeMillis(), timeZone);
	}

	public static String getTimeByTimeZone(long date, TimeZone timeZone) {
		outputFormat.setTimeZone(timeZone);
		return outputFormat.format(date);
	}

	public static void main(String[] args) {
		TimeZone timeZoneNY = TimeZone.getTimeZone("America/New_York");
		String date = getTimeByTimeZone(timeZoneNY);
		System.out.println(date);
	}
}
