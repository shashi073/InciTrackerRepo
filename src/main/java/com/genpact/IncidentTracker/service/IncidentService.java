package com.genpact.IncidentTracker.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.Util.IncedentReaderImpl;
import com.genpact.IncidentTracker.Util.Key;
import com.genpact.IncidentTracker.model.HeatMapList;
import com.genpact.IncidentTracker.model.Incident;
import com.genpact.IncidentTracker.model.Locality;
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
			 List <Incident> incidents = iReader.readData(l.getOri(), l.getLocalityId(), offenses);
			 incidents.forEach(incident-> iRepo.addIncident(incident));
		 }
		 
		 localities.forEach(locality-> lRepo.addLocality(locality));
	 
	 }
	

	public List<Incident> getIncidents() {
		
		return iRepo.getIncidents();
	}

	public List<HeatMapList> getIncidentsHeatMap(){
		List<Incident> lstIncident = iRepo.getIncidents();
		List<HeatMapList> lstHeatMap = new ArrayList<HeatMapList>();
		
		Map<Key, Integer> map = new HashMap<Key, Integer>();
		
		for(Incident eachIncident : lstIncident) {
			Key keyObj = new Key(eachIncident.getLatitude(), eachIncident.getLongitude());
			
			if(map.get(keyObj) == null) {
				map.put(keyObj, eachIncident.count);
			} else {
				int existingCount = map.get(keyObj);
				existingCount = existingCount + eachIncident.count;
				map.put(keyObj, existingCount);
			}
		}
		
		
		for(Map.Entry<Key, Integer> eachMap: map.entrySet()) {
			HeatMapList heatMap = new HeatMapList();
			heatMap.setLatitude(eachMap.getKey().getLatitude());
			heatMap.setLongitude(eachMap.getKey().getLongitude());
			heatMap.setWeight(eachMap.getValue());
			lstHeatMap.add(heatMap);
		}
		return lstHeatMap;
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
}
