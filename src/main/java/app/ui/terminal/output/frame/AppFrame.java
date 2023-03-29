package app.ui.terminal.output.frame;

import app.backend.engine.ActivitiesEngine;
import app.backend.service.ActivitiesEngineService;
import app.backend.service.AppService;
import app.backend.statistics.StatisticsCalculator;
import app.backend.statistics.WorkDayWithActivitiesStatisticsCalculator;
import app.lang.UiLangMap;
import app.ui.terminal.output.OutStream;
import app.ui.terminal.service.TerminalService;
import app.util.TextUtils;

public abstract class AppFrame extends AbstractTerminalFrame {
    protected ActivitiesEngineService appService;
    protected ActivitiesEngine appEngine;
    protected UiLangMap langMap;
    protected StatisticsCalculator statisticsCalculator;
    protected OutStream outStream;

    public AppFrame(TerminalService terminalService, ActivitiesEngineService appService, UiLangMap langMap) {
        super(terminalService);
        this.appService = appService;
        this.appEngine = appService.getEngine();
        this.langMap = langMap;
        this.outStream = terminalService.getOutStream();
        statisticsCalculator = new WorkDayWithActivitiesStatisticsCalculator(appEngine);
    }

    public UiLangMap getLangMap() {
        return langMap;
    }

    public StatisticsCalculator getStatisticsCalculator() {
        return statisticsCalculator;
    }

    @Override
    public void print() {
        printContent();
        printClosingLines();
    }

    protected abstract void printContent();

    private void printClosingLines() {
        final int BLANK_SPACES_COUNT = 2;
        final String PROMPT_LINE = "*------*";
        final String PROMPT = "> ";
        String emptyLines = TextUtils.generateCharSeq('\n', BLANK_SPACES_COUNT);
        outStream.print(emptyLines);
        outStream.println(PROMPT_LINE);
        outStream.print(PROMPT);
    }
}
