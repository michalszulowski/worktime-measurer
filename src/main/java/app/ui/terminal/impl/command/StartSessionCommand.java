package app.ui.terminal.impl.command;

import app.record.ActivityMapWorkRecord;
import app.ui.terminal.impl.context.ShowingCurrentDayContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StartSessionCommand extends ForContextCommand<ShowingCurrentDayContext> {

    public StartSessionCommand(ShowingCurrentDayContext context, List<String> args) {
        super(context, args);
    }

    @Override
    public void invoke() {
        String description = args.get(0);
        LocalDateTime time = appService.getTimeSupplier().getTime();
        appService.getEngine().addRecord(time.toLocalDate(), new ActivityMapWorkRecord(time, null, description));
    }
}
