package app.day;

import app.record.ActivityMapWorkRecord;
import app.util.Time;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WorkDayWithActivities implements WorkDay<ActivityMapWorkRecord> {
    private List<ActivityMapWorkRecord> records;

    public WorkDayWithActivities() {
        records = new ArrayList<>();
    }

    @Override
    public Collection<ActivityMapWorkRecord> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public void add(ActivityMapWorkRecord record) {
        records.add(record);
    }

    @Override
    public void remove(ActivityMapWorkRecord record) {
        records.remove(record);
    }

    @Override
    public double getTotalTime() {
        return records.stream()
                .map(ActivityMapWorkRecord::calculateTotalTime)
                .mapToDouble(Time::getHours)
                .sum();
    }

}
