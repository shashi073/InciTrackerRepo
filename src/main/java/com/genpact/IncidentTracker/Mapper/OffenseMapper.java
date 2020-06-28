package com.genpact.IncidentTracker.Mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.genpact.IncidentTracker.model.Country;
import com.genpact.IncidentTracker.model.Offense;

public class OffenseMapper implements RowMapper<Offense> 
{

	@Override
	public Offense mapRow(ResultSet rs, int rowNum) throws SQLException {
		Offense o = new Offense();
		o.setOffenseId(rs.getInt("OffenseId"));
		o.setOffenseName(rs.getString("OffenseName"));
		o.setValue(rs.getString("OffenseName"));
		return o;
	}

	

}