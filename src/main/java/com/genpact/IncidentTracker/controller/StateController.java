package com.genpact.IncidentTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.IncidentTracker.model.State;
import com.genpact.IncidentTracker.service.StateService;

@RestController
public class StateController {

	@Autowired
	private StateService sService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/addStates")
	public void addStates() {
		//Dont Use Data has been already updated
	    // sService.addStates();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getStates")
	public List<State> getStates() {
	     return sService.getStates();
	}
}