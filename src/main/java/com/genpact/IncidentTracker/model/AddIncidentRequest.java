package com.genpact.IncidentTracker.model;

import java.time.LocalDateTime;

public class AddIncidentRequest {

	
	public int offenseId;
	public String localityName;
	public String area;
	public String division;
	public double latitude;
	public double longitude;
	public String stateName;
	public String description;
	public LocalDateTime incidentDateTime;
	
	
	public LocalDateTime getIncidentDateTime() {
		return incidentDateTime;
	}
	public void setIncidentDateTime(LocalDateTime incidentDateTime) {
		this.incidentDateTime = incidentDateTime;
	}
	public int getOffenseId() {
		return offenseId;
	}
	public void setOffenseId(int offenseId) {
		this.offenseId = offenseId;
	}
	public String getLocalityName() {
		return localityName;
	}
	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
