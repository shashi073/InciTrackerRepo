package com.genpact.IncidentTracker.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.genpact.IncidentTracker.Mapper.IncidentMapper;
import com.genpact.IncidentTracker.Mapper.LocalityMapper;
import com.genpact.IncidentTracker.model.Incident;

@Repository
public class IncidentRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*
	 * public void addLocality(Locality locality) { String insertQuery =
	 * "Insert into Locality(Ori,LocalityName,Area,Division,Latitude,Longitude,StateId) Values(?,?,?,?,?,?,?)"
	 * ; jdbcTemplate.update(insertQuery,new Object[]
	 * {locality.getOri(),locality.getLocalityName(),locality.getArea(),locality.
	 * getDivision(),locality.getLatitude(),locality.getLongitude(),locality.
	 * getStateId()}); }
	 */

	public List<Incident> getIncidents() {
		String selectQuery = "Select i.IncidentId, i.IncidentType, i.IncidentYear, l.LocalityId, l.LocalityName, l.Area, "
				+ "l.Division, l.Latitude, l.Longitude,s.StateId, s.StateName,r.RegionId, r.RegionName,c.CountryId,c.CountryName"
				+ " from Incident i join Locality l on i.LocalityId = l.LocalityId join State s "
				+ "on l.StateId=s.StateId join Region r  on s.RegionId = r.RegionId join Country c on r.CountryId=c.CountryId";
		List<Incident> incidents = jdbcTemplate.query(selectQuery, new IncidentMapper());
		return incidents;
	}
}