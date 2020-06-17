package com.genpact.IncidentTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.Util.StateReaderImpl;
import com.genpact.IncidentTracker.model.Region;
import com.genpact.IncidentTracker.model.State;
import com.genpact.IncidentTracker.repository.RegionRepo;
import com.genpact.IncidentTracker.repository.StateRepo;

@Service
public class StateService {

	
	@Autowired
	private StateReaderImpl sReader;
	@Autowired
	private RegionRepo rRepo;
	@Autowired
	private StateRepo sRepo;

	public void addStates() {
		List<Region> regions = rRepo.getRegions();
		List<State> states= sReader.readData(regions);
		
		
		states.forEach(state-> sRepo.addState(state));
		
	}

	public List<State> getStates() {
		
		return sRepo.getStates();
	}
}
