package org.dongzhou.interview;

import java.util.concurrent.TimeUnit;

public class Medication {

	private double startTime;
	private double endTime;

	private String frequent;
	private TimeUnit frequentType;
	private String patientId;
	private String doctocId;

	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	public double getEndTime() {
		return endTime;
	}

	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}

	public String getFrequent() {
		return frequent;
	}

	public void setFrequent(String frequent) {
		this.frequent = frequent;
	}

	public TimeUnit getFrequentType() {
		return frequentType;
	}

	public void setFrequentType(TimeUnit frequentType) {
		this.frequentType = frequentType;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDoctocId() {
		return doctocId;
	}

	public void setDoctocId(String doctocId) {
		this.doctocId = doctocId;
	}

}
