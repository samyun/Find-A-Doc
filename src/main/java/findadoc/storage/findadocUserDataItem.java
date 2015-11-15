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

    private findadocRunnerData gameData;

    @DynamoDBHashKey(attributeName = "DoctorID")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @DynamoDBAttribute(attributeName = "Data")
    @DynamoDBMarshalling(marshallerClass = findadocGameDataMarshaller.class)
    public findadocRunnerData getGameData() {
        return gameData;
    }

    public void setGameData(findadocRunnerData gameData) {
        this.gameData = gameData;
    }

    /**
     * A {@link DynamoDBMarshaller} that provides marshalling and unmarshalling logic for
     * {@link findadocRunnerData} values so that they can be persisted in the database as String.
     */
    public static class findadocGameDataMarshaller implements
            DynamoDBMarshaller<findadocRunnerData> {

        @Override
        public String marshall(findadocRunnerData gameData) {
            try {
                return OBJECT_MAPPER.writeValueAsString(gameData);
            } catch (JsonProcessingException e) {
                throw new IllegalStateException("Unable to marshall game data", e);
            }
        }

        @Override
        public findadocRunnerData unmarshall(Class<findadocRunnerData> clazz, String value) {
            try {
                return OBJECT_MAPPER.readValue(value, new TypeReference<findadocRunnerData>() {
                });
            } catch (Exception e) {
                throw new IllegalStateException("Unable to unmarshall game data value", e);
            }
        }
    }
}
