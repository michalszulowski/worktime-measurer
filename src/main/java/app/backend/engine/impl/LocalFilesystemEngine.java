package app.backend.engine.impl;

import app.backend.engine.Engine;
import app.day.WorkDay;
import app.day.WorkDayWithActivities;
import app.record.ActivityMapWorkRecord;
import app.record.WorkRecord;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

/**
 * Manages records. Each day is stored in .json file in directory of its month which is stored in directory of its year.
 * For example record of day of 1970-01-01 will be stored in root/1970/01/01.json
 */
public class LocalFilesystemEngine implements Engine<ActivityMapWorkRecord, WorkDayWithActivities> {
    //root of filesystem
    //json loader

    @Override
    public Collection<WorkDayWithActivities> getDays(LocalDate startingFrom) {
        return null;
    }

    @Override
    public Optional<WorkDayWithActivities> getDay(LocalDate at) {
        return Optional.empty();
    }

    @Override
    public void putDay(LocalDate at, WorkDayWithActivities workDay) {

    }

    @Override
    public void addRecord(LocalDate at, WorkDayWithActivities record) {

    }
}
