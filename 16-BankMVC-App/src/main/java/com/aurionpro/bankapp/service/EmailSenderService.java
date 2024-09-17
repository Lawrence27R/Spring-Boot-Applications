package com.aurionpro.bankapp.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService {

    private static final Logger logger = LoggerFactory.getLogger(EmailSenderService.class);

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
            mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);
            logger.info("Attachment {} added to the email", fileSystem.getFilename());
        }

        mailSender.send(mimeMessage);
        logger.info("Mail sent successfully to {}", toEmail);
    }

    public void sendEmailWithAttachment(String to, String subject, String body, String attachmentPath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);

        File file = new File(attachmentPath);
        if (file.exists()) {
            helper.addAttachment(file.getName(), file);
            logger.info("Attachment {} added to the email", file.getName());
        } else {
            logger.error("Attachment file not found: {}", attachmentPath);
            throw new MessagingException("Attachment file not found: " + attachmentPath);
        }

        mailSender.send(message);
        logger.info("Mail sent successfully with attachment to {}", to);
    }
}
