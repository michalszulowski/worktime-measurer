package app.ui.terminal;

import app.backend.engine.Engine;
import app.lang.UiLangMap;
import app.record.ActivityMapWorkRecord;
import app.util.Time;

import java.time.LocalDate;
import java.util.Optional;

public class RecentStatistics implements TerminalFrameElement {
    private OutStream outStream;
    private UiLangMap langMap;
    private Engine appEngine;
    private double lastDayWorkTime; //TODO rethink this field

    public RecentStatistics(OutStream outStream, UiLangMap langMap, Engine appEngine, double lastDayWorkTime) {
        this.outStream = outStream;
        this.langMap = langMap;
        this.appEngine = appEngine;
        this.lastDayWorkTime = lastDayWorkTime;
    }

    @Override
    public void print() {
        printThatDayWorkTime();
        printDayBeforeWorkTime();
        printLastWeekAverage();
        printLastMonthAverage();
    }

    private void printThatDayWorkTime() {
        outStream.println(langMap.getText("Today worked") + ": " + lastDayWorkTime);
    }

    private void printDayBeforeWorkTime() {
        LocalDate dayBefore = LocalDate.now().minusDays(1);
        Optional<ActivityMapWorkRecord> recordOfDayBefore = appEngine.getRecord(dayBefore);
        String dayBeforeWorkTime = recordOfDayBefore.isEmpty() ?
                "-" : Time.getHours(recordOfDayBefore.get().calculateTotalTime()) + "";
        outStream.println(langMap.getText("Yesterday worked") + ": " + dayBeforeWorkTime);
    }

    private void printLastWeekAverage() {
        Optional<Float> lastWeekAverage = appEngine.getAverageHoursWorkedInLast(7);
        String lastWeekAverageWorkTime = lastWeekAverage.isEmpty() ? "-" : lastWeekAverage.get() + "";
        outStream.println(langMap.getText("Average work time in last week") + ": " + lastWeekAverageWorkTime);
    }

    private void printLastMonthAverage() {
        Optional<Float> lastMonthAverage = appEngine.getAverageHoursWorkedInLast(30);
        String lastMonthAverageWorkTime = lastMonthAverage.isEmpty() ? "-" : lastMonthAverage.get() + "";
        outStream.println(langMap.getText("Average work time in last month") + ": " + lastMonthAverageWorkTime);
    }
}
