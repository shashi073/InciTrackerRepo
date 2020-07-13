package com.genpact.IncidentTracker.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
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
	private TempRepository tRepo;
	
	private List<Double> latLngManipulatorList = new ArrayList <Double>();
	
	public void prepareAndInsertData(int startIndex) {
		createLatLngManipulatorList();
		List<Locality> localityList = tRepo.getLocalities(startIndex);
		
		int count =0;
		for(Locality l : localityList) {
			System.out.println("Getting data Loaclity-" + ++count);
			List<AddIncidentRequest> iList = new ArrayList<AddIncidentRequest>();
			int incidentId = tRepo.getIncidentId(l.getLatitude(), l.getLongitude());
			if(incidentId==0) {
				System.out.println("Preparing data Loaclity-" +count);
				getDataForDays(iList,l,0,8,10);
				getDataForDays(iList,l,8,31,15);
				getDataForDays(iList,l,31,90,20);
				getDataForDays(iList,l,90,180,25);
				getDataForDays(iList,l,180,366,30);
			}else {
				System.out.println("Preparing data Loaclity skipped-" +count);
			}
			System.out.println("Data Prepared for Loaclity-" +count+"-"+iList.size());
			for(int i =0 ; i<iList.size() ; i++) {
				System.out.println("Processing Incident Count -" + (i+1) +"for locality -"+count);
				tRepo.addOrUpdateIncident(iList.get(i), l.getLocalityId());
			}
		}
		
		
  		
		
	}
	
	private void getDataForDays(List<AddIncidentRequest> iList, Locality l, int dateStart, int dateEnd , int noOfIncidents ) {
		
		AddIncidentRequest obj = new AddIncidentRequest();
		obj.setOffenseId(getOffenseId());
		obj.setLocalityName(l.getLocalityName());
		obj.setArea(l.getArea());
		obj.setDivision(l.getDivision());
		obj.setLatitude(l.getLatitude());
		obj.setLongitude(l.getLongitude());
		obj.setStateName(l.getStateName());
		obj.setDescription("Test Data Insertion");
		obj.setIncidentDateTime(getIncidentDateTime(dateStart,dateEnd));
		iList.add(obj);
		for(int i = 0; i < noOfIncidents ; i++) {
			AddIncidentRequest obj1 = new AddIncidentRequest();
			obj1.setOffenseId(getOffenseId());
			obj1.setLocalityName(l.getLocalityName());
			obj1.setArea(l.getArea());
			obj1.setDivision(l.getDivision());
			obj1.setLatitude(getLatitude(l.getLatitude()));
			obj1.setLongitude(getLongitude(l.getLongitude()));
			obj1.setStateName(l.getStateName());
			obj1.setDescription("Test Data Insertion");
			obj1.setIncidentDateTime(getIncidentDateTime(dateStart,dateEnd));
			iList.add(obj1);
		}
	}

	
	


	
	
	 
	private int getOffenseId() {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 11);
		return randomNum;
	}
	private LocalDateTime getIncidentDateTime(int minDays , int maxDays) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		int randomNum = ThreadLocalRandom.current().nextInt(minDays, maxDays);
		LocalDateTime generatedDateTime = currentDateTime.minusDays(randomNum);
		return generatedDateTime;
	}
	private double getLatitude(double actualLatitude) {
		double generatedLatitude;
		int randomNum = ThreadLocalRandom.current().nextInt(0, 10);
		generatedLatitude = actualLatitude + this.latLngManipulatorList.get(randomNum);
		return generatedLatitude;
	}
	private double getLongitude(double actualLongitude) {
		double generatedLongitude;
		int randomNum = ThreadLocalRandom.current().nextInt(0, 10);
		generatedLongitude = actualLongitude + this.latLngManipulatorList.get(randomNum);
		return generatedLongitude;
	}
	private void createLatLngManipulatorList() {
		this.latLngManipulatorList.add(0.0048);
		this.latLngManipulatorList.add(0.0042);
		this.latLngManipulatorList.add(0.0033);
		this.latLngManipulatorList.add(0.0022);
		this.latLngManipulatorList.add(0.0015);
		this.latLngManipulatorList.add(0.0007);
		this.latLngManipulatorList.add(-0.0012);
		this.latLngManipulatorList.add(-0.0023);
		this.latLngManipulatorList.add(-0.0031);
		this.latLngManipulatorList.add(-0.0038);
		this.latLngManipulatorList.add(-0.0042);
		this.latLngManipulatorList.add(-0.0049);
		
	}
	
	
}
