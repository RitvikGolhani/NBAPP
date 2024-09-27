package com.nobroker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import static com.nobroker.service.impl.EmailVerificationService.*;
@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    private final UserService userService;

    public EmailService(JavaMailSender javaMailSender, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }

    public String generateOtp(){
        String format = String.format("%06d", new Random().nextInt(1000000));
        return format;
    }
    public Map<String, String> sendOtpEmail(String email) {
        String otp = generateOtp();
       emailOtpMapping.put(email, otp);

       sendEmail(email, "OTP for Email Verification", "Your OTP is: "+otp);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "OTP sent successfully");
        return response;
    }
    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your.gmail@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
