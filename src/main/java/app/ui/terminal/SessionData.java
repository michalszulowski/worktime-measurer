package app.ui.terminal;

import app.lang.UiLangMap;
import app.record.Activity;
import app.record.ActivityMapWorkRecord;
import app.ui.terminal.output.AppFrame;
import app.ui.terminal.output.OutStream;
import command.ArgsCommand;
import command.Command;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class SessionData extends BasicTerminalFrameElement {
    private ActivityMapWorkRecord workRecord;
    private LocalDateTime time;

    public SessionData(AppFrame parent, @Nullable ActivityMapWorkRecord workRecord, LocalDateTime time) {
        super(parent);
        this.workRecord = workRecord;
        this.time = time;
    }

    @Override
    public void print() {
        printTime();
        outStream.println();
        outStream.println(langMap.getText("CURRENT SESSION:"));
        if (workRecord != null) {
            printSessionTime();
            printActivities();
        } else {
            outStream.println(langMap.getText("No active record."));
        }
    }

    private void printTime() {
        int year = time.getYear();
        int month = time.getMonthValue();
        int day = time.getDayOfMonth();
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        String formattedTime = String.format("%04d:%02d:%02d %02d:%02d:%02d", year, month, day, hour, minute, second);
        outStream.println(formattedTime);
    }

    private void printSessionTime() {
        Duration sessionDuration = Duration.between(workRecord.getStart(), time);
        long hours = sessionDuration.toHoursPart();
        long minutes = sessionDuration.toMinutesPart();
        long seconds = sessionDuration.toSecondsPart();
        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        outStream.println(langMap.getText("Time") + ": " + formattedTime);
        outStream.println();
    }

    private void printActivities() {
        outStream.println(langMap.getText("Activities") + ":");
        for (Activity activity : workRecord.getActivityMap().getActivities()) {
            printActivity(activity);
        }
    }

    private void printActivity(Activity activity) {
        outStream.println("* " + activity.getDescription());
    }
}
