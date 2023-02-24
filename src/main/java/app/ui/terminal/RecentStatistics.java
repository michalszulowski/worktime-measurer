package app.ui.terminal;

import app.backend.engine.ActivitiesEngine;
import app.backend.engine.Engine;
import app.backend.statistics.StatisticsCalculator;
import app.backend.statistics.WorkDayWithActivitiesStatisticsCalculator;
import app.day.WorkDayWithActivities;
import app.lang.UiLangMap;
import app.record.ActivityMapWorkRecord;
import app.ui.terminal.output.AppFrame;
import app.ui.terminal.output.OutStream;
import app.ui.terminal.output.TerminalFrameElement;
import app.util.Time;

import java.time.LocalDate;
import java.util.Optional;

public class RecentStatistics extends BasicTerminalFrameElement {
    private LocalDate day;
    private StatisticsCalculator statisticsCalculator;

    public RecentStatistics(AppFrame parent, LocalDate day) {
        super(parent);
        this.day = day;
        statisticsCalculator = parent.getStatisticsCalculator();
    }

    @Override
    public void print() {
        printThatDayWorkTime();
        printDayBeforeWorkTime();
        printLastWeekAverage();
        printLastMonthAverage();
    }

    private void printThatDayWorkTime() {
        LocalDate today = day;
        double todaysWorkTime = statisticsCalculator.getTotalHoursWorkedIn(today, today);
        outStream.println(langMap.getText("Today worked") + ": " + todaysWorkTime);
    }

    private void printDayBeforeWorkTime() {
        LocalDate dayBefore = day.minusDays(1);
        double dayBeforeWorkTime = statisticsCalculator.getAverageHoursWorkedIn(dayBefore, dayBefore);
        outStream.println(langMap.getText("Yesterday worked") + ": " + dayBeforeWorkTime);
    }

    private void printLastWeekAverage() {
        LocalDate startOfTheWeek = day.minusDays(7);
        double lastWeekAverageWorkTime = statisticsCalculator.getAverageHoursWorkedIn(startOfTheWeek, day);
        outStream.println(langMap.getText("Average work time in last week") + ": " + lastWeekAverageWorkTime);
    }

    private void printLastMonthAverage() {
        LocalDate startOfTheMonth = day.minusDays(30);
        double lastMonthAverageWorkTime = statisticsCalculator.getAverageHoursWorkedIn(startOfTheMonth, day);
        outStream.println(langMap.getText("Average work time in last month") + ": " + lastMonthAverageWorkTime);
    }
}
