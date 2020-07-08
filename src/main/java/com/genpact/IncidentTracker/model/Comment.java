package com.genpact.IncidentTracker.model;

import java.util.ArrayList;
import java.util.List;

public class Comment {
	public int commentId;
	public int incidentId;
	public String comments;
	public List<String> incidentCommentImageUrls;
	public List<String> incidentCommentVideoUrls;
	
	public Comment() {
		this.incidentCommentImageUrls = new ArrayList<String>();
		this.incidentCommentVideoUrls = new ArrayList<String>();
	}
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getIncidentId() {
		return incidentId;
	}
	public void setIncidentId(int incidentId) {
		this.incidentId = incidentId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<String> getIncidentCommentImageUrls() {
		return incidentCommentImageUrls;
	}

	public void setIncidentCommentImageUrls(List<String> incidentCommentImageUrls) {
		this.incidentCommentImageUrls = incidentCommentImageUrls;
	}

	public List<String> getIncidentCommentVideoUrls() {
		return incidentCommentVideoUrls;
	}

	public void setIncidentCommentVideoUrls(List<String> incidentCommentVideoUrls) {
		this.incidentCommentVideoUrls = incidentCommentVideoUrls;
	}
	
	
}
