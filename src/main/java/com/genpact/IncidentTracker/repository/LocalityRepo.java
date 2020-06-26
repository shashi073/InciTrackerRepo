 package com.genpact.IncidentTracker.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.genpact.IncidentTracker.Mapper.LocalityMapper;
import com.genpact.IncidentTracker.Mapper.TempLocalityMapper;
import com.genpact.IncidentTracker.model.Locality;

@Repository
public class LocalityRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addLocality(Locality locality) {
		String selectQuery = "Select l.LocalityId, l.Ori,l.LocalityName, l.Area, l.Division, l.Latitude, l.Longitude,s.StateId,"
				+ "s.StateName,s.StateAbbr, r.RegionId, r.RegionName,c.CountryId,c.CountryName from Locality l join State s "
				+ "on l.StateId=s.StateId join Region r  on s.RegionId = r.RegionId join Country c on r.CountryId=c.CountryId "
				+ "where l.Latitude=? and l.Longitude=?";
		List<Locality> localities = jdbcTemplate.query(selectQuery, new Object[] {locality.getLatitude(),locality.getLongitude()},new LocalityMapper());
		if(localities.size()==0) {
			String insertQuery = "Insert into Locality(Ori,LocalityName,Area,Division,Latitude,Longitude,StateId) Values(?,?,?,?,?,?,?)";
			 jdbcTemplate.update(insertQuery,new Object[] {locality.getOri(),locality.getLocalityName(),locality.getArea(),locality.getDivision(),locality.getLatitude(),locality.getLongitude(),locality.getStateId()});
		}
		
		 
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

	public int addOrUpdateLocalityAndGetId(Locality locality) {
		int localityId = 0;
		String selectQuery = "Select l.LocalityId, l.Ori,l.LocalityName, l.Area, l.Division, l.Latitude, l.Longitude,s.StateId,"
				+ "s.StateName,s.StateAbbr, r.RegionId, r.RegionName,c.CountryId,c.CountryName from Locality l join State s "
				+ "on l.StateId=s.StateId join Region r  on s.RegionId = r.RegionId join Country c on r.CountryId=c.CountryId "
				+ "where latitude=? and longitude=?";
		List<Locality> localities = jdbcTemplate.query(selectQuery,new Object[] {locality.getLatitude(),locality.getLongitude()}, new LocalityMapper());
		if(localities.size()>0) {
			localityId=localities.get(0).getLocalityId();
		}
		else {
			final String INSERT_SQL = "Insert into Locality(Ori,LocalityName,Area,Division,Latitude,Longitude,StateId) Values(?,?,?,?,?,?,?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement(INSERT_SQL, new String[] {"LocalityId"});
			            ps.setString(1, locality.getOri());
			            ps.setString(2, locality.getLocalityName());
			            ps.setString(3, locality.getArea());
			            ps.setString(4, locality.getDivision());
			            ps.setDouble(5, locality.getLatitude());
			            ps.setDouble(6, locality.getLongitude());
			            ps.setInt(7, locality.getStateId());
			            return ps;
			        }
			    },
			    keyHolder);
			localityId = keyHolder.getKey().intValue();
		}
		
		return localityId;
	}
}