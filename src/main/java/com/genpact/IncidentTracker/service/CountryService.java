package com.genpact.IncidentTracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.model.Country;
import com.genpact.IncidentTracker.repository.CountryRepo;

@Service
public class CountryService {
	@Autowired
	private CountryRepo cRepo;

	public void addCountries() {
		List<Country> countries = new ArrayList<Country>();
		Country c1 = new Country();
		c1.setCountryCode("USA");
		c1.setCountryName("United States Of America");
		countries.add(c1);
		
		Country c2 = new Country();
		c2.setCountryCode("UK");
		c2.setCountryName("United Kingdom");
		countries.add(c2);
		
		Country c3 = new Country();
		c3.setCountryCode("In");
		c3.setCountryName("India");
		countries.add(c3);
		
		countries.forEach(country-> cRepo.addCountry(country));
		
	}

	public List<Country> getCountries() {
		
		return cRepo.getCountries();
	} 

}
