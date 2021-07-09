package com.monitor.bankendmonitoreoLinks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class SendMailService {
	
	@Autowired JavaMailSender javaMailSender;
	
	public void sendMail(String from,String to,String subject,String body) {
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(body);
		
		javaMailSender.send(mail);
	}

}
