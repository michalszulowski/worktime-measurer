package app.backend.engine;

import app.day.WorkDay;
import app.record.WorkRecord;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface Engine <V extends WorkRecord, T extends WorkDay<V> > {
    Collection<T> getDays(LocalDate startingFrom);
    Optional<T> getDay(LocalDate at);
    void putDay(LocalDate at, T workDay);
    void addRecord(LocalDate at, T record);
}