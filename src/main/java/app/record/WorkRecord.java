package app.record;

import java.time.LocalDateTime;

public interface WorkRecord {
    LocalDateTime getStart();
    LocalDateTime getEnd();
    String getDescription();
}
