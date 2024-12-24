package com.example.garbagecollection.controller;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailController {

    private final JavaMailSender mailSender;

    public EmailController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String sendUserEmail(String userEmail, String userPassword) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

            helper.setFrom("sajidbrong@gmail.com");  // Use your email
            helper.setTo(userEmail);  // User email
            helper.setSubject("Welcome to our platform!");

            // Create email body with user email and password
            String body = "<p>Dear user,</p><p>Your account has been successfully created. Below are your credentials:</p>"
                    + "<p>Email: " + userEmail + "</p>"
                    + "<p>Password: " + userPassword + "</p>"
                    + "<p>Please keep your password secure.</p>";

            helper.setText(body, true);  // true means HTML content

            mailSender.send(message);  // Send the email

            return "Email sent successfully!";
        } catch (Exception e) {
            return "Failed to send email: " + e.getMessage();
        }
    }
}
