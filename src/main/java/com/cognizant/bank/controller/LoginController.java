package com.cognizant.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cognizant.bank.models.UserRequest;

@RestController
public class LoginController {
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody UserRequest userRequest) {
		
		String uri = "http://localhost:8084/signup";
		RestTemplate restTemplate = new RestTemplate();
		
		return restTemplate.postForEntity(uri, userRequest, ResponseEntity.class);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {
		
		String uri = "http://localhost:8084/login";
		RestTemplate restTemplate = new RestTemplate();
		
		return restTemplate.postForEntity(uri, userRequest, ResponseEntity.class);
	}
	
	
	
	
}
