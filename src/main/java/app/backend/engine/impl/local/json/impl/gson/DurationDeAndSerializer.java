package app.backend.engine.impl.local.json.impl.gson;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.Duration;

public class DurationDeAndSerializer extends GsonDeAndSerializer<Duration> {
    @Override
    public JsonElement serialize(Duration duration, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(duration.getSeconds());
    }

    @Override
    public Duration deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        long seconds = jsonElement.getAsLong();
        return Duration.ofSeconds(seconds);
    }
}
