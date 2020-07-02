package com.genpact.IncidentTracker.Mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.genpact.IncidentTracker.model.ImageFile;

public class ImageMapper implements RowMapper<ImageFile> 
{

	@Override
	public ImageFile mapRow(ResultSet rs, int rowNum) throws SQLException {
		ImageFile c = new ImageFile();
		c.setImageId(rs.getInt("ImageId"));
		c.setIncidentId(rs.getInt("IncidentId"));
		c.setImageName(rs.getString("ImageName"));
		c.setImageDescription(rs.getString("ImageDescription"));
		c.setImageData(rs.getBytes("ImageData"));
		return c;
	}
}
