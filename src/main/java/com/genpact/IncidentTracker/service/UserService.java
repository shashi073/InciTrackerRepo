package com.genpact.IncidentTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.model.Country;
import com.genpact.IncidentTracker.model.User;
import com.genpact.IncidentTracker.repository.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo uRepo;

	public boolean addUser(User user) {
		
		return uRepo.addUser(user);
				
	}

	public User validateUserLogin(User user) {
			
			return uRepo.validateUserLogin(user);
					
	}
	public boolean checkEmailIdAvailability(User user) {
		
		return uRepo.checkEmailIdAvailability(user);
				
	}

}
