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
