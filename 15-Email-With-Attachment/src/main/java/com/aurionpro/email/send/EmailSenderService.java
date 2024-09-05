package com.aurionpro.email.send;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService {
    
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String body, String subject, String attachment) throws MessagingException {
        
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        
        mimeMessageHelper.setFrom("compengineering2024@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body);
        
        if (attachment != null) {
            FileSystemResource fileSystem = new FileSystemResource(new File(attachment));
            if (!fileSystem.exists()) {
            	throw new MessagingException("Attachment file not found: " + attachment);
            }
            mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);
        }

        mailSender.send(mimeMessage);
        System.out.println("Mail sent successfully.");
    }
}
