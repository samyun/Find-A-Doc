package findadoc.storage;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.InputSource;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Contains player and score data to represent a score keeper game.
 */
public class findadocRunnerData {
	// names of the doctors
    private List<String> names;
    // doctor hospital affilation 
    private Map<String, String> affiliation;
    // doctor phone number
    private Map<String, String> phone;
    // maximum applicable duration of symptom in Amazon.Duration format. "None" if n/a
    private Map<String, String> maxDuration;
    // relevant areas of symptoms.
    private Map<String, ArrayList<String>> areas;

    public findadocRunnerData() {
        // public no-arg constructor required for DynamoDBMapper marshalling
    }

    /**
     * Creates a new instance of {@link findadocRunnerData} with initialized and populated data from FHIR
     * 
     * @return
     */
    public static findadocRunnerData newInstance() {
        findadocRunnerData newInstance = new findadocRunnerData();
        
        ArrayList<String> names = new ArrayList<String>();
        TreeMap<String, String> affiliation = new TreeMap<String, String>(); // may need String.CASE_INSENSITIVE_ORDER in ()?
        TreeMap<String, String> phone = new TreeMap<String, String>();
        TreeMap<String, String> maxDuration = new TreeMap<String, String>();
        TreeMap<String, ArrayList<String>> areas = new TreeMap<String, ArrayList<String>>();
        
        populateDoctors(names, affiliation, phone, maxDuration, areas);
        
        newInstance.setNames(names);
        newInstance.setAffiliation(affiliation);
        newInstance.setPhone(phone);
        newInstance.setMaxDuration(maxDuration);
        newInstance.setAreas(areas);
        return newInstance;
    }

    public void repopulateDoctors() {
        ArrayList<String> names = new ArrayList<String>();
        TreeMap<String, String> affiliation = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        TreeMap<String, String> phone = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        TreeMap<String, String> maxDuration = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        TreeMap<String, ArrayList<String>> areas = new TreeMap<String, ArrayList<String>>(String.CASE_INSENSITIVE_ORDER);
        
        populateDoctors(names, affiliation, phone, maxDuration, areas);
        
        this.setNames(names);
        this.setAffiliation(affiliation);
        this.setPhone(phone);
        this.setMaxDuration(maxDuration);
        this.setAreas(areas);
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public Map<String, String> getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(Map<String, String> affiliation) {

        this.affiliation = affiliation;
    }

    public Map<String, String> getPhone() {
        return phone;
    }

    public void setPhone(Map<String, String> phone) {
        this.phone = phone;
    }

    public Map<String, String> getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(Map<String, String> maxDuration) {
        this.maxDuration = maxDuration;
    }

    public Map<String, ArrayList<String>> getAreas() {
        return areas;
    }

    public void setAreas(Map<String, ArrayList<String>> areas) {
        this.areas = areas;
    }
    
    public static void populateDoctors(ArrayList<String> names, TreeMap<String, String> affiliation, TreeMap<String, String> phone, 
    		TreeMap<String, String> maxDuration, TreeMap<String, ArrayList<String>> areas)
    {
    	// Instantiate repeated variables
    	String docName = "Unknown";
    	ArrayList<String> affectedAreas = new ArrayList<>();
    	String[] areaList = {"None"};
		
    	// copy and paste following for each doctor.
		docName = "Dr. Jenny Smith";
		names.add(docName);
		affiliation.put(docName, "Cleveland Clinic");
		phone.put(docName, "440-867-5309");
		maxDuration.put(docName, "None");	// None if no max duration of symptom
		affectedAreas = new ArrayList<>();
		areaList = new String[]{"arm", "leg", "body", "head"};
		specialty.put(docName, "Pain");
		for(String s : areaList)
		{
			affectedAreas.add(s);
		}
		areas.put(docName, affectedAreas);
		
		docName = "Dr. Mike Smith";
		names.add(docName);
		affiliation.put(docName, "The Ohio State Medical Center");
		phone.put(docName, "440-867-5310");
		maxDuration.put(docName, "None");	// None if no max duration of symptom
		affectedAreas = new ArrayList<>();
		areaList = new String[]{"none"};
		specialty.put(docName, "Sneeze");
		for(String s : areaList)
		{
			affectedAreas.add(s);
		}
		areas.put(docName, affectedAreas);
		
		docName = "Dr. Kate Lee";
		names.add(docName);
		affiliation.put(docName, "University Hospital Geneva Medical Center");
		phone.put(docName, "440-867-5311");
		maxDuration.put(docName, "None");	// None if no max duration of symptom
		affectedAreas = new ArrayList<>();
		areaList = new String[]{"None"};
		specialty.put(docName, "Sleep");
		for(String s : areaList)
		{
			affectedAreas.add(s);
		}
		areas.put(docName, affectedAreas);
		
		docName = "Dr. Andrew Wells";
		names.add(docName);
		affiliation.put(docName, "Ohio Department of Mental Health");
		phone.put(docName, "440-867-5312");
		maxDuration.put(docName, "None");	// None if no max duration of symptom
		affectedAreas = new ArrayList<>();
		areaList = new String[]{"None"};
		specialty.put(docName, "Emotion");
		for(String s : areaList)
		{
			affectedAreas.add(s);
		}
		areas.put(docName, affectedAreas);
		
		docName = "Dr. Steve Jones";
		names.add(docName);
		affiliation.put(docName, "Columbus City Dental Clinic");
		phone.put(docName, "440-867-5313");
		maxDuration.put(docName, "None");	// None if no max duration of symptom
		affectedAreas = new ArrayList<>();
		areaList = new String[]{"None"};
		specialty.put(docName, "Tooth");
		for(String s : areaList)
		{
			affectedAreas.add(s);
		}
		areas.put(docName, affectedAreas);
		
		docName = "Dr. James Falcon";
		names.add(docName);
		affiliation.put(docName, "Southeastern Ohio Regional Medical Center");
		phone.put(docName, "440-867-5314");
		maxDuration.put(docName, "None");	// None if no max duration of symptom
		affectedAreas = new ArrayList<>();
		areaList = new String[]{"None"};
		specialty.put(docName, "Vision");
		for(String s : areaList)
		{
			affectedAreas.add(s);
		}
		areas.put(docName, affectedAreas);
		
		docName = "Dr. Arthur Jobs";
		names.add(docName);
		affiliation.put(docName, "Ohio State University Wexner Medical Center");
		phone.put(docName, "440-867-5315");
		maxDuration.put(docName, "None");	// None if no max duration of symptom
		affectedAreas = new ArrayList<>();
		areaList = new String[]{"arm", "leg", "body", "head"};
		specialty.put(docName, "Arthritis");
		for(String s : areaList)
		{
			affectedAreas.add(s);
		}
		areas.put(docName, affectedAreas);
		
		docName = "Dr. John	Dell";
		names.add(docName);
		affiliation.put(docName, "Ohio Regional Medical Center");
		phone.put(docName, "440-867-5316");
		maxDuration.put(docName, "6 months");	// None if no max duration of symptom
		affectedAreas = new ArrayList<>();
		areaList = new String[]{"arm", "leg", "body", "head"};
		specialty.put(docName, "JointPain");
		for(String s : areaList)
		{
			affectedAreas.add(s);
		}
		areas.put(docName, affectedAreas);
    }
}
