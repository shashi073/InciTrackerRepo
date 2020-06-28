package com.genpact.IncidentTracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.Util.IncedentReaderImpl;
import com.genpact.IncidentTracker.model.FormattedIncidents;
import com.genpact.IncidentTracker.model.Incident;
import com.genpact.IncidentTracker.model.Locality;
import com.genpact.IncidentTracker.model.Offense;
import com.genpact.IncidentTracker.model.OffenseData;
import com.genpact.IncidentTracker.repository.IncidentRepo;
import com.genpact.IncidentTracker.repository.LocalityRepo;
import com.genpact.IncidentTracker.repository.OffenseRepo;
import com.genpact.IncidentTracker.repository.StateRepo;

@Service
public class IncidentService {

	@Autowired
	private OffenseRepo oRepo;
	@Autowired
	private IncedentReaderImpl iReader;
	@Autowired
	private LocalityRepo lRepo;
	@Autowired
	private IncidentRepo iRepo;
	@Autowired
	private StateRepo sRepo;

	
	 public void addIncidents() {
		 List <Offense> offenses =oRepo.getOffenses();
		 List<Locality> localities= lRepo.getTempLocality();
		 
		 for(Locality l : localities) {
			 List <Incident> incidents = iReader.readData(l.getOri(), l.getLocalityId(), offenses);
			 incidents.forEach(incident-> iRepo.addIncident(incident));
		 }
		 
		 localities.forEach(locality-> lRepo.addLocality(locality));
	 
	 }
	

	public List<Incident> getIncidents() {
		
		return iRepo.getIncidents();
	}


	public String addIncident(Incident inc) {
		 String status = "Incident Updated Succesfully";
		 sRepo.updateStateId(inc);
	   
	     if(inc.getStateId()<=0) {
	    	 status = "State Details Not Found";
	     }
	     else {
	    		 Locality l= new Locality();
	    		 l.setOri("ManualIncident");
	    		 l.setLocalityName(inc.getLocalityName());
	    		 l.setArea(inc.getArea());
	    		 l.setDivision(inc.getDivision());
	    		 l.setLatitude(inc.getLatitude());
	    		 l.setLongitude(inc.getLongitude());
	    		 l.setStateId(inc.getStateId());
	    		 l.setStateName(inc.getStateName());
	    		 int lId=lRepo.addOrUpdateLocalityAndGetId(l);
	    		 
	    		 if(lId<=0) {
	    			 status = "Locality Details not found"; 
	    		 }else {
	    			 inc.setLocalityId(lId);
	    			 if(inc.getOffenseId()<=0) {
	    				 status = "Offence Details Not Found";
	    			 }else {
	    				 iRepo.addOrUpdateIncident(inc);
	    			 }
	    		 }
	    	 
	     }
		return status;
	}


	public List<FormattedIncidents> getIncidentsListFormatted() {
		List<Incident> incidents= iRepo.getIncidents();
		List<FormattedIncidents> formattedIncidents =changeIncidentFormat(incidents);
		return formattedIncidents;
	}
	public List<FormattedIncidents> getIncidentsListByStateFormatted(String stateName) {
		List<Incident> incidents= iRepo.getIncidentsListByStateFormatted(stateName);
		List<FormattedIncidents> formattedIncidents =changeIncidentFormat(incidents);
		return formattedIncidents;
	}
	public List<FormattedIncidents> getIncidentsListByLatLngFormatted(double lat, double lng) {
		List<Incident> incidents= iRepo.getIncidentsListByLatLngFormatted(lat,lng);
		List<FormattedIncidents> formattedIncidents =changeIncidentFormat(incidents);
		return formattedIncidents;
	}
	private List<FormattedIncidents> changeIncidentFormat(List<Incident> incidents) {
		List<FormattedIncidents> formattedIncidents = new ArrayList<FormattedIncidents>();
		Map<Integer,List<Incident>> groupedIncidentData = incidents.stream().collect(Collectors.groupingBy(e->e.getLocalityId()));
		 for (Entry<Integer, List<Incident>> entry : groupedIncidentData.entrySet()) {
			  
			  FormattedIncidents fIncident= new FormattedIncidents();
			  int weight = entry.getValue().stream().mapToInt(e->e.getCount()).sum();
			  List <OffenseData> oDatas = new ArrayList<OffenseData>();
			  
			  fIncident.setIncidentId( entry.getValue().get(0).getIncidentId());
			  fIncident.setIncidentYear( entry.getValue().get(0).getIncidentYear());
			  fIncident.setLocalityId( entry.getValue().get(0).getLocalityId());
			  fIncident.setLocalityName( entry.getValue().get(0).getLocalityName());
			  fIncident.setArea( entry.getValue().get(0).getArea());
			  fIncident.setDivision( entry.getValue().get(0).getDivision());
			  fIncident.setLatitude( entry.getValue().get(0).getLatitude());
			  fIncident.setLongitude( entry.getValue().get(0).getLongitude());
			  fIncident.setStateId( entry.getValue().get(0).getStateId());
			  fIncident.setStateName( entry.getValue().get(0).getStateName());
			  fIncident.setRegionId( entry.getValue().get(0).getRegionId());
			  fIncident.setRegionName( entry.getValue().get(0).getRegionName());
			  fIncident.setCountryId( entry.getValue().get(0).getCountryId());
			  fIncident.setCountryName( entry.getValue().get(0).getCountryName());
			  fIncident.setWeight(weight);
			  
			  for(Incident inc:entry.getValue()) {
				  OffenseData oData= new OffenseData();
				  oData.setOffenseId(inc.getOffenseId());
				  oData.setOffenceName(inc.getOffenceName());
				  oData.setCount(inc.getCount());
				  fIncident.getOffenseList().add(oData);
			  }
			 
			 formattedIncidents.add(fIncident);	
			 
		 	}
	            
		System.out.println("Grouped Data Size---"+groupedIncidentData.size()+"---"+formattedIncidents.size());
		return formattedIncidents;
		
	}


	


	
}
