package app.ui.terminal.input.command;

import app.ui.terminal.impl.OneFrameInterface;
import app.ui.terminal.impl.command.CloseAppCommand;
import app.ui.terminal.impl.command.SwitchContextCommand;
import app.ui.terminal.impl.context.OneFrameContext;
import app.ui.terminal.impl.context.TerminalContext;
import app.ui.terminal.impl.context.WrongCommandContext;
import command.Command;
import command.factory.DictionaryCommandFactory;
import command.factory.NoSuchCommandException;

public abstract class ForContextFactory <T extends OneFrameContext> extends DictionaryCommandFactory {
    protected T context;

    public ForContextFactory(T context) {
        this.context = context;
        addCommand("quit", args -> new CloseAppCommand(context, args));
    }

    @Override
    public Command getCommand(String ofInput) {
        try {
            return super.getCommand(ofInput);
        } catch (NoSuchCommandException ex) {
            return getNoCommandFoundCommand(ex.getMessage());
        }
    }

    protected Command getNoCommandFoundCommand(String enteredCommand) {
        return new SwitchContextCommand(
                context, new WrongCommandContext(context.getOwner(), context, enteredCommand));
    }
}
