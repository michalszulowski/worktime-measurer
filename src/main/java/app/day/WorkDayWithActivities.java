package app.day;

import app.record.Activity;
import app.record.ActivityMapWorkRecord;
import app.util.Time;
import com.google.gson.Gson;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;

@EqualsAndHashCode
public class WorkDayWithActivities implements WorkDay<ActivityMapWorkRecord> {
    private List<ActivityMapWorkRecord> records;
    private final LocalDate date;

    public WorkDayWithActivities(LocalDate date) {
        this.date = date;
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

    /**
     * @return time as hours
     */
    @Override
    public double getTotalTime() {
        return records.stream()
                .map(ActivityMapWorkRecord::calculateTotalTime)
                .mapToDouble(Time::getHours)
                .sum();
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public Optional<ActivityMapWorkRecord> getActiveRecord() {
        Optional<ActivityMapWorkRecord> activeRecord = getRecords().stream()
                .sorted(Comparator.comparing(ActivityMapWorkRecord::getStart).reversed())
                .filter(record -> record.getEnd() == null)
                .findFirst();
        return activeRecord;
    }

    // --- TESTS ---

    public static void main(String[] args) {
        test2();
    }

    private static void test2() {
        WorkDayWithActivities workDayWithActivities = generateSampleDay();
        Gson gson = new Gson();
        String json = gson.toJson(workDayWithActivities);
        System.out.println(json);
    }

    public static WorkDayWithActivities generateSampleDay() {
        LocalDateTime referenceDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        WorkDayWithActivities workDay = new WorkDayWithActivities(LocalDate.of(1990, 1, 1));
        ActivityMapWorkRecord workRecord1 = new ActivityMapWorkRecord(referenceDateTime,
                referenceDateTime.plusHours(1), "Zadania1");
        workRecord1.addActivity(new Activity("Praca1"), 1);
        workRecord1.addActivity(new Activity("Praca2"), 2);
        ActivityMapWorkRecord workRecord2 = new ActivityMapWorkRecord(referenceDateTime.plusHours(3),
                referenceDateTime.plusHours(5), "Zadania2");
        workRecord2.addActivity(new Activity("Cwiczenia x"), 1);
        workDay.add(workRecord1);
        workDay.add(workRecord2);
        return workDay;
    }


}
