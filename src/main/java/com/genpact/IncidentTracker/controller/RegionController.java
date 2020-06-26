
package com.genpact.IncidentTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.IncidentTracker.model.Region;
import com.genpact.IncidentTracker.service.RegionService;

@RestController
public class RegionController {

	@Autowired
	private RegionService rService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/addRegions")
	public void addRegions() {
		//Dont Use Data has been already updated
	    rService.addRegions();;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getRegions")
	public List<Region> getRegions() {
	     return rService.getRegions();
	}
}