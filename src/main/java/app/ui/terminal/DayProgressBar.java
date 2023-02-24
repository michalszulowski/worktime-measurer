package app.ui.terminal;

import app.backend.statistics.StatisticsCalculator;
import app.lang.UiLangMap;
import app.ui.terminal.output.AppFrame;
import app.ui.terminal.output.OutStream;
import app.ui.terminal.output.SimpleProgressBar;

import java.time.LocalDate;

public class DayProgressBar extends BasicTerminalFrameElement {
    private SimpleProgressBar progressBar;
    private int consoleWidth;
    private LocalDate day;
    private StatisticsCalculator statisticsCalculator;
    private double todaysWorkTime;
    private double lastWeekAverageWorkTime;

    public DayProgressBar(AppFrame parent, LocalDate day) {
        super(parent);
        this.consoleWidth = parent.getWidth();
        this.day = day;
        this.statisticsCalculator = parent.getStatisticsCalculator();
    }

    @Override
    public void print() {
        calculateWorkTimes();
        outStream.println(langMap.getText("Progress in reference to last week:"));
        showProgressBar();
    }

    private void calculateWorkTimes() {
        todaysWorkTime = statisticsCalculator.getTotalHoursWorkedIn(day, day);
        lastWeekAverageWorkTime = statisticsCalculator
                .getAverageHoursWorkedIn(day.minusDays(7), day.minusDays(1));
    }

    private void showProgressBar() {
        progressBar = new SimpleProgressBar(outStream, consoleWidth, todaysWorkTime, lastWeekAverageWorkTime);
        progressBar.print();
    }
}
