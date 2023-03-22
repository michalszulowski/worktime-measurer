package app.ui.terminal.impl;

import app.ui.terminal.impl.SingleProcessTerminalInterface;
import app.ui.terminal.impl.context.TerminalContext;
import app.ui.terminal.input.command.ForContextCommand;
import command.Command;

public class SwitchContextCommand extends ForContextCommand<TerminalContext<?>> {
    private final TerminalContext<?> newContext;

    public SwitchContextCommand(TerminalContext<?> callingContext, TerminalContext<?> newContext) {
        super(callingContext);
        this.newContext = newContext;
    }

    @Override
    public void invoke() {
        callingContext.getOwner().switchContext(newContext);
    }
}
