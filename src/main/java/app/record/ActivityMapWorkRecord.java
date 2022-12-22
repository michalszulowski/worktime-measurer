package app.record;

import java.time.Duration;
import java.time.LocalDateTime;

public class ActivityMapWorkRecord extends SimpleWorkRecord {
    private ActivityMap activities;

    public ActivityMapWorkRecord(LocalDateTime start, LocalDateTime end, String description) {
        super(start, end, description);
        activities = new ActivityMap();
    }

    public void addActivity(Activity activity, float wage) {
        activities.put(activity, wage);
    }
}
