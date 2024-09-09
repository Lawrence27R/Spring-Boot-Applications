package com.aurionpro.bankapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CaptchaSettings {
    private String captcha;
    private String hiddenCaptcha;
    private String captchaId; 
}
