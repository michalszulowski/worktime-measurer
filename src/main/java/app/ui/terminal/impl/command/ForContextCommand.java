package app.ui.terminal.impl.command;

import app.backend.service.ActivitiesEngineService;
import app.ui.terminal.impl.context.TerminalContext;
import command.Command;

import java.util.List;

public abstract class ForContextCommand <T extends TerminalContext<?>> implements Command {
    protected final T context;
    protected final List<String> args;
    protected final ActivitiesEngineService appService;

    public ForContextCommand(T context, List<String> args) {
        this.context = context;
        this.args = args;
        appService = context.getOwner().getAppService();
    }
}
