package com.genpact.IncidentTracker.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.genpact.IncidentTracker.Mapper.CountryMapper;
import com.genpact.IncidentTracker.Mapper.OffenseMapper;
import com.genpact.IncidentTracker.model.Country;
import com.genpact.IncidentTracker.model.Offense;

@Repository
public class OffenseRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addOffense(Offense offence) {
		 String insertQuery = "Insert into Offence(OffenseName) Values(?)";
		 jdbcTemplate.update(insertQuery,new Object[] {offence.getOffenseName()});
	}

	public List<Offense> getOffenses() {
		String selectQuery = "Select OffenseId,OffenseName from Offence";
		List<Offense> offenses = jdbcTemplate.query(selectQuery, new OffenseMapper());
		return offenses;
	}

	
}
