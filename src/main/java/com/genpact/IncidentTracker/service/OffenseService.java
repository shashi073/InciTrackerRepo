package com.genpact.IncidentTracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.model.Country;
import com.genpact.IncidentTracker.model.Offense;
import com.genpact.IncidentTracker.repository.CountryRepo;
import com.genpact.IncidentTracker.repository.OffenseRepo;

@Service
public class OffenseService {
	@Autowired
	private OffenseRepo oRepo;

	public void addOffenses() {
		List<Offense> offenses = new ArrayList<Offense>();
		Offense o1 = new Offense();
		o1.setOffenseName("robbery");
		offenses.add(o1);
		
		Offense o2 = new Offense();
		o2.setOffenseName("rape");
		offenses.add(o2);
		
		Offense o3 = new Offense();
		o3.setOffenseName("property-crime");
		offenses.add(o3);
		
		Offense o4 = new Offense();
		o4.setOffenseName("motor-vehicle-theft");
		offenses.add(o4);
		
		Offense o5 = new Offense();
		o5.setOffenseName("larceny");
		offenses.add(o5);
		
		
		Offense o6 = new Offense();
		o6.setOffenseName("human-trafficing");
		offenses.add(o6);
		
		Offense o7 = new Offense();
		o7.setOffenseName("homicide");
		offenses.add(o7);
		
		Offense o8 = new Offense();
		o8.setOffenseName("burglary");
		offenses.add(o8);
		
		Offense o9 = new Offense();
		o9.setOffenseName("arson");
		offenses.add(o9);
		
		Offense o10 = new Offense();
		o10.setOffenseName("aggravated-assault");
		offenses.add(o10);
		
		Offense o11 = new Offense();
		o11.setOffenseName("violent-crime");
		offenses.add(o11);
		
		
		offenses.forEach(offense-> oRepo.addOffense(offense));
		
	}

	public List<Offense> getOffenses() {
		
		return oRepo.getOffenses();
	} 

}
