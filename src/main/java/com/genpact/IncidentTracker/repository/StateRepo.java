package com.genpact.IncidentTracker.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.genpact.IncidentTracker.Mapper.StateMapper;
import com.genpact.IncidentTracker.model.Incident;
import com.genpact.IncidentTracker.model.State;

@Repository
public class StateRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addState(State state) {
		 String insertQuery = "Insert into State(StateName,StateAbbr,FipsCode,RegionId) Values(?,?,?,?)";
		 jdbcTemplate.update(insertQuery,new Object[] {state.getStateName(),state.getStateAbbr(),state.getFipsCode(),state.getRegionId()});
	}

	public List<State> getStates() {
		String selectQuery = "Select s.StateId,s.StateName,s.StateAbbr,s.FipsCode,r.RegionId, r.RegionCode,r.RegionName,"
				+ "c.CountryId,c.CountryName from State s join Region r  on s.RegionId = r.RegionId join Country c on r.CountryId=c.CountryId";
		List<State> states = jdbcTemplate.query(selectQuery, new StateMapper());
		return states;
	}

	public void updateStateId(Incident inc) {
		String selectQuery = "Select s.StateId,s.StateName,s.StateAbbr,s.FipsCode,r.RegionId, r.RegionCode,r.RegionName,"
							 + "c.CountryId,c.CountryName from State s join Region r  on s.RegionId = r.RegionId join Country c"
							 + " on r.CountryId=c.CountryId where s.StateName=?";
		List<State> states = jdbcTemplate.query(selectQuery,new Object[] {inc.getStateName()}, new StateMapper());
		if(states.size()>0) {
			inc.setStateId(states.get(0).getStateId());
		}
		
	}
}
