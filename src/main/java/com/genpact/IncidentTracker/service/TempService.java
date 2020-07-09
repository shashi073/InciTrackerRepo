package com.genpact.IncidentTracker.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.model.AddIncidentRequest;
import com.genpact.IncidentTracker.model.Locality;
import com.genpact.IncidentTracker.repository.LocalityRepo;
import com.genpact.IncidentTracker.repository.StateRepo;
import com.genpact.IncidentTracker.repository.TempRepository;

@Service
public class TempService {
	@Autowired
	private StateRepo sRepo;
	@Autowired
	private LocalityRepo lRepo;
	@Autowired
	private TempRepository tRepo;
	
	public void prepareAndInsertData() {
		List<AddIncidentRequest> iList = new ArrayList<AddIncidentRequest>();
		getDataPart1(iList);
		getDataPart2(iList);
		getDataPart3(iList);
		getDataPart4(iList);
		getDataPart5(iList);
		iList.forEach(incident-> addIncident(incident));
	}
	
	public String addIncident(AddIncidentRequest inc) {
		 String status = "Incident Updated Succesfully";
		 int stateId =sRepo.updateStateId(inc);
	   
	     if(stateId<=0) {
	    	 status = "State Details Not Found";
	     }
	     else {
	    		 Locality l= new Locality();
	    		 l.setOri("ManualIncident");
	    		 l.setLocalityName(inc.getLocalityName());
	    		 l.setArea(inc.getArea());
	    		 l.setDivision(inc.getDivision());
	    		 l.setLatitude(inc.getLatitude());
	    		 l.setLongitude(inc.getLongitude());
	    		 l.setStateId(stateId);
	    		 l.setStateName(inc.getStateName());
	    		 int lId=lRepo.addOrUpdateLocalityAndGetId(l);
	    		 
	    		 if(lId<=0) {
	    			 status = "Locality Details not found"; 
	    		 }else {
	    			 
	    			 if(inc.getOffenseId()<=0) {
	    				 status = "Offence Details Not Found";
	    			 }else {
	    				status = String.valueOf(tRepo.addOrUpdateIncident(inc, lId));
	    			 }
	    		 }
	    	 
	     }
		return status;
	}
	
