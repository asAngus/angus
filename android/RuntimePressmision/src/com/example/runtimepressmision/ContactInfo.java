package com.example.runtimepressmision;

public class ContactInfo {

	
	private String name;
	private String phone;
	public ContactInfo(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}
	public ContactInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
