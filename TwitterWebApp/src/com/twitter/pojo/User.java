package com.twitter.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class User {
	private int userId;
	private String emailAddress;
	private String password;
	private String firstName;
    private String lastName;
    private String gender;
    private Date dob;
    private int phoneNo;
    private int alternateNo;
    private Date createdTime;
    private String status;
    
	public int getUserId() {
		return userId;
	}
	public void setUser_id(int userid) {
		this.userId = userid;
	}
	public String getEmail_Id() {
		return emailAddress;
	}
	public void setEmail_Id(String email_Id) {
		this.emailAddress = email_Id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirst_Name() {
		return firstName;
	}
	public void setFirst_Name(String firstName) {
		this.firstName = firstName;
	}
	public String getLast_Name() {
		return lastName;
	}
	public void setLast_Name(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public int getPhone_No() {
		return phoneNo;
	}
	public void setPhone_No(int phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getAlternate_No() {
		return alternateNo;
	}
	public void setAlternate_No(int alternateNo) {
		this.alternateNo = alternateNo;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreated_Time(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
    
}
