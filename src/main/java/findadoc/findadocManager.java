package findadoc;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import findadoc.storage.findadocDao;
import findadoc.storage.findadocDynamoDbClient;
import findadoc.storage.findadocRunner;
import findadoc.storage.findadocRunnerData;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Card;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SsmlOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

/**
 * The {@link findadocManager} receives various events and intents and manages the flow of the
 * drugs.
 */
public class findadocManager {
    /**
     * Intent slot for the type of pain.
     */
    private static final String SLOT_TYPE_PAIN = "TypeOfPain";

    /**
     * Intent slot for area of body with the issue.
     */
    private static final String SLOT_TYPE_AREA = "AreaOfPain";

    /**
     * Intent slot for duration.
     */
    private static final String SLOT_AMAZON_DURATION = "TimeDuration";
    
    private final findadocDao findadocDao;

    public findadocManager(final AmazonDynamoDBClient amazonDynamoDbClient) {
        findadocDynamoDbClient dynamoDbClient =
                new findadocDynamoDbClient(amazonDynamoDbClient);
        findadocDao = new findadocDao(dynamoDbClient);
    }

    /**
     * Creates and returns response for Launch request.
     *
     * @param request
     *            {@link LaunchRequest} for this request
     * @param session
     *            Speechlet {@link Session} for this request
     * @return response for launch request
     */
    public SpeechletResponse getLaunchResponse(LaunchRequest request, Session session) {
        // Speak welcome message and ask user questions
        // based on whether there are players or not.
        String speechText, repromptText;
        findadocRunner runner = findadocDao.getfindadocRunner(session);

        speechText = "Welcome to Find A Doc, What can I do for you?";
		repromptText = findadocTextUtil.NEXT_HELP;
        return getAskSpeechletResponse(speechText, repromptText);
    }
    


	public SpeechletResponse getPainIntentResponse(Intent intent, Session session, SkillContext skillContext) {
		//instantiate a new runner
		findadocRunner runner = findadocRunner.newInstance(session, findadocRunnerData.newInstance());
		
		String painArea =
				findadocTextUtil.getPainAreaName(intent.getSlot(SLOT_TYPE_PAIN).getValue());
		String painTime =
				findadocTextUtil.getDurationText(intent.getSlot(SLOT_AMAZON_DURATION).getValue());
        if (painArea == null && painTime == null) {
            String speechText = "Where are you hurt?";
            return getAskSpeechletResponse(speechText, speechText);
        }else if (painArea != null && painTime == null) {
            String speechText = "How long have you been experiencing pain there?";
			
            String responseText = "How long have you been hurt?";
            return getAskSpeechletResponse(speechText, responseText);
        }else if (painArea == null && painTime != null)
        {
            String speechText = "Where are you experiencing pain?";
            return getAskSpeechletResponse(speechText, speechText);
        }
		
	}

	public SpeechletResponse getSneezeIntentResponse(Intent intent, Session session, SkillContext skillContext) {
		//instantiate a new runner
		findadocRunner runner = findadocRunner.newInstance(session, findadocRunnerData.newInstance());
		
		String painTime =
				findadocTextUtil.getDurationText(intent.getSlot(SLOT_AMAZON_DURATION).getValue());
		
		if (painTime == null) {
            String speechText = "How long have you been experiencing this?";
			
            String responseText = "How long have you been experiencing this symptom? Say Stop to exit.";
            return getAskSpeechletResponse(speechText, responseText);
        }
		
	}

	public SpeechletResponse getSleepIntentResponse(Intent intent, Session session, SkillContext skillContext) {
		// TODO Auto-generated method stub
		return null;
	}

	public SpeechletResponse getEmotionIntentResponse(Intent intent, Session session, SkillContext skillContext) {
		// TODO Auto-generated method stub
		return null;
	}

	public SpeechletResponse getToothIntentResponse(Intent intent, Session session, SkillContext skillContext) {
		// TODO Auto-generated method stub
		return null;
	}

