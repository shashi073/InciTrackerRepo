package com.genpact.IncidentTracker.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.genpact.IncidentTracker.model.VideoFile;

public class VideoMapper implements RowMapper<VideoFile> 
{

	@Override
	public VideoFile mapRow(ResultSet rs, int rowNum) throws SQLException {
		VideoFile c = new VideoFile();
		c.setVideoId(rs.getInt("VideoId"));
		c.setIncidentId(rs.getInt("IncidentId"));
		c.setVideoName(rs.getString("VideoName"));
		c.setVideoDescription(rs.getString("VideoDescription"));
		c.setVideoData(rs.getBytes("VideoData"));
		return c;
	}
}
