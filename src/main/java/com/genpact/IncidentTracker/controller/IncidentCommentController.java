package com.genpact.IncidentTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.IncidentTracker.model.Comment;
import com.genpact.IncidentTracker.model.LiveIncidentCommentsAndFiles;
import com.genpact.IncidentTracker.service.IncidentCommentService;
import com.genpact.IncidentTracker.service.SharedService;

@RestController
public class IncidentCommentController {

	@Autowired
	private IncidentCommentService iCService;
	
	@Autowired
	private SharedService sService;
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/addComments")
	public String addComments(@RequestBody Comment comment) {
		//Dont Use Data has been already updated
	   String commentId =  iCService.addComments(comment);
	   return commentId;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getLiveIncidentCommentsAndFiles")
	public LiveIncidentCommentsAndFiles getLiveIncidentCommentsAndFiles(@RequestParam int incidentId) {
	     return sService.getLiveIncidentCommentsAndFiles(incidentId);
	}
}
