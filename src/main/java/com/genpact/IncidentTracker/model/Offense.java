package com.genpact.IncidentTracker.model;

public class Offense {
	
  public int offenseId;
  public String offenseName;
  public String value;
  
	public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
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
