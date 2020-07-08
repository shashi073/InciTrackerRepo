package com.genpact.IncidentTracker.model;

public class AggregatedIncident {

	public String incidentYear;
	public int count;
	public int localityId;
	public String OffenceName;
	public int offenseId;
	
	
	
	public String getIncidentYear() {
		return incidentYear;
	}
	public void setIncidentYear(String incidentYear) {
		this.incidentYear = incidentYear;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getLocalityId() {
		return localityId;
	}
	public void setLocalityId(int localityId) {
		this.localityId = localityId;
	}
	public String getOffenceName() {
		return OffenceName;
	}
	public void setOffenceName(String offenceName) {
		OffenceName = offenceName;
	}
	public int getOffenseId() {
		return offenseId;
	}
	public void setOffenseId(int offenseId) {
		this.offenseId = offenseId;
	}
	
	
	
}
