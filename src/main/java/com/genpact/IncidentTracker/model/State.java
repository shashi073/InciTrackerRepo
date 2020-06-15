package com.genpact.IncidentTracker.model;

public class State {
	public int stateId;
	public String stateName;
	public String stateAbbr;
	public Long regionCode;
	public int regionId;
	public Long fipsCode;
	
	 
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	
	
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getStateAbbr() {
		return stateAbbr;
	}
	public void setStateAbbr(String stateAbbr) {
		this.stateAbbr = stateAbbr;
	}
	public Long getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(Long regionCode) {
		this.regionCode = regionCode;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public Long getFipsCode() {
		return fipsCode;
	}
	public void setFipsCode(Long fipsCode) {
		this.fipsCode = fipsCode;
	}
	
	
}
