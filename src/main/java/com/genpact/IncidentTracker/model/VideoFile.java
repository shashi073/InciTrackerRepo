package com.genpact.IncidentTracker.model;

public class VideoFile {

	public int videoId;
	public int incidentId;
	public String videoName;
	public String videoDescription;
	public byte[] videoData;
	public int getVideoId() {
		return videoId;
	}
	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}
	public int getIncidentId() {
		return incidentId;
	}
	public void setIncidentId(int incidentId) {
		this.incidentId = incidentId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getVideoDescription() {
		return videoDescription;
	}
	public void setVideoDescription(String videoDescription) {
		this.videoDescription = videoDescription;
	}
	public byte[] getVideoData() {
		return videoData;
	}
	public void setVideoData(byte[] videoData) {
		this.videoData = videoData;
	}
	
	
}
