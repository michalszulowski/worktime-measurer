package app.impl;

import app.MeasurerApp;
import app.backend.engine.ActivitiesEngine;
import app.backend.service.ActivitiesEngineService;
import app.backend.service.ActivitiesEngineServiceImpl;
import app.backend.time.CurrentTimeSupplier;
import app.lang.DirectLangMap;
import app.ui.AppInterface;
import app.ui.terminal.impl.OneFrameInterface;
import app.ui.terminal.service.TerminalService;

public class OneFrameTerminalApp implements MeasurerApp {
    private final ActivitiesEngine appEngine;
    private final TerminalService terminalService;
    private AppInterface userInterface;
    private ActivitiesEngineService appService;

    public OneFrameTerminalApp(ActivitiesEngine appEngine, TerminalService terminalService) {
        this.appEngine = appEngine;
        this.terminalService = terminalService;
    }

    @Override
    public void start() {
        userInterface = createTerminalInterface();
        userInterface.show();
    }

    private AppInterface createTerminalInterface() {
        appService = new ActivitiesEngineServiceImpl(appEngine, new CurrentTimeSupplier());
        return new OneFrameInterface(terminalService, appService, new DirectLangMap());
    }
}
