package app.backend.engine.impl.local;

import app.backend.engine.Engine;
import app.backend.engine.impl.file.Reader;
import app.backend.engine.impl.file.SimpleReader;
import app.backend.engine.impl.file.SimpleWriter;
import app.backend.engine.impl.file.Writer;
import app.backend.engine.impl.local.json.JsonConverter;
import app.backend.engine.impl.local.json.impl.gson.WorkDayWithActivitiesConverter;
import app.day.WorkDayWithActivities;
import app.record.ActivityMapWorkRecord;

import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Manages records. Each day is stored in .json file in directory of its month which is stored in directory of its year.
 * For example record of day of 1970-01-01 will be stored in root/1970/01/01.json (YYYY/MM/DD.json
 */
public class LocalFilesystemEngine implements Engine<ActivityMapWorkRecord, WorkDayWithActivities> {
    private Path rootDir;
    private Reader fileReader;
    private Writer fileWriter;
    private JsonConverter<WorkDayWithActivities> workDayJsonParser;
    private DateToPathConverter dateToPathConverter;

    public LocalFilesystemEngine(Path rootDir) {
        this.rootDir = rootDir;
        fileReader = new SimpleReader();
        fileWriter = new SimpleWriter();
        workDayJsonParser = new WorkDayWithActivitiesConverter();
        dateToPathConverter = new DateToPathConverterImpl(rootDir);
    }

    @Override
    public Collection<WorkDayWithActivities> getDays(LocalDate from, LocalDate to) {
        //TODO think about data structure
        List<WorkDayWithActivities> workDays = new ArrayList<>();
        for (LocalDate day = from; !day.isAfter(to); day = day.plus(1, ChronoUnit.DAYS)) {
            Optional<WorkDayWithActivities> workDay = getDay(day);
            workDay.ifPresent(workDays::add);
        }
        return workDays;
    }

    @Override
    public Optional<WorkDayWithActivities> getDay(LocalDate at) {
        Path dayPath = dateToPathConverter.convert(at);
        return tryToReadDay(dayPath);
    }

    @Override
    public void putDay(LocalDate at, WorkDayWithActivities workDay) {
        String jsonContent = workDayJsonParser.createJson(workDay);
        Path filePath = dateToPathConverter.convert(at);
        fileWriter.writeWhole(filePath, jsonContent);
    }

    @Override
    public void addRecord(LocalDate at, ActivityMapWorkRecord record) {
        WorkDayWithActivities workDay = getDay(at).orElseGet(WorkDayWithActivities::new);
        workDay.add(record);
        putDay(at, workDay);
        //TODO figure out if putDay overwrites
    }

    private Optional<WorkDayWithActivities> tryToReadDay(Path from) {
        try {
            String dayInJson = fileReader.readWhole(from);
            WorkDayWithActivities workDay = workDayJsonParser.parseJson(dayInJson);
            return Optional.of(workDay);
        } catch (UncheckedIOException ex) {
            return Optional.empty();
        }
    }

}
