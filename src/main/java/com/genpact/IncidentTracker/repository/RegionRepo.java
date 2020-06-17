package com.genpact.IncidentTracker.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.genpact.IncidentTracker.Mapper.RegionMapper;
import com.genpact.IncidentTracker.model.Country;
import com.genpact.IncidentTracker.model.Region;

@Repository
public class RegionRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addRegion(Region region) {
		 String insertQuery = "Insert into Region(RegionCode,RegionName,RegionDesc,CountryId) Values(?,?,?,?)";
		 jdbcTemplate.update(insertQuery,new Object[] {region.getRegionCode(),region.getRegionName(),region.getRegionDesc(),region.getCountryId()});
	}

	public List<Region> getRegions() {
		String selectQuery = "Select r.RegionId,r.RegionCode,r.RegionName,r.RegionDesc,c.CountryId,c.CountryName from Region r join Country c on r.CountryId=c.CountryId";
		List<Region> regions = jdbcTemplate.query(selectQuery, new RegionMapper());
		return regions;
	}
}
