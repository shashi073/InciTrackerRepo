package com.genpact.IncidentTracker.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.genpact.IncidentTracker.Mapper.CountryMapper;
import com.genpact.IncidentTracker.model.Country;

@Repository
public class CountryRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addCountry(Country country) {
		 String insertQuery = "Insert into Country(CountryCode,CountryName) Values(?,?)";
		 jdbcTemplate.update(insertQuery,new Object[] {country.getCountryCode(),country.getCountryName()});
	}

	public List<Country> getCountries() {
		String selectQuery = "Select CountryId,CountryCode,CountryName from Country";
		List<Country> countries = jdbcTemplate.query(selectQuery, new CountryMapper());
		return countries;
	}

	
}
