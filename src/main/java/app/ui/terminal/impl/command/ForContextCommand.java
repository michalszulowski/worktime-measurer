package app.ui.terminal.impl.command;

import app.backend.service.ActivitiesEngineService;
import app.ui.terminal.impl.context.TerminalContext;
import command.ArgsCommand;
import command.Command;

import java.util.List;

public abstract class ForContextCommand <T extends TerminalContext<?>> extends ArgsCommand {
    protected final T context;
    protected final ActivitiesEngineService appService;

    public ForContextCommand(T context, List<String> args) {
        super(args);
        this.context = context;
        appService = context.getOwner().getAppService();
    }
}
