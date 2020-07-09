package com.genpact.IncidentTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.IncidentTracker.model.Country;
import com.genpact.IncidentTracker.service.CountryService;
import com.genpact.IncidentTracker.service.TempService;

@RestController
public class TempDataController {

	@Autowired
	private TempService tService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/addTempIncidents") 
	public void addTempIncidents() { 
	   //Dont Use Data has been already updated
	   tService.prepareAndInsertData();; 
	}
	
	
}
