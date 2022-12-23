package app.backend.engine.impl.local.json.impl.gson;

import app.record.Activity;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ActivityDeAndSerializer extends GsonDeAndSerializer<Activity> {

    @Override
    public Activity deserialize(JsonElement jsonElement, Type type,
                                JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String activityName = jsonElement.getAsString();
        return new Activity(activityName);
    }

    @Override
    public JsonElement serialize(Activity activity, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(activity.getDescription());
    }

    public static void main(String[] args) {
        Gson gson;
        gson = new GsonBuilder()
                .registerTypeAdapter(Activity.class, new ActivityDeAndSerializer())
                .enableComplexMapKeySerialization()
                .create();
        //gson = new Gson();
        Activity activity = new Activity("Aaa");
        String json = gson.toJson(activity);
        System.out.println(json);
        Activity deserialized = gson.fromJson(json, Activity.class);
        System.out.println(deserialized);
        Activity[] activities = {new Activity("A"), new Activity("B")};
        String arrJson = gson.toJson(activities);
        Map<Activity, Float> activityFloatMap = new HashMap<>();
        activityFloatMap.put(new Activity("C"), 1.f);
        activityFloatMap.put(new Activity("F"), 2.f);
        String mapJson = gson.toJson(activityFloatMap);
        System.out.println(mapJson);
    }
}
