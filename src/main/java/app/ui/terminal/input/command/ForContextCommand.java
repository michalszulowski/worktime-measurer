package app.ui.terminal.input.command;

import app.ui.terminal.impl.context.TerminalContext;
import command.Command;

public abstract class ForContextCommand <T extends TerminalContext<?>> implements Command {
    protected final T callingContext;

    protected ForContextCommand(T callingContext) {
        this.callingContext = callingContext;
    }
}
