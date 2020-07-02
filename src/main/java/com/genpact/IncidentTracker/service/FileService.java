package com.genpact.IncidentTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.model.ImageFile;
import com.genpact.IncidentTracker.model.VideoFile;
import com.genpact.IncidentTracker.repository.FileRepo;

@Service
public class FileService {
	@Autowired
	private FileRepo cRepo;

	public void addVideo(VideoFile vFile) {
		
		
		cRepo.addVideo(vFile);
		
	}

	public List<VideoFile> getVideo(int incidentId) {
		
		return cRepo.getVideos(incidentId);
	} 
	
	public void addImage(ImageFile iFile) {
		
		
		cRepo.addImage(iFile);
		
	}

	public List<ImageFile> getImages(int incidentId) {
		
		return cRepo.getImages(incidentId);
	} 

}
