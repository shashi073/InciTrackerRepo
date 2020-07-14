package com.genpact.IncidentTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.IncidentTracker.model.Country;
import com.genpact.IncidentTracker.model.User;
import com.genpact.IncidentTracker.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService uService;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/registerUser") 
	public boolean registerUser(@RequestBody User user) {
		return uService.addUser(user);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/validateUserLogin") 
	public User validateUserLogin(@RequestBody User user) {
		return uService.validateUserLogin(user);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/checkEmailIdAvailability") 
	public boolean checkEmailIdAvailability(@RequestBody User user) {
		return uService.checkEmailIdAvailability(user);
	}
	
}
