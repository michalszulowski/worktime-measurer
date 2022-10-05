package app.record;

import java.time.Duration;
import java.time.LocalDateTime;

public class WorkRecord {
    public LocalDateTime start;
    public LocalDateTime end;
    public String description;
    public Duration subtractedTime;
    public ActivityMap activities;

    public WorkRecord() {
        activities = new ActivityMap();
    }

    public Duration calculateTotalTime() {
        LocalDateTime actualEnd = end;
        if (actualEnd == null) {
            actualEnd = LocalDateTime.now();
        }
        Duration endStartDiff = Duration.between(start, actualEnd);
        Duration totalDiff = endStartDiff.minus(subtractedTime);
        return totalDiff;
    }

    public void addActivity(Activity activity, float wage) {
        activities.put(activity, wage);
    }
}
