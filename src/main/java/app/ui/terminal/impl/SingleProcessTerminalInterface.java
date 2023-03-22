package app.ui.terminal.impl;

import app.backend.service.ActivitiesEngineService;
import app.ui.AppInterface;
import app.ui.terminal.service.TerminalSettings;
import app.ui.terminal.impl.context.TerminalContext;
import concurrency.process.SimpleConcurrentProcess;

public abstract class SingleProcessTerminalInterface extends SimpleConcurrentProcess implements AppInterface {
    protected final ActivitiesEngineService appService;
    protected TerminalContext<?> context;
    protected TerminalSettings terminalSettings;

    public SingleProcessTerminalInterface(TerminalSettings terminalSettings, ActivitiesEngineService appService) {
        super("TERMINAL_INTERFACE");
        this.appService = appService;
        this.terminalSettings = terminalSettings;
    }

    @Override
    public void show() {
        start();
    }

    public void switchContext(TerminalContext<?> newContext) {
        context = newContext;
    }

    public TerminalSettings getSettings() {
        return terminalSettings;
    }

    public ActivitiesEngineService getAppService() {
        return appService;
    }
}
