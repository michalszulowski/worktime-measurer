package app.backend.engine.impl.local.json.impl.gson;

import app.backend.engine.impl.local.json.JsonConverter;
import app.day.WorkDayWithActivities;
import app.record.Activity;
import app.record.ActivityMap;
import com.google.gson.GsonBuilder;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WorkDayWithActivitiesConverter extends GsonConverter<WorkDayWithActivities> {

    public WorkDayWithActivitiesConverter() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeAndSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeAndSerializer())
                .registerTypeAdapter(Duration.class, new DurationDeAndSerializer())
                .registerTypeAdapter(Activity.class, new ActivityDeAndSerializer())
                .registerTypeAdapter(ActivityMap.class, new ActivityMapDeserializer())
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public WorkDayWithActivities parseJson(String json) {
        return gson.fromJson(json, WorkDayWithActivities.class);
    }

    public static void main(String[] args) {


    }
}
