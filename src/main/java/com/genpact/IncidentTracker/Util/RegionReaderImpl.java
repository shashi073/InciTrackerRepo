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

import com.genpact.IncidentTracker.model.Country;
import com.genpact.IncidentTracker.model.Region;

@Service
public class RegionReaderImpl 
{
 
    @SuppressWarnings("unchecked")
	public List <Region> readData(List<Country> countryList) 
    {
    	List <Region> regionList = new ArrayList<Region>();
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("C:\\Work\\Data\\regions.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            //JSONArray employeeList = (JSONArray) obj;
            
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray employeeList = (JSONArray) jsonObject.get("results");
            
             
            //Iterate over employee array
            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ,regionList,countryList) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return regionList;
    }
 
    private static void parseEmployeeObject(JSONObject employee, List <Region> regionList, List<Country> countryList) 
    {
       
    	if(employee.get("region_code")!=null && employee.get("region_name") != null 
    			&& employee.get("region_desc")!=null) {
    		Region reg=new Region();
    		reg.setRegionCode((Long) employee.get("region_code"));
    		reg.setRegionName((String) employee.get("region_name"));
    		reg.setRegionDesc((String) employee.get("region_desc"));
    		getCountry(reg, "USA", countryList);
    		regionList.add(reg);
    	}
       
        
        
    }
    private static void getCountry(Region reg, String countryCode,  List<Country> countryList) 
    {
       
    	for(Country c: countryList) {
    		if(c.getCountryCode().equalsIgnoreCase(countryCode)) {
    			reg.setCountryId(c.getCountryId());
    			break;
    		}
    	}
        
        
    }
}