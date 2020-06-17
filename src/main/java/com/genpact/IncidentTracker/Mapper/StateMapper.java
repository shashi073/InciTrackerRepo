package com.genpact.IncidentTracker.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.genpact.IncidentTracker.model.Country;
import com.genpact.IncidentTracker.model.State;

public class StateMapper implements RowMapper<State> {

	@Override
	public State mapRow(ResultSet rs, int rowNum) throws SQLException {
		State s = new State();
		
		s.setStateId(rs.getInt("StateId"));
		s.setStateAbbr(rs.getString("StateAbbr"));
		s.setStateName(rs.getString("StateName"));
		s.setFipsCode(rs.getLong("FipsCode"));
		s.setRegionId(rs.getInt("RegionId"));
		s.setRegionCode(rs.getLong("RegionCode"));
		s.setRegionName(rs.getString("RegionName"));
		s.setCountryId(rs.getInt("CountryId"));
		s.setCountryName(rs.getString("CountryName"));
		return s;
	}
	
}