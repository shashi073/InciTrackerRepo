package com.genpact.IncidentTracker.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.genpact.IncidentTracker.model.Incident;
import com.genpact.IncidentTracker.model.ModifiedTicker;

public class ModifiedTickerMapper implements RowMapper<ModifiedTicker> {

	@Override
	public ModifiedTicker mapRow(ResultSet rs, int rowNum) throws SQLException {
		ModifiedTicker i = new ModifiedTicker();
		
		i.setIncidentId(rs.getInt("IncidentId"));
		i.setOffenseId(rs.getInt("OffenseId"));
		i.setOffenceName(rs.getString("OffenseName"));
		return i;
	}
	
}
