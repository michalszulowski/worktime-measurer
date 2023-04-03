package app.ui.terminal.impl.command;

import app.ui.terminal.impl.SingleProcessTerminalInterface;
import app.ui.terminal.impl.context.TerminalContext;
import app.ui.terminal.impl.command.ForContextCommand;
import command.Command;

import java.util.Collections;

public class SwitchContextCommand extends ForContextCommand<TerminalContext<?>> {
    private final TerminalContext<?> newContext;

    public SwitchContextCommand(TerminalContext<?> callingContext, TerminalContext<?> newContext) {
        super(callingContext, Collections.emptyList());
        this.newContext = newContext;
    }

    @Override
    public void invoke() {
        context.getOwner().switchContext(newContext);
    }
}
