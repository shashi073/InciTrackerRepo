package com.genpact.IncidentTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.IncidentTracker.model.Country;
import com.genpact.IncidentTracker.service.CountryService;

@RestController
public class CountryController {

	@Autowired
	private CountryService cService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/addCountries")
	public void addCountries() {
		//Dont Use Data has been already updated
	     //cService.addCountries();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getCountries")
	public List<Country> getCountries() {
	     return cService.getCountries();
	}
}
