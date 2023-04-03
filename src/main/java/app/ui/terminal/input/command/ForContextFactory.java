package app.ui.terminal.input.command;

import app.ui.terminal.impl.OneFrameInterface;
import app.ui.terminal.impl.command.CloseAppCommand;
import app.ui.terminal.impl.command.SwitchContextCommand;
import app.ui.terminal.impl.context.OneFrameContext;
import app.ui.terminal.impl.context.TerminalContext;
import app.ui.terminal.impl.context.WrongCommandContext;
import command.ArgsCommand;
import command.Command;
import command.NotEnoughArgsProvidedException;
import command.factory.DictionaryCommandFactory;
import command.factory.NoSuchCommandException;

import java.text.ParseException;

public abstract class ForContextFactory <T extends OneFrameContext> extends DictionaryCommandFactory {
    protected T context;

    public ForContextFactory(T context) {
        this.context = context;
        addCommand("exit", args -> new CloseAppCommand(context, args));
    }

    @Override
    public Command getCommand(String ofInput) {
        try {
            return super.getCommand(ofInput);
        } catch (NoSuchCommandException ex) {
            return getNoCommandFoundCommand(ex.getMessage());
        } catch (NotEnoughArgsProvidedException ex) {
            return getNotEnoughArgsProvidedCommand(ex);
        } catch (Exception ex) {
            return getGeneralExceptionCommand(ex);
        }
    }

    protected Command getNoCommandFoundCommand(String enteredCommand) {
        return createSwitchContextCommandOfMessage(enteredCommand);
    }

    private Command getNotEnoughArgsProvidedCommand(NotEnoughArgsProvidedException exception) {
        return createSwitchContextCommandOfMessage(exception.getMessage());
    }

    private Command getParseErrorMessageCommand() {
        String message = "Error while parsing command parameter(s). Check documentation for expected parameters types.";
        return createSwitchContextCommandOfMessage(message);
    }

    private Command getGeneralExceptionCommand(Exception exception) {
        String message = exception.toString();
        return createSwitchContextCommandOfMessage(message);
    }

    private SwitchContextCommand createSwitchContextCommandOfMessage(String message) {
        return new SwitchContextCommand(
                context, new WrongCommandContext(context.getOwner(), context, message));
    }
}
