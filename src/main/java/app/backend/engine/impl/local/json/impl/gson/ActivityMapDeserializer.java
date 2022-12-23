package app.backend.engine.impl.local.json.impl.gson;

import app.record.Activity;
import app.record.ActivityMap;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;

public class ActivityMapDeserializer implements JsonDeserializer<ActivityMap> {

    @Override
    public ActivityMap deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Map<String, JsonElement> jsonMap = jsonElement.getAsJsonObject().getAsJsonObject("wageMap").asMap();
        ActivityMap activityMap = new ActivityMap();    
        for (Map.Entry<String, JsonElement> jsonEntry : jsonMap.entrySet()) {
            Activity activity = new Activity(jsonEntry.getKey());
            float wage = jsonEntry.getValue().getAsFloat();
            activityMap.put(activity, wage);
        }
        return activityMap;
    }
}
