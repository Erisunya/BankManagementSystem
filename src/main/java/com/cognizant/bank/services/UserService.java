package com.cognizant.bank.services;

import com.cognizant.bank.entities.Loan;
import com.cognizant.bank.entities.User;
import com.cognizant.bank.exceptions.LoanNotFoundException;
import com.cognizant.bank.exceptions.UsernameNotFoundException;
import com.cognizant.bank.models.UserRequest;

public interface UserService {

	public void updateUser(UserRequest userRequest) throws UsernameNotFoundException;
	
	public User viewUser(String username) throws UsernameNotFoundException;
}
