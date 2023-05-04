package app.backend.engine.impl.local.json.impl.gson;

import app.backend.engine.impl.local.json.JsonConverter;
import app.day.WorkDayWithActivities;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkDayWithActivitiesConverterTest {
    private static WorkDayWithActivitiesConverter jsonConverter;
    private static WorkDayWithActivities workDayParam;
    private static String jsonResult;
    private static String jsonParam;
    private static WorkDayWithActivities workDayResult;

    @BeforeAll
    public static void initJsonConverter() {
        jsonConverter = new WorkDayWithActivitiesConverter();
    }

    //TODO think about redoing this method
    @Test
    public void testSerializationAndDeserialization() {
        WorkDayWithActivities sampleDay = WorkDayWithActivities.generateSampleDay();
        givenWorkDay(sampleDay);
        whenSerializing();
        givenJson(jsonResult);
        whenDeserializing();
        thenWorkDayResultShouldBe(sampleDay);
    }

    private void givenWorkDay(WorkDayWithActivities workDay) {
        workDayParam = workDay;
    }

    private void whenSerializing() {
        jsonResult = jsonConverter.createJson(workDayParam);
    }

    private void thenJsonResultShouldBe(String expected) {
        assertEquals(expected, jsonResult);
    }

    private void givenJson(String json) {
        jsonParam = json;
    }

    private void whenDeserializing() {
        workDayResult = jsonConverter.parseJson(jsonParam);
    }

    private void thenWorkDayResultShouldBe(WorkDayWithActivities expected) {
        assertEquals(expected, workDayResult);
    }

}