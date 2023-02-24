package app.ui.terminal.output;

import app.backend.engine.ActivitiesEngine;
import app.backend.engine.impl.local.LocalFilesystemEngine;
import app.day.WorkDayWithActivities;
import app.lang.DirectLangMap;
import app.lang.UiLangMap;
import app.record.ActivityMapWorkRecord;
import app.ui.terminal.DayProgressBar;
import app.ui.terminal.RecentStatistics;
import app.ui.terminal.SessionData;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

public class LinuxFrame extends AppFrame {
    private TerminalFrameElement sessionData;
    private TerminalFrameElement recentStatistics;
    private TerminalFrameElement progressBar;

    public LinuxFrame(int consoleWidth, int consoleHeight, ActivitiesEngine appEngine, UiLangMap langMap) {
        super(consoleWidth, consoleHeight, appEngine, langMap);
        initFrameElements();
    }

    @Override
    public void print() {
        printCurrentSessionData();
        printRecentStatistics();
        printProgressBar();
    }

    private void initFrameElements() {
        LocalDateTime time = LocalDateTime.of(2023, 2, 22, 11, 30);
        Optional<ActivityMapWorkRecord> currentRecord = getActiveWorkRecord(time);
        sessionData = new SessionData(this, currentRecord.orElse(null), time);
        recentStatistics = new RecentStatistics(this, LocalDate.of(2023, 2, 21));
        progressBar = new DayProgressBar(this, LocalDate.of(2023, 2, 21));
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

    public static void main(String[] args) {
        String documentsPath = System.getProperty("user.home") + "/Documents/work-records/";
        ActivitiesEngine engine = new LocalFilesystemEngine(Paths.get(documentsPath));
        LinuxFrame frame = new LinuxFrame(40, 80, engine, new DirectLangMap());
        frame.print();
    }

}
