package app.ui.terminal.output.frame;

import app.backend.engine.ActivitiesEngine;
import app.backend.service.ActivitiesEngineService;
import app.backend.service.AppService;
import app.backend.statistics.StatisticsCalculator;
import app.backend.statistics.WorkDayWithActivitiesStatisticsCalculator;
import app.lang.UiLangMap;
import app.ui.terminal.service.TerminalService;

public abstract class AppFrame extends AbstractTerminalFrame {
    protected ActivitiesEngineService appService;
    protected ActivitiesEngine appEngine;
    protected UiLangMap langMap;
    protected StatisticsCalculator statisticsCalculator;

    public AppFrame(TerminalService terminalService, ActivitiesEngineService appService, UiLangMap langMap) {
        super(terminalService);
        this.appService = appService;
        this.appEngine = appService.getEngine();
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
