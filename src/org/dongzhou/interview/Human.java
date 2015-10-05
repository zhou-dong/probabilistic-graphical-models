package org.dongzhou.interview;

import java.util.List;

public class Human {

	public static enum Gender {
		MALE, FAMALE, UNKOWN;
	}

	private String id;
	private long birthDay;
	private Gender gender;

	private String prefixName;
	private String fisrtname;
	private String middlername;
	private String lastname;

	private int defaultPhoneNumberIndex = 0;
	private List<String> phones;

	private int currentAddressIndex = 0;
	private List<String> address;

	public long getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(long birthDay) {
		this.birthDay = birthDay;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPrefixName() {
		return prefixName;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	public String getFisrtname() {
		return fisrtname;
	}

	public void setFisrtname(String fisrtname) {
		this.fisrtname = fisrtname;
	}

	public String getMiddlername() {
		return middlername;
	}

	public void setMiddlername(String middlername) {
		this.middlername = middlername;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getDefaultPhoneNumberIndex() {
		return defaultPhoneNumberIndex;
	}

	public void setDefaultPhoneNumberIndex(int defaultPhoneNumberIndex) {
		this.defaultPhoneNumberIndex = defaultPhoneNumberIndex;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	public int getCurrentAddressIndex() {
		return currentAddressIndex;
	}

	public void setCurrentAddressIndex(int currentAddressIndex) {
		this.currentAddressIndex = currentAddressIndex;
	}

	public List<String> getAddress() {
		return address;
	}

	public void setAddress(List<String> address) {
		this.address = address;
	}

}
