package app.ui.terminal.impl;

import app.backend.concurrency.process.SimpleConcurrentProcess;
import app.ui.AppInterface;
import app.ui.terminal.TerminalSettings;
import app.ui.terminal.impl.context.TerminalContext;

public abstract class TerminalInterface extends SimpleConcurrentProcess implements AppInterface {
    protected TerminalContext context;
    protected TerminalSettings terminalSettings;

    public TerminalInterface(TerminalSettings terminalSettings) {
        super("TERMINAL_INTERFACE");
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
}
