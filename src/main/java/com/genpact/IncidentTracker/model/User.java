package com.genpact.IncidentTracker.model;

public class User {
	
	public String userId;
	public String userName;
	public String password;
	public String emailId;
	public String mobileNo;
	public String otp;
	public String otpExpiryDateTime;
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getOtpExpiryDateTime() {
		return otpExpiryDateTime;
	}
	public void setOtpExpiryDateTime(String expiryDateTime) {
		this.otpExpiryDateTime = expiryDateTime;
	}
	
	
}
