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
	
	
	 public void addIncident(Incident inc) { 
		  String insertQuery = "Insert into Incident(IncidentType,IncidentYear,LocalityId,OffenseId,IncidentCount) Values(?,?,?,?,?)";
		  jdbcTemplate.update(insertQuery,new Object[] {"ABC",inc.getIncidentYear(),inc.getLocalityId(),inc.getOffenseId(),
				  inc.getCount()}); 
		 }
	 

	public List<Incident> getIncidents() {
		String selectQuery = "Select i.IncidentId,  i.IncidentYear,i.IncidentCount, o.OffenseId,o.OffenseName, l.LocalityId,"
				+ " l.LocalityName, l.Area, l.Division, l.Latitude, l.Longitude,s.StateId, s.StateName,r.RegionId, r.RegionName,"
				+ "c.CountryId,c.CountryName from Incident i join Offence o on i.OffenseId=o.OffenseId join Locality l "
				+ "on i.LocalityId = l.LocalityId join State s on l.StateId=s.StateId join Region r  on s.RegionId = r.RegionId "
				+ "join Country c on r.CountryId=c.CountryId";
		List<Incident> incidents = jdbcTemplate.query(selectQuery, new IncidentMapper());
		return incidents;
	}


	public void addOrUpdateIncident(Incident inc) {
		String selectQuery = "Select i.IncidentId,  i.IncidentYear,i.IncidentCount, o.OffenseId,o.OffenseName, l.LocalityId,"
				+ " l.LocalityName, l.Area, l.Division, l.Latitude, l.Longitude,s.StateId, s.StateName,r.RegionId, "
				+ "r.RegionName,c.CountryId,c.CountryName from Incident i join Offence o on i.OffenseId=o.OffenseId join"
				+ " Locality l on i.LocalityId = l.LocalityId join State s on l.StateId=s.StateId join Region r  "
				+ "on s.RegionId = r.RegionId join Country c on r.CountryId=c.CountryId where l.LocalityId=? and o.OffenseId=?";
		List<Incident> incidents = jdbcTemplate.query(selectQuery,new Object[] {inc.getLocalityId(),inc.getOffenseId()}, 
				new IncidentMapper());
		if(incidents.size()>0) {
			inc.setCount(incidents.get(0).getCount()+1);
			String insertQuery = "Update Incident  set IncidentCount=? where IncidentId=?";
			jdbcTemplate.update(insertQuery,new Object[] {inc.getCount(),incidents.get(0).getIncidentId()}); 
		}else {
			inc.setCount(1); 
			String insertQuery = "Insert into Incident(IncidentType,IncidentYear,LocalityId,OffenseId,IncidentCount)"
					+ " Values(?,?,?,?,?)";
			jdbcTemplate.update(insertQuery,new Object[] {"ABC",inc.getIncidentYear(),inc.getLocalityId(),inc.getOffenseId(),
					inc.getCount()}); 
		}
		  
		
	}


	public List<Incident> getIncidentsListByStateFormatted(String stateName) {
		String selectQuery = "Select i.IncidentId,  i.IncidentYear,i.IncidentCount, o.OffenseId,o.OffenseName, l.LocalityId, "
				+ "l.LocalityName, l.Area, l.Division, l.Latitude, l.Longitude,s.StateId, s.StateName,r.RegionId, r.RegionName,"
				+ "c.CountryId,c.CountryName from Incident i join Offence o on i.OffenseId=o.OffenseId join Locality l "
				+ "on i.LocalityId = l.LocalityId join State s on l.StateId=s.StateId join Region r  on s.RegionId = r.RegionId"
				+ " join Country c on r.CountryId=c.CountryId where s.StateName=?";
		List<Incident> incidents = jdbcTemplate.query(selectQuery,new Object[] {stateName}, new IncidentMapper());
		return incidents;
	}


	public List<Incident> getIncidentsListByLatLngFormatted(double lat, double lng) {
		String selectQuery = "Select i.IncidentId,  i.IncidentYear,i.IncidentCount, o.OffenseId,o.OffenseName, l.LocalityId, "
				+ "l.LocalityName, l.Area, l.Division, l.Latitude, l.Longitude,s.StateId, s.StateName,r.RegionId, r.RegionName,"
				+ "c.CountryId,c.CountryName from Incident i join Offence o on i.OffenseId=o.OffenseId join Locality l "
				+ "on i.LocalityId = l.LocalityId join State s on l.StateId=s.StateId join Region r  on s.RegionId = r.RegionId"
				+ " join Country c on r.CountryId=c.CountryId where (l.Latitude between ? AND ?) AND (l.Longitude between ? AND ?)";
		List<Incident> incidents = jdbcTemplate.query(selectQuery,new Object[] {lat-25.00,lat+25.00, lng-25.00,lng+25.00}, 
				new IncidentMapper());
		return incidents;
	}
}