package app.ui.terminal.impl.context;

import app.ui.terminal.impl.OneFrameInterface;
import app.ui.terminal.impl.frame.WelcomeFrame;
import app.ui.terminal.output.frame.TerminalFrame;
import command.factory.CommandFactory;

public class WelcomeScreenContext extends OneFrameContext {

    public WelcomeScreenContext(OneFrameInterface owner) {
        super(owner);
    }

    @Override
    protected TerminalFrame createFrame() {
        return new WelcomeFrame(getOwner().getTerminalService(), getOwner().getAppService(), getOwner().getLangMap());
    }

    @Override
    public CommandFactory getCommandFactory() {
        return null;
    }


}
