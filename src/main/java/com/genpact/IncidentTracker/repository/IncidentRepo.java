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

import com.genpact.IncidentTracker.Mapper.IncidentMapper;
import com.genpact.IncidentTracker.Mapper.LiveIncidentMapper;
import com.genpact.IncidentTracker.Mapper.ModifiedTickerMapper;
import com.genpact.IncidentTracker.model.AddIncidentRequest;
import com.genpact.IncidentTracker.model.Incident;
import com.genpact.IncidentTracker.model.LiveIncident;
import com.genpact.IncidentTracker.model.ModifiedTicker;

@Repository
public class IncidentRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	 public void addIncident(Incident inc) { 
		  String insertQuery = "Insert into Incident(IncidentYear,LocalityId,OffenseId,IncidentCount) Values(?,?,?,?,?)";
		  jdbcTemplate.update(insertQuery,new Object[] {inc.getIncidentYear(),inc.getLocalityId(),inc.getOffenseId(), inc.getCount()}); 
		 }
	 

	public List<Incident> getIncidents() {

		String selectQuery = "Select i.IncidentId,  i.IncidentYear,i.IncidentCount, o.OffenseId,o.OffenseName, l.LocalityId, l.LocalityName, l.Area, "
				+ "l.Division, l.Latitude, l.Longitude,s.StateId, s.StateName,r.RegionId, r.RegionName,c.CountryId,c.CountryName"
				+ " from Incident i join Offence o on i.OffenseId=o.OffenseId join Locality l on i.LocalityId = l.LocalityId join State s "
				+ "on l.StateId=s.StateId join Region r  on s.RegionId = r.RegionId join Country c on r.CountryId=c.CountryId limit 2000, 200";

		List<Incident> incidents = jdbcTemplate.query(selectQuery, new IncidentMapper());
		return incidents;
	}


	public String addOrUpdateIncident(AddIncidentRequest inc, int localityId) {
		LocalDateTime dt= LocalDateTime.now();
		int year = dt.getYear();
		System.out.println(dt.toString());
		String incidentId;
		String selectQuery = "Select i.IncidentId,  i.IncidentYear,i.IncidentCount, o.OffenseId,o.OffenseName, l.LocalityId,"
				+ " l.LocalityName, l.Area, l.Division, l.Latitude, l.Longitude,s.StateId, s.StateName,r.RegionId, "
				+ "r.RegionName,c.CountryId,c.CountryName from Incident i join Offence o on i.OffenseId=o.OffenseId join"
				+ " Locality l on i.LocalityId = l.LocalityId join State s on l.StateId=s.StateId join Region r  "
				+ "on s.RegionId = r.RegionId join Country c on r.CountryId=c.CountryId where l.LocalityId=? and o.OffenseId=?";
		List<Incident> incidents = jdbcTemplate.query(selectQuery,new Object[] {localityId,inc.getOffenseId()}, new IncidentMapper());
		if(incidents.size()>0) {
			String insertQuery = "Update Incident  set IncidentCount=? where IncidentId=?";
			jdbcTemplate.update(insertQuery,new Object[] {incidents.get(0).getCount()+1,incidents.get(0).getIncidentId()}); 
		}else { 
			String insertQuery = "Insert into Incident(IncidentYear,LocalityId,OffenseId,IncidentCount)"
					+ " Values(?,?,?,?,?)";
			jdbcTemplate.update(insertQuery,new Object[] {year,localityId,inc.getOffenseId(),1,}); 
		}
		
		final String INSERT_SQL = "Insert into LiveIncident(OffenseId,LocalityId,Description,CreatedDate) Values(?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(INSERT_SQL, new String[] {"IncidentId"});
		            ps.setInt(1,inc.getOffenseId());
		            ps.setInt(2, localityId);
		            ps.setString(3, inc.getDescription());
		            ps.setString(4, dt.toString());
		            return ps;
		        }
		    },
		    keyHolder);
		incidentId = String.valueOf(keyHolder.getKey());
		return incidentId;
		  
		
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
		List<Incident> incidents = jdbcTemplate.query(selectQuery,new Object[] {lat-0.50, lat+0.50, lng-0.50, lng+0.50}, 
				new IncidentMapper());
		return incidents;
	}
	
	public int getCount(double lat, double lng) {
		int count =0;
		String selectQuery = "Select sum(i.IncidentCount) from Incident i join Offence o on i.OffenseId=o.OffenseId join Locality l "
				+ "on i.LocalityId = l.LocalityId join State s on l.StateId=s.StateId join Region r  on s.RegionId = r.RegionId"
				+ " join Country c on r.CountryId=c.CountryId where (l.Latitude between ? AND ?) AND (l.Longitude between ? AND ?)";
		List<Integer> incidents = jdbcTemplate.queryForList(selectQuery,new Object[] {lat-0.50, lat+0.50, lng-0.50, lng+0.50},
										Integer.class);
		System.out.println(incidents);
		if(incidents.size()>0) {
			count = incidents.get(0) != null ? incidents.get(0) : 0;
		}
		return count;
	}
	
	public List<LiveIncident> getLiveIncidentsListByLatLngFormatted(double lat, double lng) {
		String selectQuery = "Select i.IncidentId,  i.CreatedDate,i.Description, o.OffenseId,o.OffenseName, l.LocalityId, "
				+ "l.LocalityName, l.Area, l.Division, l.Latitude, l.Longitude,s.StateId, s.StateName,r.RegionId, r.RegionName,"
				+ "c.CountryId,c.CountryName from LiveIncident i join Offence o on i.OffenseId=o.OffenseId join Locality l "
				+ "on i.LocalityId = l.LocalityId join State s on l.StateId=s.StateId join Region r  on s.RegionId = r.RegionId"
				+ " join Country c on r.CountryId=c.CountryId where (l.Latitude between ? AND ?) AND (l.Longitude between ? AND ?)";
		List<LiveIncident> incidents = jdbcTemplate.query(selectQuery,new Object[] {lat-0.50, lat+0.50, lng-0.50, lng+0.50}, 
				new LiveIncidentMapper());
		return incidents;
	}
	
	public List<ModifiedTicker> getTickerListByLatLngFormatted(double lat, double lng) {
		String selectQuery = "Select i.IncidentId, o.OffenseId,o.OffenseName from LiveIncident i join Offence o on i.OffenseId=o.OffenseId "
				+ "join Locality l on i.LocalityId = l.LocalityId  where (l.Latitude between ? AND ?) "
				+ "AND (l.Longitude between ? AND ?)";
		List<ModifiedTicker> tickers= jdbcTemplate.query(selectQuery,new Object[] {lat-0.50, lat+0.50, lng-0.50, lng+0.50}, 
				new ModifiedTickerMapper());
		return tickers;
	}
	
}