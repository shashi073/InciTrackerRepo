package com.genpact.IncidentTracker.model;

public class Ticker {

	public String offenceName;
	public int count;
	public String coulourCode;
	
	
	public String getCoulourCode() {
		return coulourCode;
	}
	public void setCoulourCode(String coulourCode) {
		this.coulourCode = coulourCode;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getOffenceName() {
		return offenceName;
	}
	public void setOffenceName(String offenseName) {
		this.offenceName = offenseName;
	}
	
	
}
