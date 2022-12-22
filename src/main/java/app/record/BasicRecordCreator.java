package app.record;

import java.time.LocalDateTime;

public class BasicRecordCreator implements RecordCreator {
    private ActivityMapWorkRecord record;

    @Override
    public ActivityMapWorkRecord initRecord() {
        //record = new ActivityMapWorkRecord();
        //return record;
        return null;
    }

    @Override
    public void startRecord() {
        startRecordAt(LocalDateTime.now());
    }

    @Override
    public void startRecordAt(LocalDateTime at) {
        //record.start = at;
    }

    @Override
    public void endRecord() {
        endRecordAt(LocalDateTime.now());
    }

    @Override
    public void endRecordAt(LocalDateTime at) {
        //record.end = at;
    }

    @Override
    public void addActivity(Activity activity, float wage) {
        record.addActivity(activity, wage);
    }

    @Override
    public void setDescription(String description) {
        //record.description = description;
    }

    @Override
    public ActivityMapWorkRecord getRecord() {
        return record;
    }
}
