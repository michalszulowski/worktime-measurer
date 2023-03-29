package app.backend.engine.impl.local.json.impl.gson;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateDeAndSerializer extends GsonDeAndSerializer<LocalDate> {
    private DateTimeFormatter timeFormatter;

    public LocalDateDeAndSerializer() {
        timeFormatter =  DateTimeFormatter.ofPattern(DateTimeFormats.SMART_DATE);
    }

    @Override
    public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(timeFormatter.format(date));
    }

    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String asText = jsonElement.getAsString();
        return LocalDate.parse(asText, timeFormatter);
    }
}
