package app.day;

import app.record.ActivityMapWorkRecord;
import app.record.WorkRecord;

import java.util.Collection;

public interface WorkDay <V extends WorkRecord> {
    Collection<V> getRecords();
    void add(V record);
    void remove(V record);
    double getTotalTime();

}
