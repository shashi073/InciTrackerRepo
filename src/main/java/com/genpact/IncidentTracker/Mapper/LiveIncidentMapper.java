package com.genpact.IncidentTracker.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.genpact.IncidentTracker.model.LiveIncident;

public class LiveIncidentMapper implements RowMapper<LiveIncident> {

	@Override
	public LiveIncident mapRow(ResultSet rs, int rowNum) throws SQLException {
		LiveIncident i = new LiveIncident();
		
		i.setIncidentId(rs.getInt("IncidentId"));
		i.setDescription(rs.getString("Description"));
		i.setCreatedDate(rs.getString("CreatedDate"));
		i.setOffenseId(rs.getInt("OffenseId"));
		i.setOffenceName(rs.getString("OffenseName"));
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
