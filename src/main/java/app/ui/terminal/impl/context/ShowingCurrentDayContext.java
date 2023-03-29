package app.ui.terminal.impl.context;

import app.backend.time.TimeSupplier;
import app.ui.terminal.impl.command.*;
import app.ui.terminal.impl.OneFrameInterface;
import app.ui.terminal.input.command.ForContextFactory;
import app.ui.terminal.impl.command.SwitchContextCommand;
import app.ui.terminal.impl.frame.CurrentDayFrame;
import app.ui.terminal.output.frame.TerminalFrame;
import command.Command;
import command.factory.CommandFactory;

public class ShowingCurrentDayContext extends OneFrameContext {
    private final CommandFactory commandFactory;

    public ShowingCurrentDayContext(OneFrameInterface owner) {
        super(owner);
        commandFactory = new ShowingCurrentDayCommandFactory(this);
    }

    @Override
    public CommandFactory getCommandFactory() {
        return commandFactory;
    }

    @Override
    protected TerminalFrame createFrame() {
        TimeSupplier timeSupplier = owner.getAppService().getTimeSupplier();
        return new CurrentDayFrame(getOwner().getTerminalService(), owner.getAppService(), owner.getLangMap(), timeSupplier.getTime());
    }

    private static class ShowingCurrentDayCommandFactory extends ForContextFactory<ShowingCurrentDayContext> {
        public ShowingCurrentDayCommandFactory(ShowingCurrentDayContext context) {
            super(context);
            addCommand("start-session", args -> new StartSessionCommand(context, args));
            addCommand("finish-session", args -> new FinishSessionCommand(context, args));
            addCommand("add-activity", args -> new AddActivityCommand(context, args));
            //TODO exit day
            //addCommand("ok", l -> null);
            addCommand("refresh", args -> new EmptyCommand());
        }

        @Override
        protected Command getNoCommandFoundCommand(String enteredCommand) {
            return new SwitchContextCommand(
                    context, new WrongCommandContext(context.getOwner(), context, enteredCommand));
        }
    }

}
