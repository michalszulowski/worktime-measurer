package app.ui.terminal.impl.context;

import app.ui.terminal.impl.OneFrameInterface;
import app.ui.terminal.output.frame.TerminalFrame;
import command.factory.CommandFactory;

public class WrongCommandContext extends OneFrameContext {
    public WrongCommandContext(OneFrameInterface owner, CommandFactory commandFactory) {
        super(owner, commandFactory);
    }

    @Override
    protected TerminalFrame createFrame() {
        return null;
    }
}
