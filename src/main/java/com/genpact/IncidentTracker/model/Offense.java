package com.genpact.IncidentTracker.model;

public class Offense {
	
  public int offenseId;
  public String offenseName;
  
	public int getOffenseId() {
		return offenseId;
	}
	public void setOffenseId(int offenseId) {
		this.offenseId = offenseId;
	}
	public String getOffenseName() {
		return offenseName;
	}
	public void setOffenseName(String offenseName) {
		this.offenseName = offenseName;
	}
}
