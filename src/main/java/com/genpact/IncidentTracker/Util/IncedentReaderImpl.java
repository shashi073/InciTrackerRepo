package com.genpact.IncidentTracker.Util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.model.AggregatedIncident;
import com.genpact.IncidentTracker.model.LiveIncident;
import com.genpact.IncidentTracker.model.Offense;

@Service
public class IncedentReaderImpl {
		
		
	public List<AggregatedIncident> readData(String ori,int localityId, List<Offense> offenses) {
		List<AggregatedIncident> incidents = new ArrayList<AggregatedIncident>();
			try {
			  String preUrl ="https://api.usa.gov/crime/fbi/sapi/api/summarized/agencies/";
			  String postUrl="/offenses/2018/2018?API_KEY=dYjQZYmnzBQuzn506C4ads4ECn4b0yapmXxn8BDQ";
			  String url = preUrl+ori+postUrl;
			  URL obj = new URL(url);
			  HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			  int responseCode = con.getResponseCode();
			  System.out.println("\nSending 'GET' request to URL : " + url);
			  System.out.println("Response Code : " + responseCode);
			  BufferedReader in =new BufferedReader(
			  new InputStreamReader(con.getInputStream()));
			  String inputLine;
			  String response = "";
			   while ((inputLine = in.readLine()) != null) {
			     response = response +inputLine;
			   } in .close();
			   System.out.println(response);
			   JSONParser parser = new JSONParser(); 
			   JSONObject jsonObject = (JSONObject) parser.parse(response);
			   JSONArray employeeList = (JSONArray) jsonObject.get("results");
			   
			   
			   //Iterate over employee array
			   employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ,localityId,offenses,incidents) );
			  } catch(Exception e) {
			    System.out.println(e);
			  }
		return incidents;
	 }
	
	private static void parseEmployeeObject(JSONObject employee, int localityId, List<Offense> offenses, List<AggregatedIncident> incidents) 
	{
	   
		if(employee.get("data_year")!=null && employee.get("offense") != null 
				&& employee.get("actual")!=null) {
			AggregatedIncident inc=new AggregatedIncident();
			inc.setIncidentYear(((Long) employee.get("data_year")).toString());
			inc.setCount(((Long) employee.get("actual")).intValue());
			inc.setOffenceName((String) employee.get("offense"));
			inc.setLocalityId(localityId);
			getOffense(inc,  offenses);
			if(inc.getLocalityId()>0 && inc.getOffenseId()>0 && inc.getCount()>0) {
				incidents.add(inc);
			}
			
		}
	   
	    
	    
	}
	private static void getOffense(AggregatedIncident inc,   List<Offense> offenses) 
	{
	   
		for(Offense o: offenses) {
			if(o.getOffenseName().equalsIgnoreCase(inc.getOffenceName())) {
				inc.setOffenseId(o.getOffenseId());
				break;
			}
		}
	    
	    
	}
}