package org.dongzhou.interview;

import java.util.concurrent.TimeUnit;

public class Medication {

	private long startTime;
	private long endTime;

	private String frequent;
	private TimeUnit frequentType;
	private String patientId;
	private String doctocId;

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
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