	private AddIncidentRequest getAddIncidentRequestData(double latitude, double longitude, String dateTime, int offenseId) {
		AddIncidentRequest obj = new AddIncidentRequest();
		obj.setOffenseId(offenseId);
		obj.setLocalityName("Tiljala");
		obj.setArea("Picnic Garden");
		obj.setDivision("Ballygunge");
		obj.setLatitude(latitude);
		obj.setLongitude(longitude);
		obj.setStateName("West Bengal");
		obj.setDescription("Test Data Insertion");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		LocalDateTime incidentDateTime = LocalDateTime.parse(dateTime, formatter);
		obj.setIncidentDateTime(incidentDateTime);
		return obj;
	}
	private void getDataPart1(List<AddIncidentRequest> iList) {
		AddIncidentRequest i1 = getAddIncidentRequestData(22.524291,88.479410, "2020-07-10 11:30:30",1);
		iList.add(i1);
		AddIncidentRequest i2 = getAddIncidentRequestData(22.174291,88.079410, "2020-07-07 11:30:30",2);
		iList.add(i2);
		AddIncidentRequest i3 = getAddIncidentRequestData(22.224291,88.199410, "2020-07-08 11:30:30",2);
		iList.add(i3);
		AddIncidentRequest i4 = getAddIncidentRequestData(22.334291,88.279410, "2020-07-10 11:30:30",4);
		iList.add(i4);
		AddIncidentRequest i5 = getAddIncidentRequestData(22.475291,88.379410, "2020-07-07 11:30:30",6);
		iList.add(i5);
		AddIncidentRequest i6 = getAddIncidentRequestData(22.674291,88.589410, "2020-07-08 11:30:30",8);
		iList.add(i6);
		AddIncidentRequest i7 = getAddIncidentRequestData(22.724291,88.739410, "2020-07-08 11:30:30",2);
		iList.add(i7);
		AddIncidentRequest i8 = getAddIncidentRequestData(22.274291,88.629410, "2020-07-09 11:30:30",8);
		iList.add(i8);
		AddIncidentRequest i9 = getAddIncidentRequestData(22.594291,88.779410, "2020-07-07 11:30:30",4);
		iList.add(i9);
		AddIncidentRequest i10 = getAddIncidentRequestData(22.934291,88.829410, "2020-07-10 11:30:30",4);
		iList.add(i10);
	}
	private void getDataPart2(List<AddIncidentRequest> iList) {
		AddIncidentRequest i1 = getAddIncidentRequestData(22.524291,88.479410, "2020-06-25 11:30:30",1);
		iList.add(i1);
		AddIncidentRequest i2 = getAddIncidentRequestData(22.174291,88.079410, "2020-06-26 11:30:30",2);
		iList.add(i2);
		AddIncidentRequest i3 = getAddIncidentRequestData(22.224291,88.199410, "2020-06-27 11:30:30",2);
		iList.add(i3);
		AddIncidentRequest i4 = getAddIncidentRequestData(22.334291,88.279410, "2020-06-28 11:30:30",4);
		iList.add(i4);
		AddIncidentRequest i5 = getAddIncidentRequestData(22.475291,88.379410, "2020-06-24 11:30:30",6);
		iList.add(i5);
		AddIncidentRequest i6 = getAddIncidentRequestData(22.674291,88.589410, "2020-06-22 11:30:30",8);
		iList.add(i6);
		AddIncidentRequest i7 = getAddIncidentRequestData(22.724291,88.739410, "2020-06-28 11:30:30",2);
		iList.add(i7);
		AddIncidentRequest i8 = getAddIncidentRequestData(22.274291,88.629410, "2020-06-18 11:30:30",8);
		iList.add(i8);
		AddIncidentRequest i9 = getAddIncidentRequestData(22.594291,88.779410, "2020-06-17 11:30:30",4);
		iList.add(i9);
		AddIncidentRequest i10 = getAddIncidentRequestData(22.934291,88.829410, "2020-06-23 11:30:30",4);
		iList.add(i10);
		AddIncidentRequest i11 = getAddIncidentRequestData(22.524291,88.479410, "2020-06-25 11:30:30",7);
		iList.add(i11);
		AddIncidentRequest i12 = getAddIncidentRequestData(22.174291,88.079410, "2020-06-15 11:30:30",7);
		iList.add(i12);
		AddIncidentRequest i13 = getAddIncidentRequestData(22.224291,88.199410, "2020-06-17 11:30:30",7);
		iList.add(i13);
		AddIncidentRequest i14 = getAddIncidentRequestData(22.334291,88.279410, "2020-06-19 11:30:30",9);
		iList.add(i14);
		AddIncidentRequest i15 = getAddIncidentRequestData(22.475291,88.379410, "2020-06-21 11:30:30",9);
		iList.add(i15);
		AddIncidentRequest i16 = getAddIncidentRequestData(22.674291,88.589410, "2020-06-22 11:30:30",3);
		iList.add(i16);
		AddIncidentRequest i17 = getAddIncidentRequestData(22.724291,88.739410, "2020-06-28 11:30:30",3);
		iList.add(i17);
		AddIncidentRequest i18 = getAddIncidentRequestData(22.274291,88.629410, "2020-06-18 11:30:30",8);
		iList.add(i18);
		AddIncidentRequest i19 = getAddIncidentRequestData(22.594291,88.779410, "2020-06-17 11:30:30",10);
		iList.add(i19);
		AddIncidentRequest i20 = getAddIncidentRequestData(22.934291,88.829410, "2020-06-23 11:30:30",11);
		iList.add(i20);
	}

	

