package org.dongzhou.interview;

import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TestInterview {

	public static void main(String[] args) {

		Case oCase = new Case();
		oCase.setDescription("Feeling bad, don't wanna to anything");
		oCase.setTemperature(90);

		Patient patient = new Patient();
		patient.addAddress("123 south brundidge st");
		long oneYear = 365 * 24 * 60 * 60 * 1000;
		patient.setBirthDay(System.currentTimeMillis() - oneYear);

		System.out.println(TimeUtil.getTimeByTimeZone(patient.getBirthDay(),
				TimeZone.getTimeZone("America/New_York")));

		Medication medication = new Medication();
		medication.setStartTime(System.currentTimeMillis());
		medication.setEndTime(System.currentTimeMillis() + 24 * 3600 * 1000);
		medication.setFrequent(2);
		medication.setFrequentType(TimeUnit.DAYS);
		medication.setDosage(100);

	}
}
