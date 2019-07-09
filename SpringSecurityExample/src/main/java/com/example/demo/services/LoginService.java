package com.example.demo.services;

import java.util.Date;

import com.example.demo.model.User;
import com.example.demo.rest.model.CheckOtpPOJO;
import com.example.demo.rest.model.LoginPOJO;

public interface LoginService {
	public String passwordEncryption(String password);
	public User registration(User user);
	public User login(LoginPOJO loginValue);
	public User findByEmail(String email);
	public User updateUser(User user);
	public User forgotPassword(String emailId);
	public boolean resetPassword(CheckOtpPOJO checkOtp);
	public boolean checkOtp(String uuid, String otp);
}
