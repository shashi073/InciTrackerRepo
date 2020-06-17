package com.genpact.IncidentTracker.Mapper;


import com.genpact.IncidentTracker.model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CountryMapper implements RowMapper<Country> 
{

	@Override
	public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
		Country c = new Country();
		c.setCountryId(rs.getInt("CountryId"));
		c.setCountryCode(rs.getString("CountryCode"));
		c.setCountryName(rs.getString("CountryName"));
		return c;
	}

	

}
