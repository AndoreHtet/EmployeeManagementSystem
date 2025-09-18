package com.htet.employeemanagementapi.services.mail;

public interface EmailService {
    void sendPasswordResetEmail(String email, String optCode);
}
