package findadoc.storage;

import com.amazon.speech.speechlet.Session;

/**
 * Contains the methods to interact with the persistence layer for findadoc in DynamoDB.
 */
public class findadocDao {
    private final findadocDynamoDbClient dynamoDbClient;

    public findadocDao(findadocDynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    /**
     * Reads and returns the {@link findadocRunner} using user information from the session.
     * <p>
     * Returns null if the item could not be found in the database.
     * 
     * @param session
     * @return
     */
    public findadocRunner getfindadocRunner(Session session) {
        findadocUserDataItem item = new findadocUserDataItem();
        item.setCustomerId(session.getUser().getUserId());

        item = dynamoDbClient.loadItem(item);

        if (item == null) {
            return null;
        }

        return findadocRunner.newInstance(session, item.getRunnerData());
    }

    /**
     * Saves the {@link findadocRunner} into the database.
     * 
     * @param runner
     */
    public void savefindadocRunner(findadocRunner runner) {
        findadocUserDataItem item = new findadocUserDataItem();
        item.setCustomerId(runner.getSession().getUser().getUserId());
        item.setRunnerData(runner.getRunnerData());

        dynamoDbClient.saveItem(item);
    }
}