	public SpeechletResponse getVisionIntentResponse(Intent intent, Session session, SkillContext skillContext) {
		// TODO Auto-generated method stub
		return null;
	}
    
//
//    /**
//     * Creates and returns response for the just took intent.
//     *
//     * @param intent
//     *            {@link Intent} for this request
//     * @param session
//     *            Speechlet {@link Session} for this request
//     * @param skillContext
//     * @return response for the add player intent.
//     */
//    public SpeechletResponse getJustTookIntentResponse(Intent intent, Session session,
//            SkillContext skillContext) {
//        // just took a drug and the corresponding logic.
//        // terminate or continue the conversation based on whether the intent
//        // is from a one shot command or not.
//		
//		
//        // Load the previous instance
//        findadocRunner runner = findadocDao.getfindadocRunner(session);
//        if (runner == null) {
//       }       runner = findadocRunner.newInstance(session, findadocRunnerData.newInstance());
//      
//        String drugName =
//                findadocTextUtil.getDrugName(intent.getSlot(SLOT_TYPE_PAIN).getValue());
//        if (drugName == null) {
//            String speechText = "Sorry, I didn't get that. What drug did you take?";
//            return getAskSpeechletResponse(speechText, speechText);
//        }else if (!runner.hasDrug(drugName.toLowerCase())) {
//            String speechText = "You don't need to take any " + drugName + ", according to my system. Did you take a different drug?";
//			
//            String responseText = "Did you take any other drugs? Say Stop to exit.";
//            return getAskSpeechletResponse(speechText, responseText);
//        }
//		
//        long maxDose = runner.getDosesLeftInPeriodForDrug(drugName);        
//        long dose = 0;
//		
//        try {
//        	dose = Integer.parseInt(intent.getSlot(SLOT_AMAZON_DURATION).getValue());
//        } catch (NumberFormatException e) {
//            String speechText = "Sorry, I did not hear the dose. Please say again?";
//            return getAskSpeechletResponse(speechText, speechText);
//        }
//        
//        if (dose < 0)
//        {
//            String speechText = "Sorry, I heard a negative amount. Please say again?";
//            return getAskSpeechletResponse(speechText, speechText);
//        }
//        else if (dose > maxDose)
//        {
//            String speechText = "Sorry, I heard a dose higher than what my system says you have. Please say again?";
//            return getAskSpeechletResponse(speechText, speechText);
//        }
//		
//        // Subtract just taken from monthly prescription
//        runner.removeDosesLeftInPeriodForDrug(drugName, dose);
//        
//        // Add to daily total
//        runner.addDosesTakenTodayForDrug(drugName, dose);
//		
//        try
//		{
//        // Reset last date
//        runner.setLastDateToToday(drugName);
//		}catch (Exception e)
//		{
//			return getTellSpeechletResponse("failed resetting");
//		}
//        
//		long pillsLeft = 0;
//		long refillsLeft = 0;
//		
//        try
//		{
//        // Get data
//        pillsLeft = runner.getDosesLeftInPeriodForDrug(drugName);
//		}catch (Exception e)
//		{
//			if (runner.hasNumDosesForDrug(drugName))
//			{
//				return getTellSpeechletResponse("Has entries");
//				
//			}
//			String bleh = runner.getNames().get(0) + " is in db, " + drugName + "was heard.";
//			return getTellSpeechletResponse(bleh);
//		}
//        try
//		{
//        // Get data
//        refillsLeft = runner.getNumRefillsForDrug(drugName);
//		}catch (Exception e)
//		{
//			return getTellSpeechletResponse("failed getting refills");
//		}
//        
//        if (pillsLeft == 0 && refillsLeft > 0)
//        {
//        	runner.removeOneNumRefillsOfDrug(drugName);
//        	runner.resetDosesLeftInPeriodForDrug(drugName);
//        }
//
//        String speechText = "";
//		
//		if (pillsLeft == 1)
//			speechText += "You currently have " + pillsLeft + " dose of " + drugName + " left. ";
//		else
//			speechText += "You currently have " + pillsLeft + " doses of " + drugName + " left. ";
//			
//        if (pillsLeft < 6)
//        {
//			if (pillsLeft == 1)
//				speechText += "You currently have " + refillsLeft + " refill left. ";
//			else
//				speechText += "You currently have " + refillsLeft + " refills left. ";
//        	
//        	if (refillsLeft < 2)
//        	{
//        		speechText += "Contact your physician for additional refills. ";
//        	}
//        }
//        
//        String repromptText = null;
//
//        if (runner.getNumberOfDrugsInSystem() == 1) {
//            speechText += "Do you need anything else?";
//        } else {
//            speechText += "Would you like to report another drug? Or do you need anything else?";
//        }
//        repromptText = findadocTextUtil.NEXT_HELP;
//        
//
//        // Save the updated runner
//        findadocDao.savefindadocRunner(runner);
//
//        if (repromptText != null) {
//            return getAskSpeechletResponse(speechText, repromptText);
//        } else {
//            return getTellSpeechletResponse(speechText);
//        }
//		/**/
//    }
//
//    /**
//     * Creates and returns response for the need to take intent.
//     *
//     * @param intent
//     *            {@link Intent} for this request
//     * @param session
//     *            {@link Session} for this request
//     * @param skillContext
//     *            {@link SkillContext} for this request
//     * @return response for the add score intent
//     */
//    public SpeechletResponse getNeedToTakeIntentResponse(Intent intent, Session session,
//            SkillContext skillContext) {
//				
//        findadocRunner runner = findadocDao.getfindadocRunner(session);
//        if (runner == null) {
//            runner.resyncFHIR();		
//			findadocDao.savefindadocRunner(runner);
//        }
//        
//        TreeMap<String, Long> toTake = new TreeMap<String, Long>(String.CASE_INSENSITIVE_ORDER);
//        ArrayList<String> notToTake = new ArrayList<String>();
//        
//		
//        for (String s : runner.getNames())
//        {
//    		// if it has a valid last date for drug
//			if (runner.hasLastDatesForDrug(s))
//    		{
//            	long frequency = runner.getFrequencyForDrug(s);
//            	if (frequency >= 1)
//            	{
//            		Calendar cal = Calendar.getInstance();
//            		long dosesTaken = runner.getDosesTakenTodayForDrug(s);
//            		if (cal.getTimeInMillis() - runner.getLastDatesForDrug(s).getTimeInMillis() < 86400000
//            				&& dosesTaken >= frequency)
//            		{
//            			notToTake.add(s);
//						//return getTellSpeechletResponse("1");
//            		}
//            		else
//            		{
//            			toTake.put(s, frequency - runner.getDosesTakenTodayForDrug(s));
//						//return getTellSpeechletResponse("2");
//            		}
//            	}
//            	else
//            	{
//            		Calendar cal = Calendar.getInstance();
//            		long dosesTaken = runner.getDosesTakenTodayForDrug(s);
//            		if (cal.getTimeInMillis() - runner.getLastDatesForDrug(s).getTimeInMillis() < 86400000/frequency)
//            		{
//            			notToTake.add(s);
//						//return getTellSpeechletResponse("3");
//            		}
//            		else
//            		{
//            			toTake.put(s, 1L);
//						//return getTellSpeechletResponse("4");
//            		}
//            	}
//    		}
//    		else
//    		{
//    			toTake.put(s, runner.getFrequencyForDrug(s));
//    		}
//
//        }
//        
//        StringBuilder speechText = new StringBuilder();
//        
//        if (toTake.isEmpty())
//        {
//        	speechText.append("You do not need to take any medication right now.");
//        }
//        else
//        {
//        	speechText.append("You need to take the following now: ");
//        	for (Entry<String, Long> pair : toTake.entrySet())
//        	{
//        		if (pair.getValue() > 1)
//    			{
//        			speechText.append(pair.getValue().toString() + " doses of " + pair.getKey() + ". ");
//    			}
//        		else
//        		{
//        			speechText.append(pair.getValue().toString() + " dose of " + pair.getKey() + ". ");
//        		}
//        	}
//        	
//			if (notToTake.size() > 0)
//			{
//				speechText.append("You do not need to take the following: ");
//				for (String s : notToTake)
//				{
//					speechText.append(s + ". ");
//				}
//			}
//        }
//        
//        String speechTextStr = speechText.toString(); 
//        
//        String repromptText = "Do you need anything else?";
//        
//        if (repromptText != null) {
//            return getAskSpeechletResponse(speechTextStr, repromptText);
//        } else {
//            return getTellSpeechletResponse(speechTextStr);
//		}
//	}

