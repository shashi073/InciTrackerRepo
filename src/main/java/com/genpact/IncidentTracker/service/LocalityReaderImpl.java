package com.genpact.IncidentTracker.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.genpact.IncidentTracker.model.Locality;
import com.genpact.IncidentTracker.model.State;
 
public class LocalityReaderImpl 
{
 
    @SuppressWarnings("unchecked")
	public static void readData(List<State> stateList) 
    {
    	List<Locality> localityList = new ArrayList<Locality>();
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("C:\\Work\\Data\\agencies.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray employeeList = (JSONArray) obj;
            
           // JSONObject jsonObject = (JSONObject) obj;
            //JSONArray employeeList = (JSONArray) jsonObject.get("results");
            
             
            //Iterate over employee array
            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp, stateList, localityList ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
 
    private static void parseEmployeeObject(JSONObject employee,List<State> stateList,List<Locality> localityList) 
    {
        //Get employee object within list
    	if(employee.get("ori") != null && employee.get("agency_name")!=null
    	  && employee.get("state_abbr")!= null && employee.get("region_name") != null
    	  && employee.get("region_desc") != null &&  employee.get("county_name")!=null
    	  && employee.get("latitude") != null && employee.get("longitude") !=null) {
    		
    	}
        String ori = (String) employee.get("ori");
        String agency_name = (String) employee.get("agency_name");
        String state_name = (String) employee.get("state_name");
        String state_abbr = (String) employee.get("state_abbr");
        String region_name = (String) employee.get("region_name"); 
        String region_desc = (String) employee.get("region_desc");
        String locality = (String) employee.get("county_name");
        double latitude = (double) employee.get("latitude");
        double longitude = (double) employee.get("longitude");
        
        System.out.println("Json Object--" + ori+"-"+agency_name+"-"+state_name+"-"+latitude+"-"+longitude);
    }
}