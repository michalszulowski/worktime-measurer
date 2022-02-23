package app;

import java.time.Duration;
import java.time.LocalDateTime;

public class WorkRecord {
    private LocalDateTime start;
    private LocalDateTime end;
    private String description;
    private Duration subtractedTime;
    private ActivityMap activities;

    public Duration calculateTotalTime() {
        Duration endStartDiff = Duration.between(start, end);
        Duration totalDiff = endStartDiff.minus(subtractedTime);
        return totalDiff;
    }

    public String getDescription() {
        return description;
        //TODO add building description from activity map
    }

    public void addActivity(Activity activity, float wage) {
        activities.put(activity, wage);
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubtractedTime(Duration subtractedTime) {
        this.subtractedTime = subtractedTime;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Duration getSubtractedTime() {
        return subtractedTime;
    }

    public ActivityMap getActivities() {
        return activities;
    }
}
