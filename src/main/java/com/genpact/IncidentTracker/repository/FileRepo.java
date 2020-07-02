package com.genpact.IncidentTracker.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.genpact.IncidentTracker.Mapper.ImageMapper;
import com.genpact.IncidentTracker.Mapper.VideoMapper;
import com.genpact.IncidentTracker.model.ImageFile;
import com.genpact.IncidentTracker.model.VideoFile;

@Repository
public class FileRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addVideo(VideoFile videoFile) {
		 String insertQuery = "Insert into VideoData(IncidentId,VideoName,VideoDescription,VideoData) Values(?,?,?,?)";
		 jdbcTemplate.update(insertQuery,new Object[] {videoFile.getIncidentId(),videoFile.getVideoName(),
				 videoFile.getVideoDescription(),videoFile.getVideoData()});
	}

	public List<VideoFile> getVideos(int incidentId) {
		String selectQuery = "Select VideoId,IncidentId,VideoName,VideoDescription,VideoData"
				+ " from VideoData where IncidentId=?";
		List<VideoFile> vFiles = jdbcTemplate.query(selectQuery,new Object[] {incidentId}, new VideoMapper());
		return vFiles;
	}

	public void addImage(ImageFile imageFile) {
		 String insertQuery = "Insert into ImageFile(IncidentId,ImageName,ImageDescription,ImageData) Values(?,?,?,?)";
		 jdbcTemplate.update(insertQuery,new Object[] {imageFile.getIncidentId(),imageFile.getImageName(),
				 imageFile.getImageDescription(),imageFile.getImageData()});
	}

	public List<ImageFile> getImages(int incidentId) {
		String selectQuery = "Select ImageId,IncidentId,ImageName,ImageDescription,ImageData"
				+ " from ImageFile where IncidentId=?";
		List<ImageFile> iFiles = jdbcTemplate.query(selectQuery,new Object[] {incidentId}, new ImageMapper());
		return iFiles;
	}
	
}
