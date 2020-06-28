package com.genpact.IncidentTracker.model;

public class OffenseData{
	
	public int offenseId;
	public String offenceName;
	public int count;
	public int getOffenseId() {
		return offenseId;
	}
	public void setOffenseId(int offenseId) {
		this.offenseId = offenseId;
	}
	public String getOffenceName() {
		return offenceName;
	}
	public void setOffenceName(String offenceName) {
		this.offenceName = offenceName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	 
}