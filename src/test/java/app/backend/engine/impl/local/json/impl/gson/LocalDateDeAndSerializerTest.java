package app.backend.engine.impl.local.json.impl.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateDeAndSerializerTest {
    private static final LocalDate EXAMPLE_DATE =
            LocalDate.of(1999, 6, 11);
    private static Gson gson;
    private static LocalDate dateParam;
    private static String jsonResult;
    private static String jsonParam;
    private static LocalDate dateResult;


    @BeforeAll
    public static void initGson() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeAndSerializer())
                .create();
    }

    @Test
    public void testBasicSerialization() {
        givenDateTime(EXAMPLE_DATE);
        whenDateTimeSerialized();
        thenJsonShouldBe("\"1999-06-11\"");
    }

    @Test
    public void testBasicDeserialization() {
        givenJson("\"1998-01-01\"");
        whenJsonDeserialized();
        thenResultDateTimeShouldBe(LocalDate.of(1998, 1, 1));
    }

    @Test
    public void testSerializationAndDeserialization() {
        givenDateTime(EXAMPLE_DATE);
        whenDateTimeSerialized();
        givenJson(jsonResult);
        whenJsonDeserialized();
        thenResultDateTimeShouldBe(EXAMPLE_DATE);
    }

    private void givenJson(String json) {
        jsonParam = json;
    }

    private void whenJsonDeserialized() {
        dateResult = gson.fromJson(jsonParam, LocalDate.class);
    }

    private void thenResultDateTimeShouldBe(LocalDate expected) {
        assertEquals(expected, dateResult);
    }

    private void givenDateTime(LocalDate date) {
        dateParam = date;
    }

    private void whenDateTimeSerialized() {
        jsonResult = gson.toJson(dateParam);
    }

    private void thenJsonShouldBe(String expected) {
        assertEquals(expected, jsonResult);
    }
}