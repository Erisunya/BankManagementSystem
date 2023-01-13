package com.cognizant.bank.services;

import com.cognizant.bank.entities.Loan;
import com.cognizant.bank.exceptions.LoanNotFoundException;
import com.cognizant.bank.exceptions.UsernameNotFoundException;
import com.cognizant.bank.models.LoanRequest;

public interface LoanService {

	public Loan viewLoan(String username) throws UsernameNotFoundException, LoanNotFoundException;
	
	public void saveLoan(LoanRequest loanRequest) throws UsernameNotFoundException;
	
}
