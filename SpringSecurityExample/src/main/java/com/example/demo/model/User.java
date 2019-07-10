package com.example.demo.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name="uuid",unique=true,nullable=false)
	private String id;
	
	@Column(name="firstname",nullable=false)
	private String firstName;
	
	@Column(name="lastname",nullable=false)
	private String lastName;
	
	@Column(name="emailId",nullable=false)
	private String emailId;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(name="role",nullable=false)
	private String role;
	
	@Column(name="otp",nullable=true)
	private String otp;
	
	@Column(name="createdTime",nullable=true)
	private Date otpTime;
	

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Date getOtpTime() {
		return otpTime;
	}

	public void setOtpTime(Date otpTime) {
		this.otpTime = otpTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
