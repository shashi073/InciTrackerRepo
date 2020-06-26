package com.genpact.IncidentTracker.model;

public class Incident {
	
	

	public Incident() {
		
	}
	public int incidentId;
	public String incidentYear;
	public int localityId;
	public int count;
	public int offenseId;
	public String offenceName;
	public String localityName;
	public String area;
	public String division;
	public double latitude;
	public double longitude;
	public int stateId;
	public String stateName;
	public int regionId;
	public String regionName;
	public int countryId;
	public String countryName;
	
	public int getIncidentId() {
		return incidentId;
	}
	public void setIncidentId(int incidentId) {
		this.incidentId = incidentId;
	}
	
	public String getIncidentYear() {
		return incidentYear;
	}
	public void setIncidentYear(String incidentYear) {
		this.incidentYear = incidentYear;
	}
	public int getLocalityId() {
		return localityId;
	}
	public void setLocalityId(int localityId) {
		this.localityId = localityId;
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
	public int getOffenseId() {
		return offenseId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setOffenseId(int offenseId) {
		this.offenseId = offenseId;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
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
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getOffenceName() {
		return offenceName;
	}
	public void setOffenceName(String offenceName) {
		this.offenceName = offenceName;
	}
}
