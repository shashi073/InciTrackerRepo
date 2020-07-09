package com.genpact.IncidentTracker.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.genpact.IncidentTracker.model.Ticker;

public class ModifiedTickerMapper implements RowMapper<Ticker> {

	@Override
	public Ticker mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ticker i = new Ticker();
		
		i.setOffenceName(rs.getString(1));
		i.setCount(rs.getInt(2));
		
		return i;
	}
	
}
