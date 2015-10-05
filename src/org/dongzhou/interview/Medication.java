package org.dongzhou.interview;

import java.util.concurrent.TimeUnit;

public class Medication {

	private long startTime;
	private long endTime;

	private int frequent;
	private TimeUnit frequentType;
	private Patient patient;
	private Human doctoc;

	private int dosage;

	public int getDosage() {
		return dosage;
	}

	public void setDosage(int dosage) {
		this.dosage = dosage;
	}

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

	public int getFrequent() {
		return frequent;
	}

	public void setFrequent(int frequent) {
		this.frequent = frequent;
	}

	public TimeUnit getFrequentType() {
		return frequentType;
	}

	public void setFrequentType(TimeUnit frequentType) {
		this.frequentType = frequentType;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Human getDoctoc() {
		return doctoc;
	}

	public void setDoctoc(Human doctoc) {
		this.doctoc = doctoc;
	}

}
