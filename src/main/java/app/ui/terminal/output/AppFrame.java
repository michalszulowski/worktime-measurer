package app.ui.terminal.output;

import app.backend.engine.ActivitiesEngine;
import app.backend.statistics.StatisticsCalculator;
import app.backend.statistics.WorkDayWithActivitiesStatisticsCalculator;
import app.lang.UiLangMap;

public abstract class AppFrame extends AbstractTerminalFrame {
    protected ActivitiesEngine appEngine;
    protected UiLangMap langMap;
    protected OutStream outStream;
    protected StatisticsCalculator statisticsCalculator;

    public AppFrame(int width, int height, ActivitiesEngine appEngine, UiLangMap langMap) {
        super(width, height);
        this.appEngine = appEngine;
        this.langMap = langMap;
        outStream = new TerminalOutStream();
        statisticsCalculator = new WorkDayWithActivitiesStatisticsCalculator(appEngine);
    }

    public UiLangMap getLangMap() {
        return langMap;
    }

    public OutStream getOutStream() {
        return outStream;
    }

    public StatisticsCalculator getStatisticsCalculator() {
        return statisticsCalculator;
    }
}
