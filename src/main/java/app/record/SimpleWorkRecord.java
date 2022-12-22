package app.record;

import java.time.Duration;
import java.time.LocalDateTime;

public class SimpleWorkRecord implements WorkRecord {
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected String description;
    protected Duration subtractedTime;

    public SimpleWorkRecord(LocalDateTime start, LocalDateTime end, String description) {
        this.start = start;
        this.end = end;
        this.description = description;
        subtractedTime = Duration.ZERO;
    }

    @Override
    public LocalDateTime getStart() {
        return start;
    }

    @Override
    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public String getDescription() {
        return description;
    }

    //TODO think about moving to interface
    public Duration calculateTotalTime() {
        LocalDateTime actualEnd = end;
        if (actualEnd == null) {
            actualEnd = LocalDateTime.now();
        }
        Duration endStartDiff = Duration.between(start, actualEnd);
        Duration totalDiff = endStartDiff.minus(subtractedTime);
        return totalDiff;
    }
}
