package app.ui.terminal.impl.command;

import app.day.WorkDayWithActivities;
import app.record.ActivityMapWorkRecord;
import app.ui.terminal.impl.context.ShowingCurrentDayContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FinishSessionCommand extends EditingActivityCommand {

    public FinishSessionCommand(ShowingCurrentDayContext context, List<String> args) {
        super(context, args);
    }

    @Override
    public void invoke() {
        LocalDateTime endTime = appService.getTimeSupplier().getTime();
        WorkDayWithActivities activeDay = getActiveDay();
        ActivityMapWorkRecord workRecord =getActiveRecord(activeDay);
        workRecord.setEnd(endTime);
        appService.getEngine().putDay(activeDay.getDate(), activeDay);
    }
}
