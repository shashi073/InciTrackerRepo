 package com.genpact.IncidentTracker.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.genpact.IncidentTracker.Mapper.IncidentMapper;
import com.genpact.IncidentTracker.Mapper.LocalityMapper;
import com.genpact.IncidentTracker.Mapper.TempLocalityMapper;
import com.genpact.IncidentTracker.model.Incident;
import com.genpact.IncidentTracker.model.Locality;

@Repository
public class LocalityRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addLocality(Locality locality) {
		 String insertQuery = "Insert into Locality(Ori,LocalityName,Area,Division,Latitude,Longitude,StateId) Values(?,?,?,?,?,?,?)";
		 jdbcTemplate.update(insertQuery,new Object[] {locality.getOri(),locality.getLocalityName(),locality.getArea(),locality.getDivision(),locality.getLatitude(),locality.getLongitude(),locality.getStateId()});
	}

	public List<Locality> getLocalities() {
		String selectQuery = "Select l.LocalityId, l.Ori,l.LocalityName, l.Area, l.Division, l.Latitude, l.Longitude,s.StateId,"
				+ "s.StateName,s.StateAbbr, r.RegionId, r.RegionName,c.CountryId,c.CountryName from Locality l join State s "
				+ "on l.StateId=s.StateId join Region r  on s.RegionId = r.RegionId join Country c on r.CountryId=c.CountryId";
		List<Locality> localities = jdbcTemplate.query(selectQuery, new LocalityMapper());
		return localities;
	}
	
	
	public List<Locality> getTempLocality() {
		String selectQuery = "Select l.LocalityId, l.Ori from Locality l where l.LocalityId not in(Select i.LocalityId from Incident i)";
		List<Locality> localities = jdbcTemplate.query(selectQuery, new TempLocalityMapper());
		return localities;
	}
}