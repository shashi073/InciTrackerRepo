package com.genpact.IncidentTracker.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.genpact.IncidentTracker.model.Locality;

public class LocalityMapper implements RowMapper<Locality> {

	@Override
	public Locality mapRow(ResultSet rs, int rowNum) throws SQLException {
		Locality l = new Locality();
		
		l.setLocalityId(rs.getInt("LocalityId"));
		l.setOri(rs.getString("Ori"));
		l.setLocalityName(rs.getString("LocalityName"));
		l.setArea(rs.getString("Area"));
		l.setDivision(rs.getString("Division"));
		l.setLatitude(rs.getDouble("Latitude"));
		l.setLongitude(rs.getDouble("Longitude"));
		l.setStateId(rs.getInt("StateId"));
		l.setStateAbbr(rs.getString("StateAbbr"));
		l.setStateName(rs.getString("StateName"));
		l.setRegionId(rs.getInt("RegionId"));
		l.setRegionName(rs.getString("RegionName"));
		l.setCountryId(rs.getInt("CountryId"));
		l.setCountryName(rs.getString("CountryName"));
		return l;
	}
	
}