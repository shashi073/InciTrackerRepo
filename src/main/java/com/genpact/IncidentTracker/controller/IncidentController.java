package com.genpact.IncidentTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.IncidentTracker.model.AddIncidentRequest;
import com.genpact.IncidentTracker.model.Comment;
import com.genpact.IncidentTracker.model.FormattedIncidents;
import com.genpact.IncidentTracker.model.HeatMapList;
import com.genpact.IncidentTracker.model.Incident;
import com.genpact.IncidentTracker.model.LiveIncident;
import com.genpact.IncidentTracker.model.ModifiedTicker;
import com.genpact.IncidentTracker.service.CommentService;
import com.genpact.IncidentTracker.service.IncidentService;

@RestController
public class IncidentController {

	@Autowired
	private IncidentService iService;
	
	  @RequestMapping(method = RequestMethod.GET, value = "/addIncidents")
	  public  void addIncidents() { 
		  //Dont Use Data has been already updated
		  //iService.addIncidents();;
	  }
	  @RequestMapping(method = RequestMethod.POST, value = "/addIncident")
	  public  void addIncident(@RequestBody AddIncidentRequest inc) { 
		  //Dont Use Data has been already updated
		  iService.addIncident(inc);
	  }
	 
	@RequestMapping(method = RequestMethod.GET, value = "/getIncidents")
	public List<Incident> getIncidents() {
	     return iService.getIncidents();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getIncidentsListFormatted")
	public List<FormattedIncidents> getIncidentsListFormatted() {
	     return iService.getIncidentsListFormatted();
	}
	@RequestMapping(method = RequestMethod.GET, value = "/getIncidentsListByStateFormatted")
	public List<FormattedIncidents> getIncidentsListByStateFormatted(@RequestParam String stateName) {
	     return iService.getIncidentsListByStateFormatted(stateName);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/getIncidentsListByLatLngFormatted")
	public List<FormattedIncidents> getIncidentsListByLatLngFormatted(@RequestParam double lat, @RequestParam double lng) {
	     return iService.getIncidentsListByLatLngFormatted(lat,lng);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getIncidentsHeatMap")
	public List<HeatMapList> getIncidentsHeatMap() {
	     return iService.getIncidentsHeatMap();
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