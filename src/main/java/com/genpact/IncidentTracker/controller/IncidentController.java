package com.genpact.IncidentTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.genpact.IncidentTracker.model.AddIncidentRequest;
import com.genpact.IncidentTracker.model.LiveIncident;
import com.genpact.IncidentTracker.model.ModifiedTicker;
import com.genpact.IncidentTracker.service.IncidentService;

@RestController
public class IncidentController {

	@Autowired
	private IncidentService iService;
	
	/*
	 * @RequestMapping(method = RequestMethod.GET, value = "/addAgregatedIncidents")
	 *  public void addAgregatedIncidents() { //Dont Use Data has been already updated
	 * //iService.addIncidents();
	 *  }
	 */

	
	  @RequestMapping(method = RequestMethod.POST, value = "/addIncident")
	  public  String addIncident(@RequestBody AddIncidentRequest inc) { 
		 String id = iService.addIncident(inc);
		 return id;
	  }
	 
	@RequestMapping(method = RequestMethod.GET, value = "/getCount")
	public int getCount(@RequestParam double lat, @RequestParam double lng) { 
		  //Dont Use Data has been already updated
		  return iService.getCount(lat,lng);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/getLiveIncidentsListByLatLngFormatted")
	public List<LiveIncident> getLiveIncidentsListByLatLngFormatted(double lat, double lng){
		return iService.getLiveIncidentsListByLatLngFormatted(lat, lng);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/getTickerListByLatLngFormatted")
	public List<ModifiedTicker> getTickerListByLatLngFormatted(double lat, double lng){
		return iService.getTickerListByLatLngFormatted(lat, lng);
	}
	
}