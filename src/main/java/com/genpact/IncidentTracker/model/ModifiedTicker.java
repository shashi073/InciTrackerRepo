package com.genpact.IncidentTracker.model;

public class ModifiedTicker {

	public int incidentId;
	public int offenseId;
	public String offenceName;
	
	public int getIncidentId() {
		return incidentId;
	}
	public void setIncidentId(int incidentId) {
		this.incidentId = incidentId;
	}
	public int getOffenseId() {
		return offenseId;
	}
	public void setOffenseId(int offenceId) {
		this.offenseId = offenceId;
	}
	public String getOffenceName() {
		return offenceName;
	}
	public void setOffenceName(String offenseName) {
		this.offenceName = offenseName;
	}
	
	
}
