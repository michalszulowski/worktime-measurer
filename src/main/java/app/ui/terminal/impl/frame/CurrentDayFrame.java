package app.ui.terminal.impl.frame;

import app.backend.engine.ActivitiesEngine;
import app.backend.service.ActivitiesEngineService;
import app.day.WorkDayWithActivities;
import app.lang.UiLangMap;
import app.record.ActivityMapWorkRecord;
import app.ui.terminal.output.frame.AppFrame;
import app.ui.terminal.service.TerminalService;
import app.ui.terminal.output.element.DayProgressBar;
import app.ui.terminal.output.element.RecentStatistics;
import app.ui.terminal.output.element.SessionData;
import app.ui.terminal.output.element.TerminalFrameElement;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

public class CurrentDayFrame extends AppFrame {
    private TerminalFrameElement sessionData;
    private TerminalFrameElement recentStatistics;
    private TerminalFrameElement progressBar;
    private LocalDateTime time;

    public CurrentDayFrame(TerminalService terminalService, ActivitiesEngineService appService, UiLangMap langMap,
                           LocalDateTime time) {
        super(terminalService, appService, langMap);
        this.time = time;
        initFrameElements();
    }

    @Override
    protected void printContent() {
        printCurrentSessionData();
        printRecentStatistics();
        printProgressBar();
    }

    private void initFrameElements() {
        Optional<ActivityMapWorkRecord> currentRecord = extractCurrentRecord();
        sessionData = new SessionData(this, currentRecord.orElse(null), time);
        recentStatistics = new RecentStatistics(this, time.toLocalDate());
        progressBar = new DayProgressBar(this, time.toLocalDate());
    }

    private void printCurrentSessionData() {
        sessionData.print();
        terminalService.getOutStream().println();
    }

    private void printRecentStatistics() {
        recentStatistics.print();
        terminalService.getOutStream().println();
    }

    private void printProgressBar() {
        progressBar.print();
        terminalService.getOutStream().println();
    }

    private Optional<ActivityMapWorkRecord> extractCurrentRecord() {
        Optional<WorkDayWithActivities> day = appService.getActiveDay();
        Optional<ActivityMapWorkRecord> foundRecord =
                day.isPresent() ? day.get().getActiveRecord() : Optional.empty();
        return foundRecord;
    }

}
