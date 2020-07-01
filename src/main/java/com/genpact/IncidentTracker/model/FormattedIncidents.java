package com.genpact.IncidentTracker.model;

import java.util.ArrayList;
import java.util.List;

public class  FormattedIncidents {
	
	public FormattedIncidents() {
		this.offenseList = new ArrayList<Offense>();
		this.basicData = new FormattedIncidentBasicData();
		this.otherData = new FormattedIncidentOtherData();
	}

	
	public FormattedIncidentBasicData basicData;
	public FormattedIncidentOtherData otherData;
	public List<Offense> offenseList;
	public int incidentId;
	public String incidentYear;
	public int weight;
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
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
	public FormattedIncidentBasicData getBasicData() {
		return basicData;
	}
	public void setBasicData(FormattedIncidentBasicData basicData) {
		this.basicData = basicData;
	}
	public FormattedIncidentOtherData getOtherData() {
		return otherData;
	}
	public void setOtherData(FormattedIncidentOtherData otherData) {
		this.otherData = otherData;
	}
	public List<Offense> getOffenseList() {
		return offenseList;
	}
	public void setOffenseList(List<Offense> offenseList) {
		this.offenseList = offenseList;
	}
	
	 
	
}

 

