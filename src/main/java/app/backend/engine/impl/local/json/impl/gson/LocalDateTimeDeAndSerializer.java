package app.backend.engine.impl.local.json.impl.gson;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeAndSerializer extends GsonDeAndSerializer<LocalDateTime> {
    private DateTimeFormatter dateTimeFormatter;

    public LocalDateTimeDeAndSerializer() {
        dateTimeFormatter = DateTimeFormatter.ofPattern(DateTimeFormats.SMART);
    }

    @Override
    public JsonElement serialize(LocalDateTime dateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(dateTimeFormatter.format(dateTime));
    }

    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String asText = jsonElement.getAsString();
        return LocalDateTime.parse(asText, dateTimeFormatter);
    }

    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeAndSerializer())
                .create();
        LocalDateTime dateTime = LocalDateTime.now();
        String json = gson.toJson(dateTime);
        System.out.println(json);
    }
}