    /**
     * Creates and returns response for the help intent.
     *
     * @param intent
     *            {@link Intent} for this request
     * @param session
     *            {@link Session} for this request
     * @param skillContext
     *            {@link SkillContext} for this request
     * @return response for the help intent
     */
    public SpeechletResponse getHelpIntentResponse(Intent intent, Session session,
            SkillContext skillContext) {
        return skillContext.needsMoreHelp() ? getAskSpeechletResponse(
                findadocTextUtil.COMPLETE_HELP + " So, how can I help?",
                findadocTextUtil.NEXT_HELP)
                : getTellSpeechletResponse(findadocTextUtil.COMPLETE_HELP);
    }
	
	/**
     * Creates and returns response for the exit intent.
     *
     * @param intent
     *            {@link Intent} for this request
     * @param session
     *            {@link Session} for this request
     * @param skillContext
     *            {@link SkillContext} for this request
     * @return response for the reset intent
     */
    public SpeechletResponse getResetPlayersIntentResponse(Intent intent, Session session,
            SkillContext skillContext) {

        findadocRunner runner = findadocDao.getfindadocRunner(session);
        if (runner == null) {
            runner = findadocRunner.newInstance(session, findadocRunnerData.newInstance());
        }
		else{
            runner.resyncFHIR();
        }
		
		findadocDao.savefindadocRunner(runner);
		String ssmlSpeech = "<speak>Successfully synced with the <phoneme alphabet=\"x-sampa\" ph=\"faI@r/\">FHIR</phoneme> service</speak>";
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Find A Doc");
        card.setContent("Successfully synced with the FHIR service");

        // Create the plain text output.
        SsmlOutputSpeech speech = new SsmlOutputSpeech();
        speech.setSsml(ssmlSpeech);

        return SpeechletResponse.newTellResponse(speech, card);
    }
		

    /**
     * Creates and returns response for the exit intent.
     *
     * @param intent
     *            {@link Intent} for this request
     * @param session
     *            {@link Session} for this request
     * @param skillContext
     *            {@link SkillContext} for this request
     * @return response for the exit intent
     */
    public SpeechletResponse getExitIntentResponse(Intent intent, Session session,
            SkillContext skillContext) {
        return skillContext.needsMoreHelp() ? getTellSpeechletResponse("Why does it "
                + "take a minute to say hello, but forever to say goodbye?")
                : getTellSpeechletResponse("");
    }

    /**
     * Returns an ask Speechlet response for a speech and reprompt text.
     *
     * @param speechText
     *            Text for speech output
     * @param repromptText
     *            Text for reprompt output
     * @return ask Speechlet response for a speech and reprompt text
     */
    private SpeechletResponse getAskSpeechletResponse(String speechText, String repromptText) {
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Find A Doc");
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

    /**
     * Returns a tell Speechlet response for a speech and reprompt text.
     *
     * @param speechText
     *            Text for speech output
     * @return a tell Speechlet response for a speech and reprompt text
     */
    private SpeechletResponse getTellSpeechletResponse(String speechText) {
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Find A Doc");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }
}
