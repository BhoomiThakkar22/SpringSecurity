package com.example.demo.services;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.rest.model.CheckOtpPOJO;
import com.example.demo.rest.model.LoginPOJO;

@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	ForgotPasswordService forgotPasswordService;

	@Override
	public String passwordEncryption(String password) {
		return DigestUtils.sha256Hex(password);
	}

	@Override
	public User registration(User user) {
		User user1 = userRepo.findByEmail(user.getEmailId());
		if (user1 == null) {
			UUID uuid = UUID.randomUUID();
			user.setId(uuid.toString());
			user.setRole("User");
			String password = passwordEncryption(user.getPassword());
			user.setPassword(password);
			userRepo.save(user);
			return user;
		} else {
			return null;
		}
	}

	@Override
	public User login(LoginPOJO loginValue) {
		String email = loginValue.getEmailId();
		String password = passwordEncryption(loginValue.getPassword());
		User user = userRepo.login(email, password);
		return user;
	}

	@Override
	public User findByEmail(String emailId) {
		User user = userRepo.findByEmail(emailId);
		return user;
	}

	@Override
	public User updateUser(User user) {
		userRepo.save(user);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("No user present with this emailId : " + email);
		}
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
		return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(),
				grantedAuthorities);
	}

	@Override
	public User forgotPassword(String emailId) {
		User user = findByEmail(emailId);
		if (user == null) {
			return null;
		} else {
			String newPassword = forgotPasswordService.sendEmail(user);
			user.setOtp(newPassword);
			user.setOtpTime(new Date());
			return updateUser(user);
		}
	}

	@Override
	public boolean resetPassword(CheckOtpPOJO checkOtp) {
		String uuid = checkOtp.getUuid();
		String newPassword = checkOtp.getNewPassword();
		if (newPassword != null && uuid != null && !uuid.isEmpty() && !newPassword.isEmpty()) {
			User user = findByUuid(uuid);
			Date d = new Date();
			long diff = d.getTime() - user.getOtpTime().getTime();
			diff = diff / (60 * 1000) % 60;
			if (diff < 10) {
				user.setPassword(passwordEncryption(newPassword));
				checkOtp.setNewPassword(passwordEncryption(newPassword));
				user.setOtp(null);
				user.setOtpTime(null);
				updateUser(user);
				return true;
			} else {
				user.setOtp(null);
				user.setOtpTime(null);
				updateUser(user);
				return false;
			}
		} else {
			return false;
		}

	}

	private User findByUuid(String uuid) {
		return userRepo.findById(uuid);
	}

	@Override
	public boolean checkOtp(String uuid, String otp) {
		System.out.println("id : " + uuid);
		if (otp != null && uuid != null && !uuid.isEmpty() && !otp.isEmpty()) {
			User user = findByUuid(uuid);
			Date d = new Date();
			System.out.println(user.getId());
			long diff = d.getTime() - user.getOtpTime().getTime();

			diff = diff / (60 * 1000) % 60;
			if (user.getOtp().equals(otp) && diff < 10) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
