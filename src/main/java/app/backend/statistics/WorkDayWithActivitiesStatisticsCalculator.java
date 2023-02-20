package app.backend.statistics;

import app.backend.engine.ActivitiesEngine;
import app.backend.engine.Engine;
import app.day.WorkDayWithActivities;
import app.record.ActivityMapWorkRecord;

import java.time.LocalDate;

public class WorkDayWithActivitiesStatisticsCalculator implements StatisticsCalculator {
    private ActivitiesEngine engine;

    public WorkDayWithActivitiesStatisticsCalculator(ActivitiesEngine engine) {
        this.engine = engine;
    }

    @Override
    public double getAverageHoursWorkedIn(LocalDate from, LocalDate to) {
        double average = engine.getDays(from, to).stream()
                .mapToDouble(WorkDayWithActivities::getTotalTime)
                .average()
                .orElse(0);
        return average;
    }

    @Override
    public double getTotalHoursWorkedIn(LocalDate from, LocalDate to) {
        double sum = engine.getDays(from, to).stream()
                .mapToDouble(WorkDayWithActivities::getTotalTime)
                .sum();
        return sum;
    }
}
