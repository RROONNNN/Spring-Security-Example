package com.example.SpringSecuritydemo;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
@GetMapping("/")
	public String publicPage() {
		return "Helllo Devokx";
	}
@GetMapping("/private")
public String privatePage(Authentication authentication) {
	return "this is private "+authentication.getName();
}
}
