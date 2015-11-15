package findadoc.storage;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

import findadoc.SymptomType;

/**
 * Represents a findadoc instance.
 */
public final class findadocRunner {
    private Session session;
    private findadocRunnerData runnerData;

    private findadocRunner() {
    }

    /**
     * Creates a new instance of {@link findadocRunner} with the provided {@link Session} and
     * {@link findadocRunnerData}.
     * <p>
     * To create a new instance of {@link findadocRunnerData}, see
     * {@link findadocRunnerData#newInstance()}
     * 
     * @param session
     * @param runnerData
     * @return
     * @see findadocRunnerData#newInstance()
     */
    public static findadocRunner newInstance(Session session, findadocRunnerData runnerData) {
        findadocRunner runner = new findadocRunner();
        runner.setSession(session);
        runner.setRunnerData(runnerData);
        return runner;
    }

    protected void setSession(Session session) {
        this.session = session;
    }

    protected Session getSession() {
        return session;
    }

    protected findadocRunnerData getRunnerData() {
        return runnerData;
    }

    protected void setRunnerData(findadocRunnerData runnerData) {
        this.runnerData = runnerData;
    }
    

    public SpeechletResponse GetSpeechletResponse(SymptomType type, String time)
    {
    	SimpleCard card;
    	String docName;
    	switch (type)
    	{
    		case Pain:
    			docName = GetDocNameForPain();
    			break;
    		case Arthritis:
    			docName = GetDocNameForArthritis();
    			break;
    		case Sneeze:
    			docName = GetDocNameForSneeze();
    			break;
    		case Emotion:
    			docName = GetDocNameForEmotion();
    			break;
    		case JointPain:
    			docName = GetDocNameForJointPain();
    			break;
    		case Sleep:
    			docName = GetDocNameForSleep();
    			card = GetCard(type);
    			break;
    		case Tooth:
    			docName = GetDocNameForTooth();
    			break;
    		case Vision:
    			docName = GetDocNameForVision();
    			break;
    		default:	
    			docName = GetDocNameForGeneral();
    			break;
    	}
    	
    	
    }
    
