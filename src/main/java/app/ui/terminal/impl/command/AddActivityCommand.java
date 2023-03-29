package app.ui.terminal.impl.command;

import app.ui.terminal.impl.context.ShowingCurrentDayContext;

import java.time.LocalDateTime;
import java.util.List;

public class AddActivityCommand extends ForContextCommand<ShowingCurrentDayContext>{

    public AddActivityCommand(ShowingCurrentDayContext context, List<String> args) {
        super(context, args);
    }

    @Override
    public void invoke() {
        String description = args.get(0);
        float wage = Float.parseFloat(args.get(1));
        LocalDateTime time = appService.getTimeSupplier().getTime();
        //appService.getActiveDay()
    }
}
