package app.ui.terminal.impl.context;

import app.backend.engine.ActivitiesEngine;
import app.backend.time.TimeSupplier;
import app.ui.terminal.TerminalSettings;
import app.ui.terminal.TerminalSize;
import app.ui.terminal.impl.OneFrameInterface;
import app.ui.terminal.output.frame.CurrentDayFrame;
import app.ui.terminal.output.frame.TerminalFrame;
import command.factory.CommandFactory;
import command.factory.DictionaryCommandFactory;

import java.time.LocalDateTime;

public class ShowingCurrentDayContext extends OneFrameContext {
    public ShowingCurrentDayContext(OneFrameInterface owner) {
        super(owner, new ShowingCurrentDayCommandFactory());
    }

    @Override
    protected TerminalFrame createFrame() {
        TerminalSettings terminalSettings = owner.getSettings();
        ActivitiesEngine engine = owner.getAppService().getEngine();
        TimeSupplier timeSupplier = owner.getAppService().getTimeSupplier();
        TerminalSize size = terminalSettings.getSize();
        return new CurrentDayFrame(size.getWidth(), size.getHeight(), engine, owner.getLangMap(), timeSupplier.getTime());
    }

    private static class ShowingCurrentDayCommandFactory extends DictionaryCommandFactory {
        public ShowingCurrentDayCommandFactory() {
            //TODO add commands
        }
    }

}
