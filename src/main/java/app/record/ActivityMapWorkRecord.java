package app.record;

import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
public class ActivityMapWorkRecord extends SimpleWorkRecord {
    private final ActivityMap activityMap;

    public ActivityMapWorkRecord(LocalDateTime start, LocalDateTime end, String description) {
        super(start, end, description);
        activityMap = new ActivityMap();
    }

    public void addActivity(Activity activity, float wage) {
        activityMap.put(activity, wage);
    }

    public ActivityMap getActivityMap() {
        return activityMap;
    }
}
