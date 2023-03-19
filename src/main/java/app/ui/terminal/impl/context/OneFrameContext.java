package app.ui.terminal.impl.context;

import app.ui.terminal.impl.OneFrameInterface;
import app.ui.terminal.impl.TerminalInterface;
import app.ui.terminal.output.frame.TerminalFrame;
import command.factory.CommandFactory;

public abstract class OneFrameContext implements TerminalContext {
    protected final OneFrameInterface owner;
    private final CommandFactory commandFactory;

    public OneFrameContext(OneFrameInterface owner, CommandFactory commandFactory) {
        this.owner = owner;
        this.commandFactory = commandFactory;
    }

    @Override
    public CommandFactory getCommandFactory() {
        return commandFactory;
    }

    @Override
    public void printFrame() {
        createFrame().print();
    }

    protected abstract TerminalFrame createFrame();
}
