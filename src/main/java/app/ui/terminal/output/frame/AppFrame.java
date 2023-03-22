package app.ui.terminal.output.frame;

import app.backend.engine.ActivitiesEngine;
import app.backend.statistics.StatisticsCalculator;
import app.backend.statistics.WorkDayWithActivitiesStatisticsCalculator;
import app.lang.UiLangMap;
import app.ui.terminal.service.TerminalService;

public abstract class AppFrame extends AbstractTerminalFrame {
    protected ActivitiesEngine appEngine;
    protected UiLangMap langMap;
    protected StatisticsCalculator statisticsCalculator;

    public AppFrame(TerminalService terminalService, ActivitiesEngine appEngine, UiLangMap langMap) {
        super(terminalService);
        this.appEngine = appEngine;
        this.langMap = langMap;
        statisticsCalculator = new WorkDayWithActivitiesStatisticsCalculator(appEngine);
    }

    public UiLangMap getLangMap() {
        return langMap;
    }

    public StatisticsCalculator getStatisticsCalculator() {
        return statisticsCalculator;
    }
}
