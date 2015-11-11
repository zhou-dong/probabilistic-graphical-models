package org.dongzhou.interview;

public class Case {

	public enum Temperature {

		FEVER, NORMAL;

		private static int upperBoundary = 80;

		public static boolean isFever(int temp) {
			if (getTemperature(temp) == FEVER)
				return true;
			else
				return false;
		}

		public static Temperature getTemperature(int temp) {
			if (temp > upperBoundary)
				return FEVER;
			else
				return NORMAL;
		}
	}

	public static void main(String[] args) {
		boolean isferver = Temperature.isFever(90);
		System.out.println(isferver);
	}

	private Human reporter;
	private Patient patient;
	private String description;
	private long reportTime = System.currentTimeMillis();
	private int temperature;

	public Human getReporter() {
		return reporter;
	}

	public void setReporter(Human reporter) {
		this.reporter = reporter;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getReportTime() {
		return reportTime;
	}

	public void setReportTime(long reportTime) {
		this.reportTime = reportTime;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

}
