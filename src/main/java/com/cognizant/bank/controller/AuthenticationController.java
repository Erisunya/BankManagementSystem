package com.cognizant.bank.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import com.cognizant.bank.models.UserRequest;

@RestController
@RequestMapping("/userauth")
public class AuthenticationController {
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody UserRequest userRequest) {
		
		String uri = "http://localhost:8084/signup";
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<UserRequest> request = new HttpEntity<UserRequest>(userRequest);
		
		return restTemplate.postForEntity(uri, request, String.class);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {
		
		String uri = "http://localhost:8084/auth/user/login";
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<UserRequest> request = new HttpEntity<UserRequest>(userRequest);
		
		return restTemplate.postForEntity(uri, request, HashMap.class);
	}
}
