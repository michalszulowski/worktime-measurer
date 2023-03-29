package app.ui.terminal.impl.command;

import app.record.ActivityMapWorkRecord;
import app.ui.terminal.impl.context.ShowingCurrentDayContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StartSessionCommand extends EditingActivityCommand {
    private String description;

    public StartSessionCommand(ShowingCurrentDayContext context, List<String> args) {
        super(context, args);
        expectArgsCount(1);
        description = arguments.get(0);
    }

    @Override
    public void invoke() {
        LocalDateTime time = appService.getTimeSupplier().getTime();
        appService.getEngine().addRecord(time.toLocalDate(), new ActivityMapWorkRecord(time, null, description));
    }
}
