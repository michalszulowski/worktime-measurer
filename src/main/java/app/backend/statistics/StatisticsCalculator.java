package app.backend.statistics;

import java.time.LocalDate;

public interface StatisticsCalculator {
    double getAverageHoursWorkedIn(LocalDate from, LocalDate to);
    double getTotalHoursWorkedIn(LocalDate from, LocalDate to);
}
