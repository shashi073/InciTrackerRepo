
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
import com.genpact.IncidentTracker.model.Region;
import com.genpact.IncidentTracker.model.State;
 
public class StateReaderImpl 
{
 
    @SuppressWarnings("unchecked")
	public  void readData(List <Region> regionList) 
    {
    	List <State> stateList = new ArrayList<State>();
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("C:\\Work\\Data\\states.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            //JSONArray employeeList = (JSONArray) obj;
            
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray employeeList = (JSONArray) jsonObject.get("results");
            
             
            //Iterate over employee array
            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp, stateList, regionList ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
 
    private static void parseEmployeeObject(JSONObject employee,List <State> stateList, List<Region> regionList) 
    {
    	if(employee.get("state_id") != null && employee.get("state_name") != null 
    		&&  employee.get("state_abbr") != null && employee.get("region_code") != null
    		&& employee.get("state_fips_code") != null) {
    		
    		State st = new State();
    		st.setStateName((String) employee.get("state_name"));
    		st.setStateAbbr((String) employee.get("state_abbr"));
    		st.setRegionCode((Long) employee.get("region_code"));
    		st.setFipsCode((Long) employee.get("region_code"));
    		getRegion(st,regionList);
    		stateList.add(st);
    	}
        //Get employee object within list
        
    }
    private static void getRegion(State st,  List<Region> regionList) 
    {
       
    	for(Region r: regionList) {
    		if(r.getRegionCode() == st.getRegionCode()) {
    			st.setRegionId(r.getRegionId());
    			break;
    		}
    	}
        
        
    }
}