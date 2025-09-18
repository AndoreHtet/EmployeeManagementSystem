package com.htet.employeemanagementapi.services.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendPasswordResetEmail(String email, String optCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hhtetaung578@gmail.com");
        message.setTo(email);
        message.setSubject("Password Reset");
        message.setText("Hello,\n\n"
                + "We received a request to reset your password. Your One-Time Password (OPT) code is:\n\n"
                + optCode + "\n\n"
                + "This code is valid for 15 minutes. If you did not request this, please ignore this email.\n\n"
                + "Thank you.");
        mailSender.send(message);
    }
}
