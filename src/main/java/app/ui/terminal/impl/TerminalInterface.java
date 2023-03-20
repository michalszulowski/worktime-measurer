package app.ui.terminal.impl;

import app.backend.engine.ActivitiesEngine;
import app.backend.service.ActivitiesEngineService;
import app.backend.service.AppService;
import app.ui.AppInterface;
import app.ui.terminal.TerminalSettings;
import app.ui.terminal.impl.context.TerminalContext;
import concurrency.process.SimpleConcurrentProcess;

public abstract class TerminalInterface extends SimpleConcurrentProcess implements AppInterface {
    protected final ActivitiesEngineService appService;
    protected TerminalContext context;
    protected TerminalSettings terminalSettings;

    public TerminalInterface(TerminalSettings terminalSettings, ActivitiesEngineService appService) {
        super("TERMINAL_INTERFACE");
        this.appService = appService;
        this.terminalSettings = terminalSettings;
    }

    @Override
    public void show() {
        start();
    }

    public void switchContext(TerminalContext newContext) {
        context = newContext;
    }

    public TerminalSettings getSettings() {
        return terminalSettings;
    }

    public ActivitiesEngineService getAppService() {
        return appService;
    }
}
