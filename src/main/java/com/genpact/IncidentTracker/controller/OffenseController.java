package com.genpact.IncidentTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.IncidentTracker.model.Offense;
import com.genpact.IncidentTracker.service.OffenseService;

@RestController
public class OffenseController {

	@Autowired
	private OffenseService oService;
	
	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/addOffenses")
	 *  public void addOffenses() 
	 *  { 
	 *  //Dont Use Data has been already updated //
	 *  oService.addOffenses(); 
	 *  }
	 */
	
	@RequestMapping(method = RequestMethod.GET, value = "/getOffenses")
	public List<Offense> getOffenses() {
	     return oService.getOffenses();
	}
}
