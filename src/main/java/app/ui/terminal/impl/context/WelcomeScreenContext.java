package app.ui.terminal.impl.context;

import app.ui.terminal.impl.OneFrameInterface;
import app.ui.terminal.impl.command.*;
import app.ui.terminal.impl.frame.WelcomeFrame;
import app.ui.terminal.input.command.ForContextFactory;
import app.ui.terminal.output.frame.TerminalFrame;
import command.factory.CommandFactory;

public class WelcomeScreenContext extends OneFrameContext {
    private final CommandFactory commandFactory;

    public WelcomeScreenContext(OneFrameInterface owner) {
        super(owner);
        commandFactory = new WelcomeScreenCommandFactory(this);
    }

    @Override
    protected TerminalFrame createFrame() {
        return new WelcomeFrame(getOwner().getTerminalService(), getOwner().getAppService(), getOwner().getLangMap());
    }

    @Override
    public CommandFactory getCommandFactory() {
        return commandFactory;
    }

    //command open-day
    private static class WelcomeScreenCommandFactory extends ForContextFactory<WelcomeScreenContext> {
        public WelcomeScreenCommandFactory(WelcomeScreenContext context) {
            super(context);
            addCommand("open-day", args -> new OpenDayCommand(context, args));
        }
    }
}
