package app.ui.terminal.output;

import app.backend.engine.Engine;
import app.lang.UiLangMap;
import app.record.ActivityMapWorkRecord;
import app.ui.terminal.RecentStatistics;

public class LinuxFrame extends AbstractTputFrame {
    private Engine appEngine;
    private UiLangMap langMap;
    private OutStream outStream;
    private ActivityMapWorkRecord lastDayRecord;
    private ActivityMapWorkRecord recordDayBeforeRecord;

    private TerminalFrameElement recentStatistics;
    private TerminalFrameElement progressBar;

    public LinuxFrame(int consoleWidth, int consoleHeight, Engine appEngine, UiLangMap langMap) {
        super(consoleWidth, consoleHeight);
        this.appEngine = appEngine;
        this.langMap = langMap;
        init();
    }

    @Override
    public void print() {
        printCurrentSessionData();
        printRecentStatistics();
        printProgressBar();
    }

    private void init() {
        //double lastDayWorkTime = Time.getHours(lastDayRecord.calculateTotalTime());
        //double dayBeforeWorkTime = Time.getHours()
        recentStatistics = new RecentStatistics(outStream, langMap, appEngine, 10);
        progressBar = new SimpleProgressBar(outStream, consoleWidth, 10, 15);
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
