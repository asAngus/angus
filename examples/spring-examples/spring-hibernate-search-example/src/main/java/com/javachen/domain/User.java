package com.javachen.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;

public class User {

	private String name;
	private String firstName;
	private Calendar birthDay;
	private boolean isMale;
	
	public User(String name, String firstName, Calendar birthDay, boolean isMale) {
		setName(name);
		setFirstName(firstName);
		setBirthDay(birthDay);
		setMale(isMale);
	}
	
	public User() { }
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public Calendar getBirthDay() {
		return birthDay;
	}
	
	public void setBirthDay(Calendar birthDay) {
		this.birthDay = birthDay;
	}
	
	public boolean isMale() {
		return isMale;
	}
	
	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}
	
	@Override
	public String toString() {
		return StringUtils.join(getFirstName(), " ", getName());
	}
}
