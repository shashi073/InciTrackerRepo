package com.genpact.IncidentTracker.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.genpact.IncidentTracker.model.Country;
import com.genpact.IncidentTracker.model.Region;

public class RegionMapper implements RowMapper<Region> {

	@Override
	public Region mapRow(ResultSet rs, int rowNum) throws SQLException {
		Region r = new Region();
		r.setRegionId(rs.getInt("RegionId"));
		r.setRegionCode(rs.getLong("RegionCode"));
		r.setRegionName(rs.getString("RegionName"));
		r.setRegionDesc(rs.getString("RegionDesc"));
		r.setCountryId(rs.getInt("CountryId"));
		r.setCountryName(rs.getString("CountryName"));
		return r;
	}
	
}
