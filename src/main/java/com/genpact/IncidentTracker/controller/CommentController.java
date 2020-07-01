package com.genpact.IncidentTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.IncidentTracker.model.Comment;
import com.genpact.IncidentTracker.model.Country;
import com.genpact.IncidentTracker.service.CommentService;
import com.genpact.IncidentTracker.service.CountryService;

@RestController
public class CommentController {

	@Autowired
	private CommentService cService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/addComments")
	public void addComments(@RequestBody Comment comment) {
		//Dont Use Data has been already updated
	     cService.addComments(comment);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getComments")
	public List<Comment> getComments(@RequestParam int incidentId) {
	     return cService.getComments(incidentId);
	}
}
