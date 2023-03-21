package app.ui.terminal.impl.context;

import app.backend.engine.ActivitiesEngine;
import app.backend.time.TimeSupplier;
import app.ui.terminal.TerminalSettings;
import app.ui.terminal.TerminalSize;
import app.ui.terminal.impl.OneFrameInterface;
import app.ui.terminal.input.command.ForContextFactory;
import app.ui.terminal.output.frame.CurrentDayFrame;
import app.ui.terminal.output.frame.TerminalFrame;
import command.Command;

public class ShowingCurrentDayContext extends OneFrameContext {
    public ShowingCurrentDayContext(OneFrameInterface owner) {
        super(owner, new ShowingCurrentDayCommandFactory(null)); //TODO pass this to factory
    }

    @Override
    protected TerminalFrame createFrame() {
        TerminalSettings terminalSettings = owner.getSettings();
        ActivitiesEngine engine = owner.getAppService().getEngine();
        TimeSupplier timeSupplier = owner.getAppService().getTimeSupplier();
        TerminalSize size = terminalSettings.getSize();
        return new CurrentDayFrame(size.getWidth(), size.getHeight(), engine, owner.getLangMap(), timeSupplier.getTime());
    }

    //TODO thing about changing commands name based on UiLangMap

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
        protected Command noCommandFoundHandler(String info) {
            return null; //TODO implement
        }
    }

}
