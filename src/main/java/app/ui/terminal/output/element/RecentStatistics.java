package app.ui.terminal.output.element;

import app.backend.statistics.StatisticsCalculator;
import app.ui.terminal.output.frame.AppFrame;

import java.time.LocalDate;

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
        outStream.println(langMap.getText("Today worked") + ": " + formatDouble(todaysWorkTime));
    }

    private void printDayBeforeWorkTime() {
        LocalDate dayBefore = day.minusDays(1);
        double dayBeforeWorkTime = statisticsCalculator.getAverageHoursWorkedIn(dayBefore, dayBefore);
        outStream.println(langMap.getText("Yesterday worked") + ": " + formatDouble(dayBeforeWorkTime));
    }

    private void printLastWeekAverage() {
        LocalDate startOfTheWeek = day.minusDays(7);
        double lastWeekAverageWorkTime = statisticsCalculator.getAverageHoursWorkedIn(startOfTheWeek, day);
        outStream.println(langMap.getText("Average work time in last week") + ": "
                + formatDouble(lastWeekAverageWorkTime));
    }

    private void printLastMonthAverage() {
        LocalDate startOfTheMonth = day.minusDays(30);
        double lastMonthAverageWorkTime = statisticsCalculator.getAverageHoursWorkedIn(startOfTheMonth, day);
        outStream.println(langMap.getText("Average work time in last month") + ": "
                + formatDouble(lastMonthAverageWorkTime));
    }

    private String formatDouble(double number) {
        return String.format("%.2f", number);
    }
}
