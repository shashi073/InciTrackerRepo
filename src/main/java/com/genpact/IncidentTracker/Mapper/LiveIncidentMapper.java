package com.genpact.IncidentTracker.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.genpact.IncidentTracker.model.LiveIncident;

public class LiveIncidentMapper implements RowMapper<LiveIncident> {

	@Override
	public LiveIncident mapRow(ResultSet rs, int rowNum) throws SQLException {
		LiveIncident i = new LiveIncident();
		
		i.setIncidentId(rs.getInt("IncidentId"));
		i.setDescription(rs.getString("Description"));
		i.setCreatedDate(rs.getString("CreatedDate"));
		i.setOffenseId(rs.getInt("OffenseId"));
		i.setOffenceName(rs.getString("OffenseName"));
		i.setLocalityId(rs.getInt("LocalityId"));
		i.setLocalityName(rs.getString("LocalityName"));
		i.setArea(rs.getString("Area"));
		i.setDivision(rs.getString("Division"));
		i.setLatitude(rs.getDouble("IncidentLatitude"));
		i.setLongitude(rs.getDouble("IncidentLongitude"));
		i.setStateId(rs.getInt("StateId"));
		i.setStateName(rs.getString("StateName"));
		i.setRegionId(rs.getInt("RegionId"));
		i.setRegionName(rs.getString("RegionName"));
		i.setCountryId(rs.getInt("CountryId"));
		i.setCountryName(rs.getString("CountryName"));
		i.setCoulourCode(getColourCode(i.getOffenceName()));
		return i;
	}

	private String getColourCode(String offenceName) {
		String colourCode="";
		if(offenceName.equalsIgnoreCase("robbery")) {
			colourCode="#B40404";
		}
		else if(offenceName.equalsIgnoreCase("rape")) {
			colourCode="#F6CECE";
		}
		else if(offenceName.equalsIgnoreCase("property-crime")) {
			colourCode="#FACC2E";
		}
		else if(offenceName.equalsIgnoreCase("motor-vehicle-theft")) {
			colourCode="#FFFF00";
		}
		else if(offenceName.equalsIgnoreCase("larceny")) {
			colourCode="#9FF781";
		}
		else if(offenceName.equalsIgnoreCase("human-trafficing")) {
			colourCode="#31B404";
		}
		else if(offenceName.equalsIgnoreCase("homicide")) {
			colourCode="#00FFFF";
		}
		else if(offenceName.equalsIgnoreCase("burglary")) {
			colourCode="#013ADF";
		}
		else if(offenceName.equalsIgnoreCase("arson")) {
			colourCode="#FA58D0";
		}
		else if(offenceName.equalsIgnoreCase("aggravated-assault")) {
			colourCode="#FE2E9A";
		}
		else if(offenceName.equalsIgnoreCase("violent-crime")) {
			colourCode="#DF013A";
		}
		return colourCode;
	}
	
}
