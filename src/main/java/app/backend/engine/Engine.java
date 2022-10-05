package app.backend.engine;

import app.record.WorkRecord;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface Engine {
    Collection<WorkRecord> getRecords(LocalDate startingFrom);
    Optional<WorkRecord> getRecord(LocalDate at);
    Optional<Float> getAverageHoursWorkedInLast(int days);
}
