package app.ui.terminal.impl.context;

import app.ui.terminal.TerminalSettings;
import app.ui.terminal.impl.OneFrameInterface;
import app.ui.terminal.output.frame.CurrentDayFrame;
import app.ui.terminal.output.frame.TerminalFrame;
import command.factory.CommandFactory;

import java.time.LocalDateTime;

public class ShowingCurrentDayContext extends OneFrameContext {
    public ShowingCurrentDayContext(OneFrameInterface owner) {
        super(owner, commandFactory);
    }

    @Override
    protected TerminalFrame createFrame() {
        TerminalSettings terminalSettings = owner.getSettings();
        return new CurrentDayFrame(terminalSettings.getColumnsCount(), terminalSettings.getRowsCount(),
                owner.getAppEngine(), owner.getLangMap(), LocalDateTime.now()); //TODO add time supplier instead of now()
    }

    //TODO think how to pass command factory
    //private static class ShowingCurrentDayCommandFactory

}
