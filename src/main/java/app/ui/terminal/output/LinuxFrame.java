package app.ui.terminal.output;

import app.backend.engine.ActivitiesEngine;
import app.backend.engine.impl.local.LocalFilesystemEngine;
import app.lang.DirectLangMap;
import app.lang.UiLangMap;
import app.ui.terminal.RecentStatistics;

import java.nio.file.Paths;
import java.time.LocalDate;

public class LinuxFrame extends AbstractTputFrame {
    private ActivitiesEngine appEngine;
    private UiLangMap langMap;
    private OutStream outStream;
    private TerminalFrameElement sessionData;
    private TerminalFrameElement recentStatistics;
    private TerminalFrameElement progressBar;

    public LinuxFrame(int consoleWidth, int consoleHeight, ActivitiesEngine appEngine, UiLangMap langMap) {
        super(consoleWidth, consoleHeight);
        this.appEngine = appEngine;
        this.langMap = langMap;
        outStream = new TerminalOutStream();
        initFrameElements();
    }

    @Override
    public void print() {
        //printCurrentSessionData();
        printRecentStatistics();
        printProgressBar();
    }

    private void initFrameElements() {
        //TODO handle empty
        //WorkDayWithActivities today = appEngine.getDay(LocalDate.now()).get();
        //today.getRecords().iterator().
        //LocalDateTime now = LocalDateTime.now();
        //sessionData = new SessionData(outStream, langMap, );
        recentStatistics = new RecentStatistics(outStream, langMap, LocalDate.now(), appEngine);
        progressBar = new SimpleProgressBar(outStream, consoleWidth, 10, 15);
    }

    private void printCurrentSessionData() {
        sessionData.print();
    }

    private void printRecentStatistics() {
        recentStatistics.print();
    }

    private void printProgressBar() {
        progressBar.print();
    }

    public static void main(String[] args) {
        String documentsPath = System.getProperty("user.dir") + "/Documents/";
        ActivitiesEngine engine = new LocalFilesystemEngine(Paths.get(documentsPath));
        LinuxFrame frame = new LinuxFrame(40, 80, engine, new DirectLangMap());
        frame.print();
    }

}
