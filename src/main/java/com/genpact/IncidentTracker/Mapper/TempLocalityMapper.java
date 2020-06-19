package com.genpact.IncidentTracker.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.genpact.IncidentTracker.model.Locality;

public class TempLocalityMapper implements RowMapper<Locality> {

	@Override
	public Locality mapRow(ResultSet rs, int rowNum) throws SQLException {
		Locality l = new Locality();
		
		l.setLocalityId(rs.getInt("LocalityId"));
		l.setOri(rs.getString("Ori"));
		return l;
	}
	
}