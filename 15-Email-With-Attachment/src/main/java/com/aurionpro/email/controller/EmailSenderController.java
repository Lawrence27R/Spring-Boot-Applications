package com.aurionpro.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.email.send.EmailSenderService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/emailApp")
public class EmailSenderController {
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@PutMapping("/send")
	public String sendEmail(
	        @RequestParam String toEmail, @RequestParam String subject, @RequestBody String body, 
	        @RequestParam String attachment) throws MessagingException {
		
		emailSenderService.sendEmail(toEmail, body, subject, attachment);
		
		return "Mail sent successfully.";
	}
}

