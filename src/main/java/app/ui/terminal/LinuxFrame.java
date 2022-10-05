package app.ui.terminal;

import app.backend.engine.Engine;
import app.lang.UiLangMap;
import app.record.WorkRecord;
import app.util.Time;

public class LinuxFrame extends AbstractTputFrame {
    private Engine appEngine;
    private UiLangMap langMap;
    private OutStream outStream;
    private WorkRecord lastDayRecord;
    private double lastDayWorkTime;
    private TerminalFrameElement progressBar;
    private TerminalFrameElement recentStatistics;



    @Override
    public void print() {
        printCurrentSessionData();
        printRecentStatistics();
        printProgressBar();
    }

    private void init() {
        lastDayWorkTime = Time.getHours(lastDayRecord.calculateTotalTime());
    }

    private void printCurrentSessionData() {

    }

    private void printRecentStatistics() {
        recentStatistics.print();
    }

    private void printProgressBar() {

        progressBar.print();
    }


}
