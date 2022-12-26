package app.backend.engine.impl.local.json.impl.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateTimeDeAndSerializerTest {
    private static Gson gson;
    private static LocalDateTime dateTime;
    private static String json;

    @Test
    public void testIfSerializationCorrect() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeAndSerializer())
                .create();
        LocalDateTime dateTime = LocalDateTime.now();
        String json = gson.toJson(dateTime);
        System.out.println(json);
    }

    private void givenDateTime() {
        //dateTime = Lo
    }

    private void whenDateTimeSerialized() {
        json = gson.toJson(dateTime);
    }

    private void thenJsonShouldBe(String expected) {
        assertEquals(expected, json);
    }
}