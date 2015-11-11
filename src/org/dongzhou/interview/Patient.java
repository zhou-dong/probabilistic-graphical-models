package org.dongzhou.interview;

import java.util.List;

public class Patient extends Human {

	private int temp;

	private List<Case> cases;

	private int lastCheckTime;

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getLastCheckTime() {
		return lastCheckTime;
	}

	public void setLastCheckTime(int lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}

	public List<Case> getCases() {
		return cases;
	}

	public void setCases(List<Case> cases) {
		this.cases = cases;
	}

}
