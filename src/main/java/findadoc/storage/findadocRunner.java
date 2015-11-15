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
    

    public SpeechletResponse GetSpeechletResponse(SymptomType type)
    {
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
    	
    	return GetResponse(docName);
    }
    
    private SpeechletResponse GetResponse(String docName)
    {
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        
        card.setTitle(docName);
        
        String affiliation = GetAffiliationFromName(docName);
        String phone = GetPhoneFromName(docName);
        
        String cardText = affiliation + " / " + phone;
        card.setContent(cardText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        String speechText = "I recommend you see " + docName + " at " + affiliation + ". See the Alexa app for a phone number.";
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }
    
    private String GetAffiliationFromName(String docName)
    {
    	Map<String,String> map = runnerData.getAffiliation();
    	
		return map.get(docName);
    }
    
    private String GetPhoneFromName(String docName)
    {
    	Map<String,String> map = runnerData.getPhone();
    	
		return map.get(docName);
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
}