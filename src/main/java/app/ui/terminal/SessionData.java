package app.ui.terminal;

import app.lang.UiLangMap;
import app.record.Activity;
import app.record.ActivityMapWorkRecord;
import app.ui.terminal.output.OutStream;

import java.time.Duration;
import java.time.LocalDateTime;

public class SessionData extends BasicTerminalFrameElement {
    private ActivityMapWorkRecord workRecord;
    private LocalDateTime time;

    public SessionData(OutStream outStream, UiLangMap langMap, ActivityMapWorkRecord workRecord, LocalDateTime time) {
        super(outStream, langMap);
        this.workRecord = workRecord;
        this.time = time;
    }

    @Override
    public void print() {
        outStream.println(langMap.getText("CURRENT SESSION"));
        printSessionTime();
        printActivities();
    }

    private void printSessionTime() {
        Duration sessionDuration = Duration.between(workRecord.getStart(), time);
        long hours = sessionDuration.toHours();
        long minutes = sessionDuration.toMinutes();
        long seconds = sessionDuration.toSeconds();
        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        outStream.println(langMap.getText("Time") + ": " + formattedTime);
    }

    private void printActivities() {
        outStream.println(langMap.getText("Activities") + ":");
        for (Activity activity : workRecord.getActivityMap().getActivities()) {
            printActivity(activity);
        }
    }

    private void printActivity(Activity activity) {
        outStream.println(activity.getDescription());
    }
}
