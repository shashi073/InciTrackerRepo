package com.genpact.IncidentTracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.genpact.IncidentTracker.Util.IncedentReaderImpl;
import com.genpact.IncidentTracker.model.FormattedIncidents;
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
			  
			  
			  fIncident.setIncidentId( entry.getValue().get(0).getIncidentId());
			  fIncident.setIncidentYear( entry.getValue().get(0).getIncidentYear()); 
			  
			  fIncident.getBasicData().setLatitude( entry.getValue().get(0).getLatitude());
			  fIncident.getBasicData().setLongitude( entry.getValue().get(0).getLongitude());
			  fIncident.getBasicData().setWeight(weight);
			  
			  
			  
			  fIncident.getOtherData().setLocalityId( entry.getValue().get(0).getLocalityId());
			  fIncident.getOtherData().setLocalityName( entry.getValue().get(0).getLocalityName());
			  fIncident.getOtherData().setArea( entry.getValue().get(0).getArea());
			  fIncident.getOtherData().setDivision( entry.getValue().get(0).getDivision());
			  fIncident.getOtherData().setStateId( entry.getValue().get(0).getStateId());
			  fIncident.getOtherData().setStateName( entry.getValue().get(0).getStateName());
			  fIncident.getOtherData().setRegionId( entry.getValue().get(0).getRegionId());
			  fIncident.getOtherData().setRegionName( entry.getValue().get(0).getRegionName());
			  fIncident.getOtherData().setCountryId( entry.getValue().get(0).getCountryId());
			  fIncident.getOtherData().setCountryName( entry.getValue().get(0).getCountryName());
			  
			  
			  for(Incident inc:entry.getValue()) {
				  Offense oData= new Offense();
				  oData.setOffenseId(inc.getOffenseId());
				  oData.setOffenseName(inc.getOffenceName());
				  oData.setDescription(inc.getOffenceName());
				  oData.setCount(inc.getCount());
				  fIncident.getOffenseList().add(oData);
			  }
			 
			 formattedIncidents.add(fIncident);	
			 
		 	}
	            
		System.out.println("Grouped Data Size---"+groupedIncidentData.size()+"---"+formattedIncidents.size());
		return formattedIncidents;
		
	}


	


	
}
