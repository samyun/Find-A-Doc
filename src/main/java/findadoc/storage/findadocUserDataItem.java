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

    private findadocRunnerData runnerData;

    @DynamoDBHashKey(attributeName = "CustomerId")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @DynamoDBAttribute(attributeName = "Data")
    @DynamoDBMarshalling(marshallerClass = findadocRunnerDataMarshaller.class)
    public findadocRunnerData getRunnerData() {
        return runnerData;
    }

    public void setRunnerData(findadocRunnerData runnerData) {
        this.runnerData = runnerData;
    }

    /**
     * A {@link DynamoDBMarshaller} that provides marshalling and unmarshalling logic for
     * {@link findadocRunnerData} values so that they can be persisted in the database as String.
     */
    public static class findadocRunnerDataMarshaller implements
            DynamoDBMarshaller<findadocRunnerData> {

        @Override
        public String marshall(findadocRunnerData runnerData) {
            try {
                return OBJECT_MAPPER.writeValueAsString(runnerData);
            } catch (JsonProcessingException e) {
                throw new IllegalStateException("Unable to marshall runner data", e);
            }
        }

        @Override
        public findadocRunnerData unmarshall(Class<findadocRunnerData> clazz, String value) {
            try {
                return OBJECT_MAPPER.readValue(value, new TypeReference<findadocRunnerData>() {
                });
            } catch (Exception e) {
                throw new IllegalStateException("Unable to unmarshall runner data value", e);
            }
        }
    }
}
