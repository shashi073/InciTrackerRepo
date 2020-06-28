package com.genpact.IncidentTracker.Util;

public class Key {
	
	public double latitude;
	public double longitude;
	
	public Key(double lati, double longi) {
		this.latitude = lati;
		this.longitude = longi;
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
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public boolean equals(Key key) {
		return key.getLatitude() == this.latitude && key.getLongitude() == this.longitude;
	}
	
	@Override
	public int hashCode() {
		return (int) (this.getLatitude() + this.getLongitude());
	}
	
}
