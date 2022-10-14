package app.record;

import java.time.Duration;
import java.time.LocalDateTime;

public class ActivityMapWorkRecord extends SimpleWorkRecord {
    private ActivityMap activities;

    public ActivityMapWorkRecord() {
        activities = new ActivityMap();
    }

    public void addActivity(Activity activity, float wage) {
        activities.put(activity, wage);
    }
}
