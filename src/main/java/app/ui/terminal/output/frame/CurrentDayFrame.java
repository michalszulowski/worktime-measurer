package app.ui.terminal.output.frame;

import app.backend.engine.ActivitiesEngine;
import app.backend.engine.impl.local.LocalFilesystemEngine;
import app.day.WorkDayWithActivities;
import app.lang.DirectLangMap;
import app.lang.UiLangMap;
import app.record.ActivityMapWorkRecord;
import app.ui.terminal.output.element.DayProgressBar;
import app.ui.terminal.output.element.RecentStatistics;
import app.ui.terminal.output.element.SessionData;
import app.ui.terminal.output.element.TerminalFrameElement;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

public class CurrentDayFrame extends AppFrame {
    private TerminalFrameElement sessionData;
    private TerminalFrameElement recentStatistics;
    private TerminalFrameElement progressBar;
    private LocalDateTime time;

    public CurrentDayFrame(int consoleWidth, int consoleHeight, ActivitiesEngine appEngine, UiLangMap langMap,
                           LocalDateTime time) {
        super(consoleWidth, consoleHeight, appEngine, langMap);
        this.time = time;
        initFrameElements();
    }

    @Override
    public void print() {
        printCurrentSessionData();
        printRecentStatistics();
        printProgressBar();
    }

    private void initFrameElements() {
        Optional<ActivityMapWorkRecord> currentRecord = getActiveWorkRecord(time);
        sessionData = new SessionData(this, currentRecord.orElse(null), time);
        recentStatistics = new RecentStatistics(this, time.toLocalDate());
        progressBar = new DayProgressBar(this, time.toLocalDate());
    }

    private void printCurrentSessionData() {
        sessionData.print();
        outStream.println();
    }

    private void printRecentStatistics() {
        recentStatistics.print();
        outStream.println();
    }

    private void printProgressBar() {
        progressBar.print();
        outStream.println();
    }

    //TODO think about maybe moving to app?
    private Optional<ActivityMapWorkRecord> getActiveWorkRecord(LocalDateTime time) {
        Optional<WorkDayWithActivities> workDay = appEngine.getDay(time.toLocalDate());
        if (workDay.isEmpty())
            return Optional.empty();
        Optional<ActivityMapWorkRecord> result = workDay.get()
                .getRecords().stream()
                .sorted(Comparator.comparing(ActivityMapWorkRecord::getStart).reversed())
                .filter(record -> record.getEnd() == null && record.getStart().isBefore(time))
                .findFirst();
        return result;
    }

}
