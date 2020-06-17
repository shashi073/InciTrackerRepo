package com.genpact.IncidentTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.IncidentTracker.model.Incident;
import com.genpact.IncidentTracker.service.IncidentService;

@RestController
public class IncidentController {

	@Autowired
	private IncidentService iService;
	
	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/addLocalities") public
	 * void addLocalities() { //Dont Use Data has been already updated
	 * //lService.addLocalities();; }
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getIncidents")
	public List<Incident> getIncidents() {
	     return iService.getIncidents();
	}
}