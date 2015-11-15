package findadoc.storage;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshaller;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Model representing an item of the findadocUserData table in DynamoDB for the findadoc
 * skill.
 */
@DynamoDBTable(tableName = "FindADocUserData")
public class findadocUserDataItem {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private String customerId;

    private findadocGameData gameData;

    @DynamoDBHashKey(attributeName = "CustomerId")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @DynamoDBAttribute(attributeName = "Data")
    @DynamoDBMarshalling(marshallerClass = findadocGameDataMarshaller.class)
    public findadocGameData getGameData() {
        return gameData;
    }

    public void setGameData(findadocGameData gameData) {
        this.gameData = gameData;
    }

    /**
     * A {@link DynamoDBMarshaller} that provides marshalling and unmarshalling logic for
     * {@link findadocGameData} values so that they can be persisted in the database as String.
     */
    public static class findadocGameDataMarshaller implements
            DynamoDBMarshaller<findadocGameData> {

        @Override
        public String marshall(findadocGameData gameData) {
            try {
                return OBJECT_MAPPER.writeValueAsString(gameData);
            } catch (JsonProcessingException e) {
                throw new IllegalStateException("Unable to marshall game data", e);
            }
        }

        @Override
        public findadocGameData unmarshall(Class<findadocGameData> clazz, String value) {
            try {
                return OBJECT_MAPPER.readValue(value, new TypeReference<findadocGameData>() {
                });
            } catch (Exception e) {
                throw new IllegalStateException("Unable to unmarshall game data value", e);
            }
        }
    }
}
