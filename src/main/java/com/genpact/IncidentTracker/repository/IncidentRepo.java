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
import com.genpact.IncidentTracker.Mapper.LiveIncidentMapper;
import com.genpact.IncidentTracker.Mapper.ModifiedTickerMapper;
import com.genpact.IncidentTracker.model.AddIncidentRequest;
import com.genpact.IncidentTracker.model.AggregatedIncident;
import com.genpact.IncidentTracker.model.LiveIncident;
import com.genpact.IncidentTracker.model.Ticker;

@Repository
public class IncidentRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	 
	public void addIncident(AggregatedIncident inc) { 
		  String insertQuery = "Insert into Incident(IncidentYear,LocalityId,OffenseId,IncidentCount) Values(?,?,?,?,?)";
		  jdbcTemplate.update(insertQuery,new Object[] {inc.getIncidentYear(),inc.getLocalityId(),inc.getOffenseId(), inc.getCount()}); 
		 }
	 
	

	public String addOrUpdateIncident(AddIncidentRequest inc, int localityId) {
		LocalDateTime dt= LocalDateTime.now();
		int year = dt.getYear();
		System.out.println(dt.toString());
		String incidentId;
		String selectQuery = "Select i.IncidentId from Incident i join Offence o on i.OffenseId=o.OffenseId join"
				+ " Locality l on i.LocalityId = l.LocalityId  where l.LocalityId=? and o.OffenseId=?";
		List<Integer> incidents = jdbcTemplate.queryForList(selectQuery,new Object[] {localityId,inc.getOffenseId()}, Integer.class);
		if(incidents.size()>0) {
			String insertQuery = "Update Incident  set IncidentCount=IncidentCount+1 where IncidentId=?";
			jdbcTemplate.update(insertQuery,new Object[] {incidents.get(0).intValue()}); 
		}else { 
			String insertQuery = "Insert into Incident(IncidentYear,LocalityId,OffenseId,IncidentCount)"
					+ " Values(?,?,?,?)";
			jdbcTemplate.update(insertQuery,new Object[] {year,localityId,inc.getOffenseId(),1}); 
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

	public int getCount(double lat, double lng) {
		int count =0;
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(dt.toString());
		LocalDateTime dt1 =dt.minusDays(365);
		System.out.println(dt1.toString());
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
		LocalDateTime maxDate = LocalDateTime.now();
		LocalDateTime minDate = maxDate.minusDays(7);
		String selectQuery = "Select i.IncidentId,  i.CreatedDate,i.Description, o.OffenseId,o.OffenseName, l.LocalityId, "
				+ "l.LocalityName, l.Area, l.Division, l.Latitude, l.Longitude,s.StateId, s.StateName,r.RegionId, r.RegionName,"
				+ "c.CountryId,c.CountryName from LiveIncident i join Offence o on i.OffenseId=o.OffenseId join Locality l "
				+ "on i.LocalityId = l.LocalityId join State s on l.StateId=s.StateId join Region r  on s.RegionId = r.RegionId"
				+ " join Country c on r.CountryId=c.CountryId where (l.Latitude between ? AND ?) AND (l.Longitude between ? AND ?)"
				+ "AND (i.CreatedDate between ? AND ?)";
		List<LiveIncident> incidents = jdbcTemplate.query(selectQuery,new Object[] {lat-0.50, lat+0.50, lng-0.50, lng+0.50, minDate, maxDate}, 
				new LiveIncidentMapper());
		return incidents;
	}
	
	public List<Ticker> getTickerListByLatLngFormatted(double lat, double lng) {
		LocalDateTime maxDate = LocalDateTime.now();
		LocalDateTime minDate = maxDate.minusDays(7);
		String selectQuery = "Select o.OffenseName,count(1) from LiveIncident i join Offence o on i.OffenseId=o.OffenseId "
				+ "join Locality l on i.LocalityId = l.LocalityId  where (l.Latitude between ? AND ?) "
				+ "AND (l.Longitude between ? AND ?) AND (i.CreatedDate between ? AND ?) group by o.OffenseId";
		List<Ticker> tickers= jdbcTemplate.query(selectQuery,new Object[] {lat-0.50, lat+0.50, lng-0.50, lng+0.50, minDate, maxDate}, 
				new ModifiedTickerMapper());
		return tickers;
	}
	
	public List<Ticker> getTickerListByLatLngAndDays(double lat, double lng,int noOfDays ) {
		LocalDateTime maxDate = LocalDateTime.now();
		LocalDateTime minDate = maxDate.minusDays(noOfDays);
		String selectQuery = "Select o.OffenseName,count(1) from LiveIncident i join Offence o on i.OffenseId=o.OffenseId "
				+ "join Locality l on i.LocalityId = l.LocalityId  where (l.Latitude between ? AND ?) "
				+ "AND (l.Longitude between ? AND ?) AND (i.CreatedDate between ? AND ?) group by o.OffenseId";
		List<Ticker> tickers= jdbcTemplate.query(selectQuery,new Object[] {lat-0.50, lat+0.50, lng-0.50, lng+0.50, minDate, maxDate}, 
				new ModifiedTickerMapper());
		return tickers;
	}
}