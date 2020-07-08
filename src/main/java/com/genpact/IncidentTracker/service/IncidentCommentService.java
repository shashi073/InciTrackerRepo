package com.genpact.IncidentTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.model.Comment;
import com.genpact.IncidentTracker.repository.IncidentCommentRepo;

@Service
public class IncidentCommentService {
	@Autowired
	private IncidentCommentRepo iCRepo;

	public String addComments(Comment comment) {
		
		
		return iCRepo.addComment(comment);
		
	}

	 

}
