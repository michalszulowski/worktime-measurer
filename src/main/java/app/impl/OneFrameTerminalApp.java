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
    private final TerminalService terminalService;
    private final ActivitiesEngine appEngine;
    private AppInterface userInterface;
    private ActivitiesEngineService appService;

    public OneFrameTerminalApp(TerminalService terminalService, ActivitiesEngine appEngine) {
        this.terminalService = terminalService;
        this.appEngine = appEngine;
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
