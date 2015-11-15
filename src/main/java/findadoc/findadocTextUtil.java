package findadoc;

import java.sql.Time;
import java.util.Arrays;
import java.util.List;

/**
 * Util containing various text related utils.
 */
public final class findadocTextUtil {

    private findadocTextUtil() {
    }

    /**
     * List of drug names blacklisted for this app.
     */
    private static final List<String> NAME_WHITELIST = Arrays.asList("arm","leg","head","back","foot","ankle","ear",
    		"neck","hand","hands","arms","legs","knees","knee","feet","ankles","elbow","elbows","shoulder","shoulders",
    		"ears","toes","fingers","finger","thumb","thumbs","chest","butt","ass","thighs","tits","balls","dick","forearms",
    		"nose","joints");

												
    /**
     * Text of complete help.
     */
    public static final String COMPLETE_HELP =


            "Here's some things you can say. My leg hurts, my neck has hurt for 1 day, "
                    + "I've been coughing the past week, and exit.";

    /**
     * Text of next help.
     */
    public static final String NEXT_HELP = "You can tell me your symptoms, "
            + "say help, or exit. What would you like?";

    /**
     * Cleans up the pain area name and sanitizes it.
     *

     * @param recognizedAreaName
     * @return
     */
    public static String getPainAreaName(String recognizedAreaName) {

        if (recognizedAreaName == null || recognizedAreaName.isEmpty()) {
            return null;
        }
        String cleanedName;
        if (recognizedAreaName.contains(" ")) {
            // the name should only contain a first name, so ignore the second part if any
            cleanedName = recognizedAreaName.substring(recognizedAreaName.indexOf(" "));
        } else {

            cleanedName = recognizedAreaName;
        }

        // if the name is on our whitelist, it must be correct
        if (NAME_WHITELIST.contains(cleanedName)) {
           return cleanedName; 
        }

        return null;
    }
    
    /**
     * Cleans up the pain area name and sanitizes it.
     *

     * @param recognizedDurationText
     * @return
     */
    public static String getDurationText(String recognizedDurationText) {

        if (recognizedDurationText == null || recognizedDurationText.isEmpty()) {
            return null;
        }

        boolean isTime = false;
        boolean isDay = false;
        
        // if first character isn't a P, it's not a valid time
        if (recognizedDurationText.charAt(0) != 'P')
        	return null;
        
        // if second character is a T, it's a time, if it's a number it's a day
        if (recognizedDurationText.charAt(1) == 'T')
        {
        	isTime = true;
        	isDay = false;
        }else if (Character.isDigit(recognizedDurationText.charAt(1)))
        {
        	isDay = true;
        	isTime = false;
        }
        
        int l = recognizedDurationText.length();
        // if all but last char is a number, it's valid thus far
        if (isTime)
        {
        	for (int i = 2; i < l-2;i++)
        	{
        		if (!Character.isDigit(recognizedDurationText.charAt(i)))
        		{
        			return null;
        		}
        	}
        }
        else if (isDay)
        {
        	for (int i = 2; i < l-2;i++)
        	{
        		if (!Character.isDigit(recognizedDurationText.charAt(i)))
        		{
        			return null;
        		}
        	}
        }

        //if last char is not SMHDWY, it's invalid.
        if (isTime)
        {
        	char c = recognizedDurationText.charAt(l-1);
        	if (c != 'S' && c != 'M' && c != 'H')
        		return null;
        }
        else if (isDay)
        {
        	char c = recognizedDurationText.charAt(l-1);
        	if (c != 'D' && c != 'W' && c != 'M' && c != 'Y')
        		return null;
        }

        
    	return recognizedDurationText;
    }
    
    public static boolean isJointPain(String text)
    {
    	if (text.equals("joints"))
    		return true;
    	return false;
    }
}