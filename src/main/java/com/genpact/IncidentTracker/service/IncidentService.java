package com.genpact.IncidentTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.Util.IncedentReaderImpl;
import com.genpact.IncidentTracker.model.Incident;
import com.genpact.IncidentTracker.model.Locality;
import com.genpact.IncidentTracker.model.Offense;
import com.genpact.IncidentTracker.model.State;
import com.genpact.IncidentTracker.repository.IncidentRepo;
import com.genpact.IncidentTracker.repository.LocalityRepo;
import com.genpact.IncidentTracker.repository.OffenseRepo;

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
}
