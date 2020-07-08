package com.genpact.IncidentTracker.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.genpact.IncidentTracker.Util.IncedentReaderImpl;
import com.genpact.IncidentTracker.model.AddIncidentRequest;
import com.genpact.IncidentTracker.model.AggregatedIncident;
import com.genpact.IncidentTracker.model.LiveIncident;
import com.genpact.IncidentTracker.model.Locality;
import com.genpact.IncidentTracker.model.ModifiedTicker;
import com.genpact.IncidentTracker.model.Offense;
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
			 List <AggregatedIncident> incidents = iReader.readData(l.getOri(), l.getLocalityId(), offenses);
			 incidents.forEach(incident-> iRepo.addIncident(incident));
		 }
		 
		 localities.forEach(locality-> lRepo.addLocality(locality));
	 
	 }
	

	
	public String addIncident(AddIncidentRequest inc) {
		 String status = "Incident Updated Succesfully";
		 int stateId =sRepo.updateStateId(inc);
	   
	     if(stateId<=0) {
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
	    		 l.setStateId(stateId);
	    		 l.setStateName(inc.getStateName());
	    		 int lId=lRepo.addOrUpdateLocalityAndGetId(l);
	    		 
	    		 if(lId<=0) {
	    			 status = "Locality Details not found"; 
	    		 }else {
	    			 
	    			 if(inc.getOffenseId()<=0) {
	    				 status = "Offence Details Not Found";
	    			 }else {
	    				status = String.valueOf(iRepo.addOrUpdateIncident(inc, lId));
	    			 }
	    		 }
	    	 
	     }
		return status;
	}
	
	
	


	public int getCount(double lat, double lng) {
		return iRepo.getCount(lat, lng);
	}
	
	public List<LiveIncident> getLiveIncidentsListByLatLngFormatted(double lat, double lng){
		return iRepo.getLiveIncidentsListByLatLngFormatted(lat, lng);
	}

	public List<ModifiedTicker> getTickerListByLatLngFormatted(double lat, double lng){
		return iRepo.getTickerListByLatLngFormatted(lat, lng);
	}
	
}
