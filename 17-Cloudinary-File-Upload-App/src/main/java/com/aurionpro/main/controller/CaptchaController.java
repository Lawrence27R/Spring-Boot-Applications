package com.aurionpro.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.main.captcha.CaptchaGenerator;
import com.aurionpro.main.captcha.CaptchaSettings;

import cn.apiclub.captcha.Captcha;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    private CaptchaSettings captchaSettings;

    @GetMapping("/generate")
    public CaptchaSettings generateCaptcha() {
        captchaSettings = new CaptchaSettings();
        Captcha captcha = CaptchaGenerator.generateCaptcha(260, 80);
        captchaSettings.setHiddenCaptcha(captcha.getAnswer());
        captchaSettings.setCaptcha("");
//        captchaSettings.setRealCaptcha(CaptchaGenerator.encodeCaptchatoBinary(captcha));
        return captchaSettings;
    }

    @PostMapping("/verify")
    public String verifyCaptcha(@RequestBody CaptchaSettings inputCaptcha) {
        if (!inputCaptcha.getCaptcha().equals(captchaSettings.getHiddenCaptcha())) {
        	return "Invalid Captcha";
        }
        return "Captcha verified successfully";
        
    }
}
