package findadoc;

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
    private static final List<String> NAME_WhiteLIST = Arrays.asList("leg", "legs", 
    												"arm", "arms", "feet", "foot", "knee",
    												"knees", "back", "head", "shoulder", "shoulders",
    												"hands", "hand");

												
    /**
     * Text of complete help.
     */
    public static final String COMPLETE_HELP =


            "Here's some things you can say. My neck has hurt for 1 day, "
                    + "and exit.";

    /**
     * Text of next help.
     */
    public static final String NEXT_HELP = "You can check if you took your medication, "
            + "tell me that you took medicine, or say help. What would you like?";

    /**
     * Cleans up the drug name, and sanitizes it against the blacklist.
     *

     * @param recognizedDrugName
     * @return
     */
    public static String getDrugName(String recognizedDrugName) {

        if (recognizedDrugName == null || recognizedDrugName.isEmpty()) {
            return null;
        }

        String cleanedName;
        if (recognizedDrugName.contains(" ")) {
            // the name should only contain a first name, so ignore the second part if any
            cleanedName = recognizedDrugName.substring(recognizedDrugName.indexOf(" "));
        } else {

            cleanedName = recognizedDrugName;
        }

        // if the name is on our blacklist, it must be mis-recognition
        if (Name_WHITELIST.contains(cleanedName)) {
           return cleanedName; 
        }

        return null;
    }
}