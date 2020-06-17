package com.genpact.IncidentTracker.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.genpact.IncidentTracker.model.Incident;

public class IncidentMapper implements RowMapper<Incident> {

	@Override
	public Incident mapRow(ResultSet rs, int rowNum) throws SQLException {
		Incident i = new Incident();
		
		i.setIncidentId(rs.getInt("IncidentId"));
		i.setIncidentType(rs.getString("IncidentType"));
		i.setIncidentYear(rs.getString("IncidentYear"));
		i.setLocalityId(rs.getInt("LocalityId"));
		i.setLocalityName(rs.getString("LocalityName"));
		i.setArea(rs.getString("Area"));
		i.setDivision(rs.getString("Division"));
		i.setLatitude(rs.getDouble("Latitude"));
		i.setLongitude(rs.getDouble("Longitude"));
		i.setStateId(rs.getInt("StateId"));
		i.setStateName(rs.getString("StateName"));
		i.setRegionId(rs.getInt("RegionId"));
		i.setRegionName(rs.getString("RegionName"));
		i.setCountryId(rs.getInt("CountryId"));
		i.setCountryName(rs.getString("CountryName"));
		return i;
	}
	
}