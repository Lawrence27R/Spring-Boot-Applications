package com.aurionpro.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
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
        
        // Uncomment to send an attachment (optional)
        // if (attachment != null) {
        //     FileSystemResource fileSystem = new FileSystemResource(new File(attachment));
        //     mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);
        // }

        mailSender.send(mimeMessage);
        System.out.println("Mail sent successfully.");
    }
}

