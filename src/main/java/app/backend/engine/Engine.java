package app.backend.engine;

import app.day.WorkDay;
import app.record.WorkRecord;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Kind of database interface. Gives access to records.
 */
public interface Engine <V extends WorkRecord, T extends WorkDay<V> > {
    List<T> getDays(LocalDate from, LocalDate to);
    Optional<T> getDay(LocalDate at);
    void putDay(LocalDate at, T workDay);
    void addRecord(LocalDate at, V record);
}
