package com.nobroker.controller;

import com.nobroker.entity.User;
import com.nobroker.service.impl.EmailService;
import com.nobroker.service.impl.EmailVerificationService;
import com.nobroker.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private EmailService  emailService;
    private EmailVerificationService emailVerificationService;

    public RegistrationController(EmailVerificationService emailVerificationService) {
        this.emailVerificationService = emailVerificationService;
    }

    @PostMapping("/register")
    public Map<String,String> registerUser(@RequestBody User user){
        User registeredUser = userService.registerUser(user);
        return emailService.sendOtpEmail(user.getEmail());

    }

    //http://localhost:8080/api/verify-otp?email=&otp=

    @PostMapping("/verify-otp")
    public Map<String, String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        return emailVerificationService.verifyOtp(email, otp);
    }
}
