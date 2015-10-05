package org.dongzhou.interview;

public class Patient extends Human {

	public enum Temperature {

		HIGH, NORMAL, LOW;

		private static int upperBoundary;
		private static int lowerBoundary;

		public static Temperature getTemperature(int temp) {
			if (temp > upperBoundary)
				return HIGH;
			else if (temp < lowerBoundary)
				return LOW;
			else
				return NORMAL;
		}
	}

	private int temp;

	private Temperature tempCheck;
	private int lastCheckTime;

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public Temperature getTempCheck() {
		return tempCheck;
	}

	public void setTempCheck(Temperature tempCheck) {
		this.tempCheck = tempCheck;
	}

	public int getLastCheckTime() {
		return lastCheckTime;
	}

	public void setLastCheckTime(int lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}

}
