package com.genpact.IncidentTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.model.Comment;
import com.genpact.IncidentTracker.model.Country;
import com.genpact.IncidentTracker.repository.CommentRepo;

@Service
public class CommentService {
	@Autowired
	private CommentRepo cRepo;

	public void addComments(Comment comment) {
		
		
		cRepo.addComment(comment);
		
	}

	public List<Comment> getComments(int incidentId) {
		
		return cRepo.getComments(incidentId);
	} 

}
