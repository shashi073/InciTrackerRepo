package com.genpact.IncidentTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.IncidentTracker.model.User;
import com.genpact.IncidentTracker.service.TwilioClient;

@RestController
public class OtpController {

	@Autowired
	private TwilioClient tClient;
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/sendOtp") 
	public void sendOtp(@RequestBody User user) { 
	   //Dont Use Data has been already updated
	   tClient.sendOtp(user); 
	}
	@RequestMapping(method = RequestMethod.POST, value = "/validateOtp") 
	public String validateOtp(@RequestBody User user) { 
	   //Dont Use Data has been already updated
	   return tClient.validateOtp(user); 
	}
	
	
	
}
