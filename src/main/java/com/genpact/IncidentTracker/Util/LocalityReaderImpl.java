package com.genpact.IncidentTracker.Util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.genpact.IncidentTracker.model.Locality;
import com.genpact.IncidentTracker.model.Region;
import com.genpact.IncidentTracker.model.State;

@Service
public class LocalityReaderImpl 
{
 
    @SuppressWarnings("unchecked")
	public List<Locality> readData(List<State> stateList) 
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
        return localityList;
    }
 
    private static void parseEmployeeObject(JSONObject employee,List<State> stateList,List<Locality> localityList) 
    {
        //Get employee object within list
    	if(employee.get("ori") != null && employee.get("agency_name")!=null
    	  && employee.get("state_abbr")!= null && employee.get("region_name") != null
    	  &&  employee.get("county_name")!=null  && employee.get("latitude") != null 
    	  && employee.get("longitude") !=null  && employee.get("division_name") != null) {
    		
    		Locality lt = new Locality();
    		lt.setOri((String) employee.get("ori"));
    		lt.setLocalityName((String) employee.get("agency_name"));
    		lt.setStateName((String) employee.get("state_name"));
    		lt.setStateAbbr((String) employee.get("state_abbr"));
    		getState(lt, stateList);
    		lt.setRegionName((String) employee.get("region_name"));
    		lt.setArea((String) employee.get("county_name"));
    		lt.setDivision((String) employee.get("division_name"));
    		lt.setLatitude((double) employee.get("latitude"));
    		lt.setLongitude((double) employee.get("longitude"));
    		if(lt.getStateId()>0) {
    			localityList.add(lt);
    		}
    		
    	}
        
    }
    
    private static void getState(Locality lt,  List<State> stateList) 
    {
       
    	for(State s: stateList) {
    		if(s.getStateAbbr().equalsIgnoreCase(lt.getStateAbbr())) {
    			lt.setStateId(s.getStateId());
    			break;
    		}
    	}
        
        
    }
}