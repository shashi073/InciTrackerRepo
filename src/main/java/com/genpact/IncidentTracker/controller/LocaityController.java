
package com.genpact.IncidentTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.IncidentTracker.model.Locality;
import com.genpact.IncidentTracker.model.State;
import com.genpact.IncidentTracker.service.LocalityService;

@RestController
public class LocaityController {

	@Autowired
	private LocalityService lService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/addLocalities")
	public void addLocalities() {
		//Dont Use Data has been already updated
	     //lService.addLocalities();;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getLocalities")
	public List<Locality> getLocalities() {
	     return lService.getLocalities();
	}
}