	private void getDataPart3(List<AddIncidentRequest> iList) {
		AddIncidentRequest i1 = getAddIncidentRequestData(22.524291,88.479410, "2020-05-25 11:30:30",1);
		iList.add(i1);
		AddIncidentRequest i2 = getAddIncidentRequestData(22.174291,88.079410, "2020-05-26 11:30:30",2);
		iList.add(i2);
		AddIncidentRequest i3 = getAddIncidentRequestData(22.224291,88.199410, "2020-05-27 11:30:30",2);
		iList.add(i3);
		AddIncidentRequest i4 = getAddIncidentRequestData(22.334291,88.279410, "2020-05-28 11:30:30",4);
		iList.add(i4);
		AddIncidentRequest i5 = getAddIncidentRequestData(22.475291,88.379410, "2020-05-24 11:30:30",6);
		iList.add(i5);
		AddIncidentRequest i6 = getAddIncidentRequestData(22.674291,88.589410, "2020-05-22 11:30:30",8);
		iList.add(i6);
		AddIncidentRequest i7 = getAddIncidentRequestData(22.724291,88.739410, "2020-05-28 11:30:30",2);
		iList.add(i7);
		AddIncidentRequest i8 = getAddIncidentRequestData(22.274291,88.629410, "2020-05-18 11:30:30",8);
		iList.add(i8);
		AddIncidentRequest i9 = getAddIncidentRequestData(22.594291,88.779410, "2020-05-17 11:30:30",4);
		iList.add(i9);
		AddIncidentRequest i10 = getAddIncidentRequestData(22.934291,88.829410, "2020-05-23 11:30:30",4);
		iList.add(i10);
		AddIncidentRequest i11 = getAddIncidentRequestData(22.524291,88.479410, "2020-06-08 11:30:30",7);
		iList.add(i11);
		AddIncidentRequest i12 = getAddIncidentRequestData(22.174291,88.079410, "2020-06-04 11:30:30",7);
		iList.add(i12);
		AddIncidentRequest i13 = getAddIncidentRequestData(22.224291,88.199410, "2020-04-27 11:30:30",7);
		iList.add(i13);
		AddIncidentRequest i14 = getAddIncidentRequestData(22.334291,88.279410, "2020-04-28 11:30:30",9);
		iList.add(i14);
		AddIncidentRequest i15 = getAddIncidentRequestData(22.475291,88.379410, "2020-04-24 11:30:30",9);
		iList.add(i15);
		AddIncidentRequest i16 = getAddIncidentRequestData(22.674291,88.589410, "2020-04-22 11:30:30",3);
		iList.add(i16);
		AddIncidentRequest i17 = getAddIncidentRequestData(22.724291,88.739410, "2020-04-28 11:30:30",3);
		iList.add(i17);
		AddIncidentRequest i18 = getAddIncidentRequestData(22.274291,88.629410, "2020-05-18 11:30:30",8);
		iList.add(i18);
		AddIncidentRequest i19 = getAddIncidentRequestData(22.594291,88.779410, "2020-05-27 11:30:30",10);
		iList.add(i19);
		AddIncidentRequest i20 = getAddIncidentRequestData(22.934291,88.829410, "2020-05-23 11:30:30",11);
		iList.add(i20);
	}
	private void getDataPart4(List<AddIncidentRequest> iList) {
		AddIncidentRequest i1 = getAddIncidentRequestData(22.524291,88.479410, "2020-04-25 11:30:30",1);
		iList.add(i1);
		AddIncidentRequest i2 = getAddIncidentRequestData(22.174291,88.079410, "2020-04-26 11:30:30",2);
		iList.add(i2);
		AddIncidentRequest i3 = getAddIncidentRequestData(22.224291,88.199410, "2020-04-27 11:30:30",2);
		iList.add(i3);
		AddIncidentRequest i4 = getAddIncidentRequestData(22.334291,88.279410, "2020-04-28 11:30:30",4);
		iList.add(i4);
		AddIncidentRequest i5 = getAddIncidentRequestData(22.475291,88.379410, "2020-04-24 11:30:30",6);
		iList.add(i5);
		AddIncidentRequest i6 = getAddIncidentRequestData(22.674291,88.589410, "2020-04-22 11:30:30",8);
		iList.add(i6);
		AddIncidentRequest i7 = getAddIncidentRequestData(22.724291,88.739410, "2020-04-28 11:30:30",2);
		iList.add(i7);
		AddIncidentRequest i8 = getAddIncidentRequestData(22.274291,88.629410, "2020-04-18 11:30:30",8);
		iList.add(i8);
		AddIncidentRequest i9 = getAddIncidentRequestData(22.594291,88.779410, "2020-03-17 11:30:30",4);
		iList.add(i9);
		AddIncidentRequest i10 = getAddIncidentRequestData(22.934291,88.829410, "2020-03-23 11:30:30",4);
		iList.add(i10);
		AddIncidentRequest i11 = getAddIncidentRequestData(22.524291,88.479410, "2020-02-08 11:30:30",7);
		iList.add(i11);
		AddIncidentRequest i12 = getAddIncidentRequestData(22.174291,88.079410, "2020-03-04 11:30:30",7);
		iList.add(i12);
		AddIncidentRequest i13 = getAddIncidentRequestData(22.224291,88.199410, "2020-03-27 11:30:30",7);
		iList.add(i13);
		AddIncidentRequest i14 = getAddIncidentRequestData(22.334291,88.279410, "2020-03-28 11:30:30",9);
		iList.add(i14);
		AddIncidentRequest i15 = getAddIncidentRequestData(22.475291,88.379410, "2020-03-24 11:30:30",9);
		iList.add(i15);
		AddIncidentRequest i16 = getAddIncidentRequestData(22.674291,88.589410, "2020-03-22 11:30:30",3);
		iList.add(i16);
		AddIncidentRequest i17 = getAddIncidentRequestData(22.724291,88.739410, "2020-03-28 11:30:30",3);
		iList.add(i17);
		AddIncidentRequest i18 = getAddIncidentRequestData(22.274291,88.629410, "2020-03-18 11:30:30",8);
		iList.add(i18);
		AddIncidentRequest i19 = getAddIncidentRequestData(22.594291,88.779410, "2020-04-27 11:30:30",10);
		iList.add(i19);
		AddIncidentRequest i20 = getAddIncidentRequestData(22.934291,88.829410, "2020-03-23 11:30:30",11);
		iList.add(i20);
	}
	private void getDataPart5(List<AddIncidentRequest> iList) {
		AddIncidentRequest i1 = getAddIncidentRequestData(22.524291,88.479410, "2019-12-25 11:30:30",1);
		iList.add(i1);
		AddIncidentRequest i2 = getAddIncidentRequestData(22.174291,88.079410, "2019-11-26 11:30:30",2);
		iList.add(i2);
		AddIncidentRequest i3 = getAddIncidentRequestData(22.224291,88.199410, "2019-12-27 11:30:30",2);
		iList.add(i3);
		AddIncidentRequest i4 = getAddIncidentRequestData(22.334291,88.279410, "2019-11-28 11:30:30",4);
		iList.add(i4);
		AddIncidentRequest i5 = getAddIncidentRequestData(22.475291,88.379410, "2019-10-24 11:30:30",6);
		iList.add(i5);
		AddIncidentRequest i6 = getAddIncidentRequestData(22.674291,88.589410, "2019-11-22 11:30:30",8);
		iList.add(i6);
		AddIncidentRequest i7 = getAddIncidentRequestData(22.724291,88.739410, "2019-12-28 11:30:30",2);
		iList.add(i7);
		AddIncidentRequest i8 = getAddIncidentRequestData(22.274291,88.629410, "2019-10-18 11:30:30",8);
		iList.add(i8);
		AddIncidentRequest i9 = getAddIncidentRequestData(22.594291,88.779410, "2019-09-17 11:30:30",4);
		iList.add(i9);
		AddIncidentRequest i10 = getAddIncidentRequestData(22.934291,88.829410, "2019-09-23 11:30:30",4);
		iList.add(i10);
		AddIncidentRequest i11 = getAddIncidentRequestData(22.524291,88.479410, "2019-10-08 11:30:30",7);
		iList.add(i11);
		AddIncidentRequest i12 = getAddIncidentRequestData(22.174291,88.079410, "2019-09-04 11:30:30",7);
		iList.add(i12);
		AddIncidentRequest i13 = getAddIncidentRequestData(22.224291,88.199410, "2019-08-27 11:30:30",7);
		iList.add(i13);
		AddIncidentRequest i14 = getAddIncidentRequestData(22.334291,88.279410, "2019-10-28 11:30:30",9);
		iList.add(i14);
		AddIncidentRequest i15 = getAddIncidentRequestData(22.475291,88.379410, "2019-10-24 11:30:30",9);
		iList.add(i15);
		AddIncidentRequest i16 = getAddIncidentRequestData(22.674291,88.589410, "2019-11-22 11:30:30",3);
		iList.add(i16);
		AddIncidentRequest i17 = getAddIncidentRequestData(22.724291,88.739410, "2019-08-28 11:30:30",3);
		iList.add(i17);
		AddIncidentRequest i18 = getAddIncidentRequestData(22.274291,88.629410, "2019-10-18 11:30:30",8);
		iList.add(i18);
		AddIncidentRequest i19 = getAddIncidentRequestData(22.594291,88.779410, "2019-11-27 11:30:30",10);
		iList.add(i19);
		AddIncidentRequest i20 = getAddIncidentRequestData(22.934291,88.829410, "2019-08-23 11:30:30",11);
		iList.add(i20);
	}
	
}
