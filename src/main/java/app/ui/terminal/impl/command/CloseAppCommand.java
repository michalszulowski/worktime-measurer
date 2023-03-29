package app.ui.terminal.impl.command;

import app.ui.terminal.impl.OneFrameInterface;
import app.ui.terminal.impl.context.ShowingCurrentDayContext;
import app.ui.terminal.impl.context.TerminalContext;

import java.util.List;

public class CloseAppCommand extends ForContextCommand<TerminalContext<? extends OneFrameInterface>>{
    public CloseAppCommand(TerminalContext<? extends OneFrameInterface> context, List<String> args) {
        super(context, args);
    }

    @Override
    public void invoke() {
        context.getOwner().getExecutionController().kill();
    }
}
