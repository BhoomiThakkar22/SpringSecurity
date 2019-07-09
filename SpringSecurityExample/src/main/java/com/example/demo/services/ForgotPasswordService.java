package com.example.demo.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class ForgotPasswordService {
	
	@Autowired
	LoginService loginService;
	
	public String sendEmail(User user) {
        
		int randNumber=(int)(Math.random()*1000000);
		String newPassword = String.format("%06d", randNumber);
		String userName = "sahil.patel@internal.mail";
        String password = "tatva123";
        String emailSubject = "Regarding Forgot Password";
        String emailMessage = "Otp is : "+newPassword;
        
        try {
        	//set properties
			Properties props = System.getProperties();
			props.put("mail.smtp.host", "192.168.0.7");
			props.put("mail.smtp.port", "25");
			props.put("mail.smtp.auth", "true");
			
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {    
			    //override the getPasswordAuthentication method 
				protected PasswordAuthentication getPasswordAuthentication() {                            
				    return new PasswordAuthentication(userName,password); 
				}
			});
			
            // Create email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            message.setRecipients(Message.RecipientType.TO, user.getEmailId());
            message.setSubject(emailSubject);
            message.setContent(emailMessage, "text/html");
            Transport.send(message);
            
            return newPassword;
            
        } catch (MessagingException e) {
            return "Error in sending Email : " + e;
        }
    }
}
