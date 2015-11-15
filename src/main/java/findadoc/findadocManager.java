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

        speechText = "Welcome to Find A Doc, how can I help you find a doctor?";
		repromptText = findadocTextUtil.NEXT_HELP;
        return getAskSpeechletResponse(speechText, repromptText);
    }
    


	public SpeechletResponse getPainIntentResponse(Intent intent, Session session, SkillContext skillContext) {
		//instantiate a new runner
		findadocRunner runner = findadocRunner.newInstance(session, findadocRunnerData.newInstance());

		String painArea =
				findadocTextUtil.getPainAreaName(intent.getSlot(SLOT_TYPE_AREA).getValue());

		String painTime =
				findadocTextUtil.getDurationText(intent.getSlot(SLOT_AMAZON_DURATION).getValue());
        if (painArea == null && painTime == null) {
            String speechText = "Where are you hurt?";
            return getAskSpeechletResponse(speechText, speechText);
        }else if (painArea == null && painTime != null)
        {
            String speechText = "Where are you experiencing pain?";

            session.setAttribute(SLOT_AMAZON_DURATION, painTime);      
            return getAskSpeechletResponse(speechText, speechText);
        }
        
        if(findadocTextUtil.isJointPain(painArea))
        {
        	if(painTime != null)
        	{
        		boolean time = false;
        		char[] c = painTime.toCharArray();
        		for (char ch : c)
        		{
        			if (ch == 'T')
        				return runner.GetSpeechletResponse(SymptomType.JointPain);
        		}
        		return runner.GetSpeechletResponse(SymptomType.Arthritis);
        	}
        }
        return runner.GetSpeechletResponse(SymptomType.Pain);
	}

	public SpeechletResponse getSneezeIntentResponse(Intent intent, Session session, SkillContext skillContext) {
		//instantiate a new runner
		findadocRunner runner = findadocRunner.newInstance(session, findadocRunnerData.newInstance());
				
		return runner.GetSpeechletResponse(SymptomType.Sneeze);
		
	}

	public SpeechletResponse getSleepIntentResponse(Intent intent, Session session, SkillContext skillContext) {
		//instantiate a new runner
		findadocRunner runner = findadocRunner.newInstance(session, findadocRunnerData.newInstance());
		
		
		return runner.GetSpeechletResponse(SymptomType.Sleep);
		
	}

	public SpeechletResponse getEmotionIntentResponse(Intent intent, Session session, SkillContext skillContext) {
		//instantiate a new runner
		findadocRunner runner = findadocRunner.newInstance(session, findadocRunnerData.newInstance());

		return runner.GetSpeechletResponse(SymptomType.Emotion);
		
	}

	public SpeechletResponse getToothIntentResponse(Intent intent, Session session, SkillContext skillContext) {
		//instantiate a new runner
		findadocRunner runner = findadocRunner.newInstance(session, findadocRunnerData.newInstance());
		
		
		return runner.GetSpeechletResponse(SymptomType.Tooth);
		
	}

	public SpeechletResponse getVisionIntentResponse(Intent intent, Session session, SkillContext skillContext) {
		//instantiate a new runner
		findadocRunner runner = findadocRunner.newInstance(session, findadocRunnerData.newInstance());

		return runner.GetSpeechletResponse(SymptomType.Vision);
		
	}

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
     * @return response for the exit intent
     */
    public SpeechletResponse getExitIntentResponse(Intent intent, Session session,
            SkillContext skillContext) {
        return skillContext.needsMoreHelp() ? getTellSpeechletResponse("Are you satisfied with your care?")
                : getTellSpeechletResponse("");
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
    public SpeechletResponse getDyingIntentResponse(Intent intent, Session session,
            SkillContext skillContext) {
        return getTellSpeechletResponse("Oh, I see. Sorry, can't help you there. Call 911 for emergency services. Good luck!");
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