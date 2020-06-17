package com.genpact.IncidentTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.Util.RegionReaderImpl;
import com.genpact.IncidentTracker.model.Country;
import com.genpact.IncidentTracker.model.Region;
import com.genpact.IncidentTracker.repository.CountryRepo;
import com.genpact.IncidentTracker.repository.RegionRepo;

@Service
public class RegionService {

	@Autowired
	private CountryRepo cRepo;
	@Autowired
	private RegionReaderImpl rReader;
	@Autowired
	private RegionRepo rRepo;

	public void addRegions() {
		List<Country> countries = cRepo.getCountries();
		List<Region> regions=rReader.readData(countries);
		
		
		regions.forEach(region-> rRepo.addRegion(region));
		
	}

	public List<Region> getRegions() {
		
		return rRepo.getRegions();
	}
}
