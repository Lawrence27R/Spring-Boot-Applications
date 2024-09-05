package com.aurionpro.intro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstRestController {
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello from Spring";
	}
	
	@GetMapping("/bye")
	public String sayBye() {
		return "Bye from auto Spring";
	}
}	
