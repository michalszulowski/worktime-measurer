package app.ui.terminal.impl.context;

import app.ui.terminal.impl.OneFrameInterface;
import app.ui.terminal.output.frame.TerminalFrame;

public abstract class OneFrameContext implements TerminalContext<OneFrameInterface> {
    protected final OneFrameInterface owner;

    public OneFrameContext(OneFrameInterface owner) {
        this.owner = owner;
    }

    @Override
    public void printFrame() {
        createFrame().print();
    }

    protected abstract TerminalFrame createFrame();

    @Override
    public OneFrameInterface getOwner() {
        return owner;
    }
}
