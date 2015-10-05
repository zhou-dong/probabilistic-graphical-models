package org.dongzhou.interview;

import java.util.List;

public class Patient implements Human {

	public enum Gender {
		MALE, FAMALE, UNKOWN;
	}

	private String id;
	private Gender gender;

	private int defaultPhoneNumberIndex;
	private List<String> phones;

	private int currentAddressIndex;
	private List<String> address;

	private int lastCheckTime;

	@Override
	public String getId() {
		return id;
	}
}
