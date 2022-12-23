package app.backend.engine.impl.local.json.impl.gson;

import app.backend.engine.impl.local.json.JsonConverter;
import app.day.WorkDayWithActivities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkDayWithActivitiesConverterTest {
    @Test
    public void checkIfRawSerializedAndSerializedDeserializedEqual() {
        JsonConverter<WorkDayWithActivities> jsonConverter = new WorkDayWithActivitiesConverter();
        WorkDayWithActivities workDay = WorkDayWithActivities.sampleDay();
        String serialized = jsonConverter.createJson(workDay);
        WorkDayWithActivities deserializedDay = jsonConverter.parseJson(serialized);
        String serializedDeserialized = jsonConverter.createJson(deserializedDay);
        assertEquals(serialized, serializedDeserialized);
    }

}