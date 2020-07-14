package com.genpact.IncidentTracker.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		String incidentDateTime= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println(incidentDateTime);
		String incidentId;
		
		final String INSERT_SQL = "Insert into LiveIncident(OffenseId,LocalityId,Description,CreatedDate,IncidentLatitude,IncidentLongitude) Values(?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(INSERT_SQL, new String[] {"IncidentId"});
		            ps.setInt(1,inc.getOffenseId());
		            ps.setInt(2, localityId);
		            ps.setString(3, inc.getDescription());
		            ps.setString(4, incidentDateTime);
		            ps.setDouble(5, inc.getLatitude());
		            ps.setDouble(6, inc.getLongitude());
		            return ps;
		        }
		    },
		    keyHolder);
		incidentId = String.valueOf(keyHolder.getKey());
		return incidentId;
		  
		
	}

	public int getCount(double lat, double lng) {
		int count =0;
		String maxDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String minDate = LocalDateTime.now().minusDays(15).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String selectQuery = "Select count(1) from LiveIncident  where (IncidentLatitude between ? AND ?) "
							+ "AND (IncidentLongitude between ? AND ?) AND (CreatedDate between ? AND ?)";
		List<Integer> incidents = jdbcTemplate.queryForList(selectQuery,new Object[] {lat-0.0050, lat+0.0050, lng-0.0050, lng+0.0050,
									minDate,maxDate},	Integer.class);
		if(incidents.size()>0) {
			count = incidents.get(0) != null ? incidents.get(0) : 0;
		}
		return count;
	}
	
	public List<LiveIncident> getLiveIncidentsListByLatLngFormatted(double lat, double lng) {
		String maxDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String minDate = LocalDateTime.now().minusDays(15).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String selectQuery = "Select i.IncidentId,  i.CreatedDate,i.Description, o.OffenseId,o.OffenseName, l.LocalityId, "
				+ "l.LocalityName, l.Area, l.Division, i.IncidentLatitude, i.IncidentLongitude,s.StateId, s.StateName,r.RegionId, "
				+ "r.RegionName, c.CountryId,c.CountryName from LiveIncident i join Offence o on i.OffenseId=o.OffenseId join Locality l "
				+ "on i.LocalityId = l.LocalityId join State s on l.StateId=s.StateId join Region r  on s.RegionId = r.RegionId"
				+ " join Country c on r.CountryId=c.CountryId where (i.IncidentLatitude between ? AND ?) "
				+ "AND (i.IncidentLongitude between ? AND ?) AND (i.CreatedDate between ? AND ?)";
		List<LiveIncident> incidents = jdbcTemplate.query(selectQuery,new Object[] {lat-0.0050, lat+0.0050, lng-0.0050, lng+0.0050,
									minDate, maxDate}, 	new LiveIncidentMapper());
		return incidents;
	}
	
	public List<Ticker> getTickerListByLatLngFormatted(double lat, double lng) {
		String maxDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String minDate = LocalDateTime.now().minusDays(15).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String selectQuery = "Select o.OffenseName,count(1) from LiveIncident i join Offence o on i.OffenseId=o.OffenseId "
				+ "where (i.IncidentLatitude between ? AND ?) AND (i.IncidentLongitude between ? AND ?) "
				+ "AND (i.CreatedDate between ? AND ?) group by o.OffenseId";
		List<Ticker> tickers= jdbcTemplate.query(selectQuery,new Object[] {lat-0.0050, lat+0.0050, lng-0.0050, lng+0.0050, minDate, maxDate}, 
				new ModifiedTickerMapper());
		return tickers;
	}
	
	public List<Ticker> getTickerListByLatLngAndDays(double lat, double lng,int noOfDays ) {
		String maxDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String minDate = LocalDateTime.now().minusDays(noOfDays).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String selectQuery = "Select o.OffenseName,count(1) from LiveIncident i join Offence o on i.OffenseId=o.OffenseId "
				+ " where (i.IncidentLatitude between ? AND ?) AND (i.IncidentLongitude between ? AND ?)"
				+ " AND (i.CreatedDate between ? AND ?) group by o.OffenseId";
		List<Ticker> tickers= jdbcTemplate.query(selectQuery,new Object[] {lat-0.0050, lat+0.0050, lng-0.0050, lng+0.0050, minDate, maxDate}, 
				new ModifiedTickerMapper());
		return tickers;
	}
}