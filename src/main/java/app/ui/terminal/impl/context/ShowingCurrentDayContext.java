package app.ui.terminal.impl.context;

import app.backend.engine.ActivitiesEngine;
import app.backend.time.TimeSupplier;
import app.ui.terminal.service.TerminalSettings;
import app.ui.terminal.service.TerminalSize;
import app.ui.terminal.impl.OneFrameInterface;
import app.ui.terminal.input.command.ForContextFactory;
import app.ui.terminal.impl.SwitchContextCommand;
import app.ui.terminal.impl.CurrentDayFrame;
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
        ActivitiesEngine engine = owner.getAppService().getEngine();
        TimeSupplier timeSupplier = owner.getAppService().getTimeSupplier();
        return new CurrentDayFrame(getOwner().getTerminalService(), engine, owner.getLangMap(), timeSupplier.getTime());
    }

    private static class ShowingCurrentDayCommandFactory extends ForContextFactory<ShowingCurrentDayContext> {
        public ShowingCurrentDayCommandFactory(ShowingCurrentDayContext context) {
            super(context);
            //TODO implement
            addCommand("start-session", l -> null);
            addCommand("finish-session", l -> null);
            addCommand("add-activity", l -> null);
            addCommand("ok", l -> null);
            addCommand("quit", l -> null);
        }

        @Override
        protected Command noCommandFoundHandler(String enteredCommand) {
            return new SwitchContextCommand(
                    context, new WrongCommandContext(context.getOwner(), context, enteredCommand));
        }
    }

}
