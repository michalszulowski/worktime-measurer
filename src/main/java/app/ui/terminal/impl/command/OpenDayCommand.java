package app.ui.terminal.impl.command;

import app.ui.terminal.impl.SingleProcessTerminalInterface;
import app.ui.terminal.impl.context.ShowingCurrentDayContext;
import app.ui.terminal.impl.context.WelcomeScreenContext;

import java.time.LocalDate;
import java.util.List;

public class OpenDayCommand extends ForContextCommand<WelcomeScreenContext> {
    private LocalDate day;

    public OpenDayCommand(WelcomeScreenContext context, List<String> args) {
        super(context, args);
        //TODO add passing day
    }

    @Override
    public void invoke() {
        var terminalInterface = context.getOwner();
        terminalInterface.switchContext(new ShowingCurrentDayContext(terminalInterface));
    }
}
