package com.cognizant.bank.controller;

import com.cognizant.bank.entities.Loan;
import com.cognizant.bank.exceptions.LoanNotFoundException;
import com.cognizant.bank.exceptions.UsernameNotFoundException;
import com.cognizant.bank.models.LoanRequest;
import com.cognizant.bank.services.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/loan")
public class LoanController {
	
	@Autowired
	private LoanService loanService;
	
	@GetMapping("/{username}")
	public ResponseEntity<?> showLoan(@PathVariable("username") String username) throws UsernameNotFoundException, LoanNotFoundException {
		
		return new ResponseEntity<Loan>(loanService.viewLoan(username), HttpStatus.OK);
	}
	
	@PostMapping("/apply")
	public ResponseEntity<?> applyLoan(LoanRequest loanRequest) throws UsernameNotFoundException {
		
		loanService.saveLoan(loanRequest);
		
		return new ResponseEntity<String>("Loan successfully applied.", HttpStatus.OK);
	}	
}
