package org.dongzhou.interview;

public enum Issue {

	RASH("SKIN RED NONOOOONO"), FEVER("100"), EYERED("HARD"), BODY("");

	private String description;

	private Issue(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public static void main(String args[]) {
		System.out.println(RASH.getDescription());
	}
}
