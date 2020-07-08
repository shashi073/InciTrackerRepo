package com.genpact.IncidentTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.genpact.IncidentTracker.model.FileDetails;
import com.genpact.IncidentTracker.repository.FileRepo;

@Service
public class FileService {
	@Autowired
	private FileRepo cRepo;

	public void addIncidentFile(FileDetails fDetails) {
				
		cRepo.addIncidentFile(fDetails);
	}

	public void addIncidentCommentFile(FileDetails fDetails) {
		
		cRepo.addIncidentCommentFile(fDetails);
	}
	
	/*
	 * public void addImage(ImageFile iFile) {
	 * 
	 * cRepo.addImage(iFile); }
	 * 
	 * public List<ImageFile> getImages(int incidentId) {
	 * 
	 * return cRepo.getImages(incidentId); }
	 */
}
