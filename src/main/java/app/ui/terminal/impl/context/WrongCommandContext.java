package app.ui.terminal.impl.context;

import app.backend.engine.ActivitiesEngine;
import app.ui.terminal.service.TerminalSettings;
import app.ui.terminal.service.TerminalSize;
import app.ui.terminal.impl.OneFrameInterface;
import app.ui.terminal.input.command.ForContextFactory;
import app.ui.terminal.impl.SwitchContextCommand;
import app.ui.terminal.output.frame.TerminalFrame;
import command.Command;
import command.factory.CommandFactory;

public class WrongCommandContext extends OneFrameContext {
    private OneFrameContext previousContext;
    private String commandEntered;
    private final CommandFactory commandFactory;

    public WrongCommandContext(OneFrameInterface owner, OneFrameContext previousContext, String commandEntered) {
        super(owner);
        this.previousContext = previousContext;
        this.commandEntered = commandEntered;
        commandFactory = new WrongCommandCommandFactory(this);
    }

    @Override
    protected TerminalFrame createFrame() {
        TerminalSettings terminalSettings = owner.getSettings();
        ActivitiesEngine engine = owner.getAppService().getEngine();
        TerminalSize size = terminalSettings.getSize();
        return new WrongCommandFrame(size.getWidth(), size.getHeight(), engine, owner.getLangMap(), commandEntered);
    }

    @Override
    public CommandFactory getCommandFactory() {
        return commandFactory;
    }

    public OneFrameContext getPreviousContext() {
        return previousContext;
    }

    private static class WrongCommandCommandFactory extends ForContextFactory<WrongCommandContext> {
        public WrongCommandCommandFactory(WrongCommandContext context) {
            super(context);
        }

        @Override
        protected Command noCommandFoundHandler(String enteredCommand) {
            return new SwitchContextCommand(context, context.getPreviousContext());
        }
    }
}
