package com.genpact.IncidentTracker.model;

import java.util.ArrayList;
import java.util.List;

public class LiveIncidentCommentsAndFiles {
	 
	public int incidentId;
	public List<String> incidentImageUrls;
	public List<String> incidentVideoUrls;
	public List<Comment> comments;
	
	public LiveIncidentCommentsAndFiles() {
		this.incidentImageUrls = new ArrayList<String>();
		this.incidentVideoUrls = new ArrayList<String>();
		this.comments = new ArrayList<Comment>();
	}

	public int getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(int incidentId) {
		this.incidentId = incidentId;
	}

	public List<String> getIncidentImageUrls() {
		return incidentImageUrls;
	}

	public void setIncidentImageUrls(List<String> incidentImageUrls) {
		this.incidentImageUrls = incidentImageUrls;
	}

	public List<String> getIncidentVideoUrls() {
		return incidentVideoUrls;
	}

	public void setIncidentVideoUrls(List<String> incidentVideoUrls) {
		this.incidentVideoUrls = incidentVideoUrls;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	

}
