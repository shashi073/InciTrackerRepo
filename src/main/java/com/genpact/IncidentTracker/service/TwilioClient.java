package com.genpact.IncidentTracker.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.model.User;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioClient {


	@Value("${twilioAccountSid}")
	private String accountSid;
	@Value("${twilioAuthToken}")
	private String authToken;
	@Value("${twilioPhoneNo}")
	private String phoneNo;
	private static Map<String, User> otpUserMap= new HashMap<String,User>();
	
	
	@PostConstruct
	private void initializeTwilio() {
		 Twilio.init(accountSid, authToken);
	}

	public String validateOtp(User user) {
		   String response = "Failed";
		   User existingUser = otpUserMap.get(user.getMobileNo());
	       if(existingUser == null) {
	    	   response = "Mobile No Not Found";
	       }else {
	    	   String currentDateTime = ZonedDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	    	   if(existingUser.getOtpExpiryDateTime().compareTo(currentDateTime)<0) {
	    		   response = "Otp has Expired";
	    	   }
	    	   else {
	    		   response = "Verified Successfully";
	    	   }
	       }
	      return response;
		}

	public void sendOtp(User user) {
		 int randomNum = ThreadLocalRandom.current().nextInt(1000, 9999);
		   user.setOtp(String.valueOf(randomNum));
		   user.setOtpExpiryDateTime(ZonedDateTime.now(ZoneId.of("UTC")).plusSeconds(120)
				   .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		   Message.creator(new PhoneNumber(user.getMobileNo()), new PhoneNumber(phoneNo),
			                "Welcome to incident tracker Application. Your Otp is - " +user.getOtp()).create();
	       
	       otpUserMap.put(user.getMobileNo(), user);
		
	}

}