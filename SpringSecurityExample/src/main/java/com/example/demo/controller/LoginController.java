package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.rest.model.CheckOtpPOJO;
import com.example.demo.rest.model.LoginPOJO;
import com.example.demo.services.LoginServiceImpl;

@RestController
@CrossOrigin()
public class LoginController {

	@Autowired
	LoginServiceImpl loginService;

	/*
	 * @RequestMapping("/") public String hello(){ return "Hello world"; }
	 */

	@RequestMapping("/user/login")
	@ResponseBody
	public User Login(@RequestBody LoginPOJO loginValue) {
		return loginService.login(loginValue);
	}

	@RequestMapping("/user/registration")
	@ResponseBody
	public User selfRegister(@RequestBody User user) {
		return loginService.registration(user);
	}

	@RequestMapping("/user/forgotPassword")
	@ResponseBody
	public User findByEmailId(@RequestBody String emailId) {
		return loginService.forgotPassword(emailId);
	}

	@RequestMapping("/user/checkOtp")
	@ResponseBody
	public CheckOtpPOJO checkOtp(@RequestBody CheckOtpPOJO checkOtp) {
		if (loginService.checkOtp(checkOtp.getUuid(), checkOtp.getOtp())) {
			return checkOtp;
		} else {
			return null;
		}
	}

	@RequestMapping("/user/resetPassword")
	@ResponseBody
	public CheckOtpPOJO resetPassword(@RequestBody CheckOtpPOJO checkOtp) {
		if (loginService.resetPassword(checkOtp)) {
			return checkOtp;
		} else {
			return null;
		}
	}
}