    private SimpleCard GetCard(SymptomType type)
    {
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        
        docName = GetDocNameFromType(type);
        card.setTitle(docName);
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
        repromptSpeech.setText(repromptText);
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptSpeech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
    
    private String GetDocNameForPain()
    {
    	List<String> list = runnerData.getNames();
    	Map<String,String> map = runnerData.getSpecialty();
    	
    	for (String s : list)
    	{
    		String specialty = map.get(s);
    		if (specialty.equals("Pain"))
    			return s;
    	}
    	
    	return "Unknown";
    }
    
    private String GetDocNameForSneeze()
    {
    	List<String> list = runnerData.getNames();
    	Map<String,String> map = runnerData.getSpecialty();
    	
    	for (String s : list)
    	{
    		String specialty = map.get(s);
    		if (specialty.equals("Sneeze"))
    			return s;
    	}
    	
    	return "Unknown";
    }
    
    private String GetDocNameForSleep()
    {
    	List<String> list = runnerData.getNames();
    	Map<String,String> map = runnerData.getSpecialty();
    	
    	for (String s : list)
    	{
    		String specialty = map.get(s);
    		if (specialty.equals("Sleep"))
    			return s;
    	}
    	
    	return "Unknown";
    }
    
    private String GetDocNameForEmotion()
    {
    	List<String> list = runnerData.getNames();
    	Map<String,String> map = runnerData.getSpecialty();
    	
    	for (String s : list)
    	{
    		String specialty = map.get(s);
    		if (specialty.equals("Emotion"))
    			return s;
    	}
    	
    	return "Unknown";
    }
    
    private String GetDocNameForTooth()
    {    	
    	List<String> list = runnerData.getNames();
    	Map<String,String> map = runnerData.getSpecialty();
    	
    	for (String s : list)
    	{
    		String specialty = map.get(s);
    		if (specialty.equals("Tooth"))
    			return s;
    	}
    	
    	return "Unknown";
    }
    
    private String GetDocNameForVision()
    {
    	List<String> list = runnerData.getNames();
    	Map<String,String> map = runnerData.getSpecialty();
    	
    	for (String s : list)
    	{
    		String specialty = map.get(s);
    		if (specialty.equals("Vision"))
    			return s;
    	}
    	
    	return "Unknown";
    }
    
    private String GetDocNameForArthritis()
    {
    	List<String> list = runnerData.getNames();
    	Map<String,String> map = runnerData.getSpecialty();
    	
    	for (String s : list)
    	{
    		String specialty = map.get(s);
    		if (specialty.equals("Arthritis"))
    			return s;
    	}
    	
    	return "Unknown";
    }
    
    private String GetDocNameForJointPain()
    {
    	List<String> list = runnerData.getNames();
    	Map<String,String> map = runnerData.getSpecialty();
    	
    	for (String s : list)
    	{
    		String specialty = map.get(s);
    		if (specialty.equals("Joint Pain"))
    			return s;
    	}
    	
    	return "Unknown";
    }
    

    
    private String GetDocNameForGeneral()
    {
    	List<String> list = runnerData.getNames();
    	Map<String,String> map = runnerData.getSpecialty();
    	
    	for (String s : list)
    	{
    		String specialty = map.get(s);
    		if (specialty.equals("General"))
    			return s;
    	}
    	
    	return "Unknown";
    }

    /**
     * Returns the list containing the names of doctors
     * 
     * @return 
     */
    public List<String> getNames() {
        return runnerData.getNames();
    }

    /**
     * Returns true if the doctor has drug entries, false otherwise.
     * 
     * @return true if the doctor has drug entries, false otherwise
     */
    public boolean hasDrugEntries() {
        return !runnerData.getNames().isEmpty();
    }

    /**
     * Returns the number of drugs in the system.
     * 
     * @return the number of drugs in the system.
     */
    public int getNumberOfDrugsInSystem() {
        return runnerData.getNames().size();
    }

    /**
     * Add a drug to the doctor.
     * 
     * @param drugName
     *            Name of the drug
     */
    public void addDrugToSystem(String drugName) {
        runnerData.getNames().add(drugName);
    }

    /**
     * Returns true if the drug exists in the doctor, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @return true if the drug exists in the doctor, false otherwise
     */
    public boolean hasDrug(String drugName) {
        List<String> list = runnerData.getNames();
		for (String s : list)
		{
			if (s.equalsIgnoreCase(drugName))
				return true;
		}
		return false;
    }

    /**
     * Returns true if the doctor has dose left in period entries, false otherwise.
     * 
     * @return true if the doctor has dose left in period entries, false otherwise
     */
    public boolean hasNumDosesEntries() {
        return !runnerData.getNumDoses().isEmpty();
    }

    /**
     * Returns the number of doses left in the perscription period for a drug
     * 
     * @param drugName
     *            drug name
     * @return number of doses left in the perscription period
     */
    public long getDosesLeftInPeriodForDrug(String drugName) {
		Map<String, Long> map = runnerData.getNumDoses();
		for (Map.Entry<String, Long> pair : map.entrySet())
		{
			if (pair.getKey().equalsIgnoreCase(drugName))
				return pair.getValue();
		}
		return 0;
    }

    /**
     * Returns true if the numDoses entry for a drug exists in the doctor, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @return true if the drug exists in the doctor, false otherwise
     */
    public boolean hasNumDosesForDrug(String drugName) {
		Map<String, Long> map = runnerData.getNumDoses();
		for (Map.Entry<String, Long> pair : map.entrySet())
		{
			if (pair.getKey().equalsIgnoreCase(drugName))
				return true;
		}
		return false;
    }

    /**
     * Adds the doses passed to it to the current doses for a drug. Returns true if the drug
     * existed, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @param dose
     *            dose to be added
     * @return true if the drug existed, false otherwise.
     */
    private boolean addDosesLeftInPeriodForDrug(String drugName, long dose) {

        long currentDose = 0L;
        if (hasDrug(drugName) && hasNumDosesForDrug(drugName)) {
        	currentDose = getDosesLeftInPeriodForDrug(drugName);
			runnerData.getNumDoses().remove(drugName.toLowerCase());
			runnerData.getNumDoses().put(drugName.toLowerCase(), Long.valueOf(currentDose + dose));
			return true;
        }
        else{
        	return false;
        }
    }

    /**
     * Remove the doses passed to it from the current doses for a drug. Returns true if the drug
     * existed, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @param dose
     *            dose to be removed
     * @return true if the drug existed, false otherwise.
     */
    public boolean removeDosesLeftInPeriodForDrug(String drugName, long dose) {

        long currentDose = 0L;
        if (hasDrug(drugName) && hasNumDosesForDrug(drugName)) {
        	currentDose = getDosesLeftInPeriodForDrug(drugName);
			runnerData.getNumDoses().remove(drugName.toLowerCase());
            runnerData.getNumDoses().put(drugName.toLowerCase(), Long.valueOf(currentDose - dose));
            return true;
        }
        else{
        	return false;
        }
    }
    
    public boolean resetDosesLeftInPeriodForDrug(String drugName) {
    	if (hasDrug(drugName) && hasNumDosesForDrug(drugName)) {
			runnerData.getNumDoses().remove(drugName.toLowerCase());
            runnerData.getNumDoses().put(drugName.toLowerCase(), Long.valueOf(getSupplyForDrug(drugName)));
            return true;
        }
        else{
        	return false;
        }
    }

    /**
     * Returns true if the doctor has last dates taken entries, false otherwise.
     * 
     * @return true if the doctor has last dates taken entries, false otherwise
     */
    public boolean hasLastDatesEntries() {
        return !runnerData.getLastDates().isEmpty();
    }

    /**
     * Returns true if the lastDates entry for a drug exists in the doctor, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @return true if the drug exists in the doctor, false otherwise
     */
    public boolean hasLastDatesForDrug(String drugName) {
        return (getLastDatesForDrug(drugName) !=null);
    }

    /**
     * Returns the last date the drug was taken
     * 
     * @param drugName
     *            drug name
     * @return last date drug was taken
     */
    public Calendar getLastDatesForDrug(String drugName) {		
		Map<String, Calendar> map = runnerData.getLastDates();
		for (Map.Entry<String, Calendar> pair : map.entrySet())
		{
			if (pair.getKey().equalsIgnoreCase(drugName))
				return pair.getValue();
		}
		return null;
    }

    /**
     * Sets the last date drug was taken to now. Returns true if the drug
     * existed, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @return true if the drug existed, false otherwise.
     */
    public boolean setLastDateToToday(String drugName) {
    	if (hasDrug(drugName)) {
			runnerData.getLastDates().remove(drugName.toLowerCase());
            runnerData.getLastDates().put(drugName.toLowerCase(), Calendar.getInstance());
            return true;
        }
        else{
        	return false;
        }
    }

    /**
     * Compares last date a drug was used to today. Returns true if used today,
     * false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @return true if the drug was used today, false otherwise.
     */
    public boolean checkIfDrugUsedToday(String drugName) {

    	if (hasDrug(drugName) && hasLastDatesForDrug(drugName)) {
            Calendar lastUsed = getLastDatesForDrug(drugName);
            
            if (lastUsed == null)
            	return false;
            
            Calendar today = Calendar.getInstance();
            
            if (lastUsed.YEAR == today.YEAR && lastUsed.DAY_OF_YEAR == today.DAY_OF_YEAR)
            	return true;
            
            return false;
        }
        else{
        	return false;
        }
    }
    
    /**
     * If the drug was last taken prior to today, resync FHIR and reset
     * dayTotals.
     */
    public void resyncIfNewDay()
    {
    	boolean newDay = false;
    	List<String> names = runnerData.getNames();
    	if (names.size() < 1)
		{
    		newDay = true;
		}
    	else
    	{
        	for (String s : names)
        	{
        		if (!checkIfDrugUsedToday(s))
        		{
        			newDay = true;
        			break;
        		}
        	}
    	}

    	
    	if (newDay)
    	{
    		resyncFHIR();
    	}
    }
	
	/**
	 * Resyncs with the FHIR service.
	 */
	public void resyncFHIR()
	{
		runnerData.repopulateDoctors();
	}

    /**
     * Returns true if the doctor has day total entries, false otherwise.
     * 
     * @return true if the doctor has day total entries, false otherwise
     */
    public boolean hasDayTotalEntries() {
        return !runnerData.getDayTotals().isEmpty();
    }

    /**
     * Returns the number of doses taken in the day for a drug
     * 
     * @param drugName
     *            drug name
     * @return number of doses left in the perscription period
     */
    public long getDosesTakenTodayForDrug(String drugName) {
		Map<String, Long> map = runnerData.getDayTotals();
		for (Map.Entry<String, Long> pair : map.entrySet())
		{
			if (pair.getKey().equalsIgnoreCase(drugName))
				return pair.getValue();
		}
		return 0;
    }

    /**
     * Returns true if the dayTotals entry for a drug exists in the doctor, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @return true if the drug exists in the doctor, false otherwise
     */
    public boolean hasDayTotalsForDrug(String drugName) {
		Map<String, Long> map = runnerData.getDayTotals();
		for (Map.Entry<String, Long> pair : map.entrySet())
		{
			if (pair.getKey().equalsIgnoreCase(drugName))
				return true;
		}
		return false;
    }

    /**
     * Adds the doses passed to it to the current doses taken today for a drug. Returns true if the drug
     * existed, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @param dose
     *            dose to be added
     * @return true if the drug existed, false otherwise.
     */
    public boolean addDosesTakenTodayForDrug(String drugName, long dose) {

        long currentDose = 0L;
        if (hasDrug(drugName) && hasDayTotalsForDrug(drugName)) {
        	currentDose = getDosesTakenTodayForDrug(drugName);
			runnerData.getDayTotals().remove(drugName.toLowerCase());
            runnerData.getDayTotals().put(drugName.toLowerCase(), Long.valueOf(currentDose + dose));
            return true;
        }
        else{
        	return false;
        }
    }

    /**
     * Resets current doses taken today for a drug to 0. Returns true if the drug
     * existed, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @return true if the drug existed, false otherwise.
     */
    public boolean resetDosesTakenTodayForDrug(String drugName) {

        long currentDose = 0L;
        if (hasDrug(drugName) && hasDayTotalsForDrug(drugName)) {
			runnerData.getDayTotals().remove(drugName.toLowerCase());
        	runnerData.getDayTotals().put(drugName.toLowerCase(), null);
        	return true;
        }
        else{
        	return false;
        }
    }

    /**
     * Remove the doses passed to it from the current doses taken today for a drug. Returns true if the drug
     * existed, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @param dose
     *            dose to be removed
     * @return true if the drug existed, false otherwise.
     */
    private boolean removeDosesTakenTodayForDrug(String drugName, long dose) {

        long currentDose = 0L;
        if (hasDrug(drugName) && hasDayTotalsForDrug(drugName)) {
        	currentDose = getDosesTakenTodayForDrug(drugName);
			runnerData.getDayTotals().remove(drugName.toLowerCase());
            runnerData.getDayTotals().put(drugName.toLowerCase(), Long.valueOf(currentDose - dose));
            return true;
        }
        else{
        	return false;
        }
    }

    /**
     * Returns true if the doctor has frequency entries, false otherwise.
     * 
     * @return true if the doctor has frequency entries, false otherwise
     */
    public boolean hasFrequencyEntries() {
        return !runnerData.getFrequency().isEmpty();
    }

    /**
     * Returns the frequency to take a drug
     * 
     * @param drugName
     *            drug name
     * @return frequency to take the drug
     */
    public long getFrequencyForDrug(String drugName) {
		Map<String, Long> map = runnerData.getFrequency();
		for (Map.Entry<String, Long> pair : map.entrySet())
		{
			if (pair.getKey().equalsIgnoreCase(drugName))
				return pair.getValue();
		}
		return 0;
    }

    /**
     * Returns true if the frequency entry for a drug exists in the doctor, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @return true if the drug exists in the doctor, false otherwise
     */
    public boolean hasFrequencyForDrug(String drugName) {
		Map<String, Long> map = runnerData.getFrequency();
		for (Map.Entry<String, Long> pair : map.entrySet())
		{
			if (pair.getKey().equalsIgnoreCase(drugName))
				return true;
		}
		return false;
    }

    /**
     * Change the dosage frequency for a drug. Returns true if the drug
     * existed, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @param frequency
     *            frequency to be changed to
     * @return true if the drug existed, false otherwise.
     */
    public boolean changeFrequencyOfDrug(String drugName, long frequency) {

        if (hasDrug(drugName) && hasFrequencyForDrug(drugName)) {
			runnerData.getFrequency().remove(drugName.toLowerCase());
        	runnerData.getFrequency().put(drugName.toLowerCase(), Long.valueOf(frequency));
        	return true;
        }
        else{
        	return false;
        }
    }

    /**
     * Returns true if the doctor has amount of supply entries, false otherwise.
     * 
     * @return true if the doctor has amount of supply entries, false otherwise
     */
    public boolean hasSupplyEntries() {
        return !runnerData.getSupply().isEmpty();
    }

    /**
     * Returns the amount supplied for a drug
     * 
     * @param drugName
     *            drug name
     * @return amount supplied for a drug
     */
    public long getSupplyForDrug(String drugName) {
		Map<String, Long> map = runnerData.getSupply();
		for (Map.Entry<String, Long> pair : map.entrySet())
		{
			if (pair.getKey().equalsIgnoreCase(drugName))
				return pair.getValue();
		}
		return 0;
    }

    /**
     * Returns true if the amount supplied for a drug exists in the doctor, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @return true if the drug exists in the doctor, false otherwise
     */
    public boolean hasSupplyForDrug(String drugName) {
		Map<String, Long> map = runnerData.getSupply();
		for (Map.Entry<String, Long> pair : map.entrySet())
		{
			if (pair.getKey().equalsIgnoreCase(drugName))
				return true;
		}
		return false;
    }

    /**
     * Change the supply for a drug. Returns true if the drug
     * existed, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @param supply
     *            supply to be changed to
     * @return true if the drug existed, false otherwise.
     */
    public boolean changeSupplyOfDrug(String drugName, long supply) {

        if (hasDrug(drugName) && hasSupplyForDrug(drugName)) {
			runnerData.getSupply().remove(drugName.toLowerCase());
        	runnerData.getSupply().put(drugName.toLowerCase(), Long.valueOf(supply));
        	return true;
        }
        else{
        	return false;
        }
    }

    /**
     * Returns true if the doctor has amount of refill entries, false otherwise.
     * 
     * @return true if the doctor has amount of refill entries, false otherwise
     */
    public boolean hasNumRefillsEntries() {
        return !runnerData.getNumRefills().isEmpty();
    }

    /**
     * Returns the number of refills for a drug
     * 
     * @param drugName
     *            drug name
     * @return number of refills for a drug
     */
    public long getNumRefillsForDrug(String drugName) {
		Map<String, Long> map = runnerData.getNumRefills();
		for (Map.Entry<String, Long> pair : map.entrySet())
		{
			if (pair.getKey().equalsIgnoreCase(drugName))
				return pair.getValue();
		}
		return 0;
    }

    /**
     * Returns true if the number of refills for a drug exists in the doctor, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @return true if the drug exists in the doctor, false otherwise
     */
    public boolean hasNumRefillsForDrug(String drugName) {
		Map<String, Long> map = runnerData.getNumRefills();
		for (Map.Entry<String, Long> pair : map.entrySet())
		{
			if (pair.getKey().equalsIgnoreCase(drugName))
				return true;
		}
		return false;
    }

    /**
     * Change the refill for a drug. Returns true if the drug
     * existed, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @param refill
     *            refill to be changed to
     * @return true if the drug existed, false otherwise.
     */
    public boolean changeNumRefillsOfDrug(String drugName, long refill) {

        if (hasDrug(drugName) && hasNumRefillsForDrug(drugName)) {
			runnerData.getNumRefills().remove(drugName.toLowerCase());
        	runnerData.getNumRefills().put(drugName.toLowerCase(), Long.valueOf(refill));
        	return true;
        }
        else{
        	return false;
        }
    }

    /**
     * Remove the specificed amount of refills from the total for a drug. Returns true if the drug
     * existed, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @param refill
     *            refills to remove
     * @return true if the drug existed, false otherwise.
     */
    public boolean removeNumRefillsOfDrug(String drugName, long refill) {

        if (hasDrug(drugName) && hasNumRefillsForDrug(drugName)) {
        	long currentRefills = getNumRefillsForDrug(drugName);
			runnerData.getNumRefills().remove(drugName.toLowerCase());
        	runnerData.getNumRefills().put(drugName.toLowerCase(), Long.valueOf(currentRefills - refill));
        	return true;
        }
        else{
        	return false;
        }
    }
    
    /**
     * Remove the 1 refill from the total for a drug. Returns true if the drug
     * existed, false otherwise.
     * 
     * @param drugName
     *            Name of the drug
     * @return true if the drug existed, false otherwise.
     */
    public boolean removeOneNumRefillsOfDrug(String drugName){
    	return removeNumRefillsOfDrug(drugName, 1);
    }
}
