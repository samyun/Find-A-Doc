package findadoc;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public final class findadocSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds;

    static {
        supportedApplicationIds = new HashSet<String>();
        supportedApplicationIds.add("amzn1.echo-sdk-ams.app.fb3d19f1-6819-4207-9678-a178f99bdb47");
    }

    public findadocSpeechletRequestStreamHandler() {
        super(new findadocSpeechlet(), supportedApplicationIds);
    }
}
