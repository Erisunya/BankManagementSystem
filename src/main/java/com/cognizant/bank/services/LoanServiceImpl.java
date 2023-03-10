package com.cognizant.bank.services;

import java.util.Optional;

import com.cognizant.bank.entities.Loan;
import com.cognizant.bank.entities.User;
import com.cognizant.bank.exceptions.LoanNotFoundException;
import com.cognizant.bank.exceptions.UsernameNotFoundException;
import com.cognizant.bank.models.LoanRequest;
import com.cognizant.bank.repositories.LoanRepository;
import com.cognizant.bank.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService{
	
	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Loan viewLoan(String username) throws UsernameNotFoundException, LoanNotFoundException {
		
		Optional<User> userOp = userRepository.findByUsername(username);
		
		if(userOp.isEmpty()) {
			throw new UsernameNotFoundException();
		}
		
		Optional<Loan> loanOp = loanRepository.findByUser(userOp.get());
		
		if(loanOp.isEmpty()) {
			throw new LoanNotFoundException();
		}
		
		return loanOp.get();	
	}

	@Override
	public void saveLoan(LoanRequest loanRequest) throws UsernameNotFoundException {

		Optional<User> userOp = userRepository.findByUsername(loanRequest.getUsername());
		
		if(userOp.isEmpty()) {
			throw new UsernameNotFoundException();
		}
		
		User user = userOp.get();
		Loan loan = new Loan();
		loan.setUser(user);
		loan.setLoanType(loanRequest.getLoanType());
		loan.setLoanAmount(loanRequest.getLoanAmount());
		loan.setLoanDate(loanRequest.getLoanDate());
		loan.setLoanDuration(loanRequest.getLoanDuration());
		loan.setInterestRate(loanRequest.getInterestRate());
		
		loanRepository.saveAndFlush(loan);	
	}
	
}
