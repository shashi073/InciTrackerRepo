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
import com.genpact.IncidentTracker.model.AddIncidentRequest;

@Repository
public class TempRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	 
	public String addOrUpdateIncident(AddIncidentRequest inc, int localityId) {
	
		int year = inc.getIncidentDateTime().getYear();
		System.out.println(inc.getIncidentDateTime().toString());
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
		            ps.setString(4, inc.getIncidentDateTime().toString());
		            return ps;
		        }
		    },
		    keyHolder);
		incidentId = String.valueOf(keyHolder.getKey());
		return incidentId;
		  
		
	}

	
	
}