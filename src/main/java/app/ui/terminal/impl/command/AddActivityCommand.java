package app.ui.terminal.impl.command;

import app.day.WorkDayWithActivities;
import app.record.Activity;
import app.record.ActivityMapWorkRecord;
import app.record.RecordCreator;
import app.ui.terminal.impl.context.ShowingCurrentDayContext;

import java.time.LocalDateTime;
import java.util.List;

public class AddActivityCommand extends ForContextCommand<ShowingCurrentDayContext>{
    private String description;
    private float wage;

    public AddActivityCommand(ShowingCurrentDayContext context, List<String> args) {
        super(context, args);
        expectArgsCount(2);
        description = arguments.get(0);
        wage = Float.parseFloat(arguments.get(1));
    }

    @Override
    public void invoke() {
        //TODO what with situation, where command call starts at day before, but getActiveDay happens on the next day?
        LocalDateTime time = appService.getTimeSupplier().getTime();
        WorkDayWithActivities activeDay = getActiveDay();
        ActivityMapWorkRecord workRecord = getActiveRecord(activeDay);
        Activity activity = parseActivity();
        workRecord.addActivity(activity, wage);
        appService.getEngine().putDay(activeDay.getDate(), activeDay);
    }

    private WorkDayWithActivities getActiveDay() {
        return appService.getActiveDay()
                .orElseThrow(() -> new IllegalStateException("No active day"));
    }

    private ActivityMapWorkRecord getActiveRecord(WorkDayWithActivities day) {
        return day.getActiveRecord()
                .orElseThrow(() -> new IllegalStateException("No active session"));
    }

    private Activity parseActivity() {
        return new Activity(description);
    }
}
