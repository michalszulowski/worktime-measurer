package app.ui.terminal.impl.command;

import app.day.WorkDayWithActivities;
import app.record.ActivityMapWorkRecord;
import app.ui.terminal.impl.context.ShowingCurrentDayContext;

import java.util.List;

public abstract class EditingActivityCommand extends ForContextCommand<ShowingCurrentDayContext> {
    public EditingActivityCommand(ShowingCurrentDayContext context, List<String> args) {
        super(context, args);
    }

    protected WorkDayWithActivities getActiveDay() {
        return appService.getActiveDay()
                .orElseThrow(() -> new IllegalStateException("No active day"));
    }

    protected ActivityMapWorkRecord getActiveRecord(WorkDayWithActivities day) {
        return day.getActiveRecord()
                .orElseThrow(() -> new IllegalStateException("No active session"));
    }
}
