package com.rsi.bean;

import java.sql.Date;

public class user {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private Date dob;
	private String gender;
	private String password;
	private int isactivate;
	private Date dateoflogin;
	private Date activedate;
	public int getIsactivate() {
		return isactivate;
	}
	public void setIsactivate(int isactivate) {
		this.isactivate = isactivate;
	}
	public Date getDateoflogin() {
		return dateoflogin;
	}
	public void setDateoflogin(Date dateoflogin) {
		this.dateoflogin = dateoflogin;
	}
	public user(int id, String firstName, String lastName, String email, Date dob, String gender, String password,
			int isactivate, Date dateoflogin, Date activedate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dob = dob;
		this.gender = gender;
		this.password = password;
		this.isactivate = isactivate;
		this.dateoflogin = dateoflogin;
		this.activedate = activedate;
	}
	public Date getActivedate() {
		return activedate;
	}
	public void setActivedate(Date activedate) {
		this.activedate = activedate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
}
