package com.cognizant.bank.controller;

import com.cognizant.bank.entities.Loan;
import com.cognizant.bank.entities.User;
import com.cognizant.bank.exceptions.LoanNotFoundException;
import com.cognizant.bank.exceptions.UsernameNotFoundException;
import com.cognizant.bank.models.UserRequest;
import com.cognizant.bank.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UpdateController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/update")
	public ResponseEntity<?> updateUser(UserRequest userRequest) throws UsernameNotFoundException {
		
		userService.updateUser(userRequest);
		
		return new ResponseEntity<String>("User successfully updated.", HttpStatus.OK);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<?> showUser(@PathVariable("username") String username) throws UsernameNotFoundException {

		return new ResponseEntity<User>(userService.viewUser(username), HttpStatus.OK);
	}
}
