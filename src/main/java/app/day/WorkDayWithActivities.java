package app.day;

import app.record.Activity;
import app.record.ActivityMapWorkRecord;
import app.util.Time;

import java.time.LocalDateTime;
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

    // --- TESTS ---

    public static void main(String[] args) {
        WorkDayWithActivities workDay = new WorkDayWithActivities();
        ActivityMapWorkRecord workRecord1 = new ActivityMapWorkRecord(LocalDateTime.now(),
                LocalDateTime.now().plusHours(1), "Zadania1");
        workRecord1.addActivity(new Activity("Praca1"), 1);
        workRecord1.addActivity(new Activity("Praca2"), 2);
        ActivityMapWorkRecord workRecord2 = new ActivityMapWorkRecord(LocalDateTime.now().plusHours(3),
                LocalDateTime.now().plusHours(5), "Zadania2");
        workRecord1.addActivity(new Activity("Cwiczenia x"), 1);
        workDay.add(workRecord1);
        workDay.add(workRecord2);

    }
}
