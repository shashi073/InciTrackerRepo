package com.genpact.IncidentTracker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.model.Comment;
import com.genpact.IncidentTracker.model.FileDetails;
import com.genpact.IncidentTracker.model.LiveIncidentCommentsAndFiles;
import com.genpact.IncidentTracker.repository.FileRepo;
import com.genpact.IncidentTracker.repository.IncidentCommentRepo;

@Service
public class SharedService {

	@Autowired
	private IncidentCommentRepo icRepo;
	@Autowired
	private FileRepo fRepo;
	
	
	public LiveIncidentCommentsAndFiles getLiveIncidentCommentsAndFiles(int incidentId) {
		
		LiveIncidentCommentsAndFiles data = new LiveIncidentCommentsAndFiles();
		data.setIncidentId(incidentId);
		List<FileDetails> incidentFiles = fRepo.getIncidentFile(incidentId);
		List<Comment> comments = icRepo.getComments(incidentId);
		List<FileDetails> incidentCommentFiles = fRepo.getIncidentCommentFile(incidentId);
		LiveIncidentCommentsAndFiles details= new LiveIncidentCommentsAndFiles();
		details.setIncidentId(incidentId); 
		
		details.getIncidentImageUrls().addAll(incidentFiles.stream().filter(i->i.getFileType().
				contains("image")).map(i->i.getFileUrl()).collect(Collectors.toList()));
		details.getIncidentVideoUrls().addAll(incidentFiles.stream().filter(i->i.getFileType().
				contains("video")).map(i->i.getFileUrl()).collect(Collectors.toList()));
		for(Comment c : comments) {
			c.getIncidentCommentImageUrls().addAll(incidentCommentFiles.stream()
					.filter(i->i.getCommentId()==c.getCommentId() && i.getFileType().contains("image"))
					.map(i->i.getFileUrl()).collect(Collectors.toList()));
			c.getIncidentCommentVideoUrls().addAll(incidentCommentFiles.stream()
					.filter(i->i.getCommentId()==c.getCommentId() && i.getFileType().contains("video"))
					.map(i->i.getFileUrl()).collect(Collectors.toList()));
			
		}
		details.getComments().addAll(comments);
		return details;
	}
}
