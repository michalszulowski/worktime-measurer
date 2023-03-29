package app.record;

import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.time.LocalDateTime;

@EqualsAndHashCode
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
