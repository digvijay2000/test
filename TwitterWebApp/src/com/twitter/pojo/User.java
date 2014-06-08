package com.twitter.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class User {
	private int user_id;
	private String email_Id;
	private String password;
	private String first_Name;
    private String last_Name;
    private String gender;
    private Date dob;
    private int phone_No;
    private int alternate_No;
    private Timestamp created_Time;
    private String status;
    
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getEmail_Id() {
		return email_Id;
	}
	public void setEmail_Id(String email_Id) {
		this.email_Id = email_Id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirst_Name() {
		return first_Name;
	}
	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}
	public String getLast_Name() {
		return last_Name;
	}
	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
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
		return phone_No;
	}
	public void setPhone_No(int phone_No) {
		this.phone_No = phone_No;
	}
	public int getAlternate_No() {
		return alternate_No;
	}
	public void setAlternate_No(int alternate_No) {
		this.alternate_No = alternate_No;
	}
	public Timestamp getCreated_Time() {
		return created_Time;
	}
	public void setCreated_Time(Timestamp created_Time) {
		this.created_Time = created_Time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
    
}
