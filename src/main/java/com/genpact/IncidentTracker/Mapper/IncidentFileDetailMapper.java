package com.genpact.IncidentTracker.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.genpact.IncidentTracker.model.FileDetails;

public class IncidentFileDetailMapper implements RowMapper<FileDetails> 
{

	@Override
	public FileDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		FileDetails c = new FileDetails();
		c.setFileId(rs.getInt("FileId"));
		c.setIncidentId(rs.getInt("IncidentId"));
		c.setFileType(rs.getString("FileType"));
		c.setFileUrl(rs.getString("FileUrl"));
		return c;
	}
}
