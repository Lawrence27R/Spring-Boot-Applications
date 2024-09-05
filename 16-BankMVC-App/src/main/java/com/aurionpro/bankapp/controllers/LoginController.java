package com.aurionpro.bankapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.bankapp.dto.JwtAuthResponse;
import com.aurionpro.bankapp.dto.LoginDto;
import com.aurionpro.bankapp.dto.RegistrationDto;
import com.aurionpro.bankapp.entity.User;
import com.aurionpro.bankapp.service.AuthService;
import com.aurionpro.bankapp.service.CaptchaGeneratorService;

@RestController
@RequestMapping("/bankApp")
public class LoginController {

    @Autowired
    private AuthService authService;

    @Autowired
    private CaptchaGeneratorService captchaSettings;
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegistrationDto registrationDto) {
        return ResponseEntity.ok(authService.register(registrationDto));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        // Verify captcha before attempting login
        if (!loginDto.getCaptcha().equals(captchaSettings.getHiddenCaptcha())) {
            return ResponseEntity.status(400).body("Invalid Captcha");
        }

        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }
}
