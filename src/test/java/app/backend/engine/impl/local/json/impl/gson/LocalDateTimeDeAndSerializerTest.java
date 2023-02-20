package app.backend.engine.impl.local.json.impl.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateTimeDeAndSerializerTest {
    private static final LocalDateTime EXAMPLE_DATE_TIME =
            LocalDateTime.of(1999, 6, 11, 10, 30, 15);
    private static Gson gson;
    private static LocalDateTime dateTimeParam;
    private static String jsonResult;
    private static String jsonParam;
    private static LocalDateTime dateTimeResult;


    @BeforeAll
    public static void initGson() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeAndSerializer())
                .create();
    }

    @Test
    public void testBasicSerialization() {
        givenDateTime(EXAMPLE_DATE_TIME);
        whenDateTimeSerialized();
        thenJsonShouldBe("\"1999-06-11 10:30:15\"");
    }

    @Test
    public void testBasicDeserialization() {
        givenJson("\"1998-01-01 11:59:59\"");
        whenJsonDeserialized();
        thenResultDateTimeShouldBe(LocalDateTime.of(1998, 1, 1, 11, 59, 59));
    }

    @Test
    public void testSerializationAndDeserialization() {
        givenDateTime(EXAMPLE_DATE_TIME);
        whenDateTimeSerialized();
        givenJson(jsonResult);
        whenJsonDeserialized();
        thenResultDateTimeShouldBe(EXAMPLE_DATE_TIME);
    }

    private void givenJson(String json) {
        jsonParam = json;
    }

    private void whenJsonDeserialized() {
        dateTimeResult = gson.fromJson(jsonParam, LocalDateTime.class);
    }

    private void thenResultDateTimeShouldBe(LocalDateTime expected) {
        assertEquals(expected, dateTimeResult);
    }

    private void givenDateTime(LocalDateTime dateTime) {
        dateTimeParam = dateTime;
    }

    private void whenDateTimeSerialized() {
        jsonResult = gson.toJson(dateTimeParam);
    }

    private void thenJsonShouldBe(String expected) {
        assertEquals(expected, jsonResult);
    }
}