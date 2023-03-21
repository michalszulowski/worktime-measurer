package app.ui.terminal.input.command;

import app.ui.terminal.impl.context.TerminalContext;
import command.Command;
import command.factory.DictionaryCommandFactory;
import command.factory.NoSuchCommandException;

public abstract class ForContextFactory <T extends TerminalContext<?>> extends DictionaryCommandFactory {
    private T context;

    public ForContextFactory(T context) {
        this.context = context;
    }

    @Override
    public Command getCommand(String ofInput) {
        try {
            return super.getCommand(ofInput);
        } catch (NoSuchCommandException ex) {
            return noCommandFoundHandler(ex.getMessage());
        }
    }

    protected abstract Command noCommandFoundHandler(String info);
}
