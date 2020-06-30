package com.genpact.IncidentTracker.model;

public class Offense {
	
  public int offenseId;
  public String offenseName;
  public String description;
  public int count;
  public String value;
  
  
	public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
