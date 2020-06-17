package com.genpact.IncidentTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.Util.LocalityReaderImpl;
import com.genpact.IncidentTracker.model.Locality;
import com.genpact.IncidentTracker.model.State;
import com.genpact.IncidentTracker.repository.LocalityRepo;
import com.genpact.IncidentTracker.repository.StateRepo;

@Service
public class LocalityService {

	
	@Autowired
	private LocalityReaderImpl lReader;
	@Autowired
	private LocalityRepo lRepo;
	@Autowired
	private StateRepo sRepo;

	public void addLocalities() {
		List<State> states = sRepo.getStates();
		List<Locality> localities= lReader.readData(states);
		
		
		localities.forEach(locality-> lRepo.addLocality(locality));
		
	}

	public List<Locality> getLocalities() {
		
		return lRepo.getLocalities();
	}
}
