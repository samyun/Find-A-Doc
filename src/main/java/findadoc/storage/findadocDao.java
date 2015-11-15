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
     * Reads and returns the {@link findadocGame} using user information from the session.
     * <p>
     * Returns null if the item could not be found in the database.
     * 
     * @param session
     * @return
     */
    public findadocGame getfindadocGame(Session session) {
        findadocUserDataItem item = new findadocUserDataItem();
        item.setCustomerId(session.getUser().getUserId());

        item = dynamoDbClient.loadItem(item);

        if (item == null) {
            return null;
        }

        return findadocGame.newInstance(session, item.getGameData());
    }

    /**
     * Saves the {@link findadocGame} into the database.
     * 
     * @param game
     */
    public void savefindadocGame(findadocGame game) {
        findadocUserDataItem item = new findadocUserDataItem();
        item.setCustomerId(game.getSession().getUser().getUserId());
        item.setGameData(game.getGameData());

        dynamoDbClient.saveItem(item);
    }
}
