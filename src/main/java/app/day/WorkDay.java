package app.day;

import app.record.ActivityMapWorkRecord;
import app.record.WorkRecord;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface WorkDay <V extends WorkRecord> {
    Collection<V> getRecords();
    void add(V record);
    void remove(V record);
    double getTotalTime();
    LocalDate getDate();
    Optional<V> getActiveRecord();
}
