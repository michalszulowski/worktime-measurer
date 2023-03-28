package app.ui.terminal.impl;

import app.backend.service.ActivitiesEngineService;
import app.ui.AppInterface;
import app.ui.terminal.service.TerminalService;
import app.ui.terminal.impl.context.TerminalContext;
import concurrency.process.SimpleConcurrentProcess;

public abstract class SingleProcessTerminalInterface extends SimpleConcurrentProcess implements AppInterface {
    protected final ActivitiesEngineService appService;
    protected TerminalContext<?> context;
    protected TerminalService terminalService;

    public SingleProcessTerminalInterface(TerminalService terminalService, ActivitiesEngineService appService) {
        super("TERMINAL_INTERFACE");
        this.appService = appService;
        this.terminalService = terminalService;
    }

    @Override
    public void show() {
        start();
    }

    public void switchContext(TerminalContext<?> newContext) {
        context = newContext;
    }

    public TerminalService getSettings() {
        return terminalService;
    }

    public ActivitiesEngineService getAppService() {
        return appService;
    }
}
