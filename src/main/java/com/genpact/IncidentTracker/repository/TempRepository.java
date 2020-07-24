package com.genpact.IncidentTracker.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.genpact.IncidentTracker.Mapper.LocalityMapper;
import com.genpact.IncidentTracker.model.AddIncidentRequest;
import com.genpact.IncidentTracker.model.Locality;

@Repository
public class TempRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	 
	public void addOrUpdateIncident(AddIncidentRequest inc, int localityId) {
	
		
			String insertQuery = "Insert into LiveIncident(OffenseId,LocalityId,Description,CreatedDate,IncidentLatitude,IncidentLongitude) Values(?,?,?,?,?,?)";
			jdbcTemplate.update(insertQuery,new Object[] {inc.getOffenseId(),localityId,inc.getDescription(),
					inc.getIncidentDateTime().toString(),inc.getLatitude(),inc.getLongitude()}); 
		
	
		  
		
	}

	public List<Locality> getLocalities(int startIndex) {
		String selectQuery = "Select l.LocalityId, l.Ori,l.LocalityName, l.Area, l.Division, l.Latitude, l.Longitude,s.StateId,"
				+ "s.StateName,s.StateAbbr, r.RegionId, r.RegionName,c.CountryId,c.CountryName from Locality l join State s "
				+ "on l.StateId=s.StateId join Region r  on s.RegionId = r.RegionId join Country c on r.CountryId=c.CountryId limit ?,?";
		List<Locality> localities = jdbcTemplate.query(selectQuery, new Object[] {startIndex, 3500},new LocalityMapper());
		return localities;
	}
	
	public int getIncidentId(double lat, double lng) {
		int incidentId=0;
		String selectQuery = "Select i.IncidentId from LiveIncident i  "
							+ "where (i.IncidentLatitude between ? AND ?) AND (i.IncidentLongitude between ? AND ?)";
		List<Integer> incidents = jdbcTemplate.queryForList(selectQuery,new Object[] {lat-0.005, lat+0.005, lng-0.005, lng+0.005},
				Integer.class);
		if(incidents.size()>0) {
			incidentId = incidents.get(0) != null ? incidents.get(0) : 0;
		}
		return incidentId;
	}
	
}