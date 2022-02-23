package app;

import java.time.LocalDateTime;

public class BasicRecordCreator implements RecordCreator {
    private WorkRecord createdRecord;

    @Override
    public void startRecord() {
        startRecordAt(LocalDateTime.now());
    }

    @Override
    public void startRecordAt(LocalDateTime at) {
        createdRecord.setStart(at);
    }

    @Override
    public void endRecord() {
        endRecordAt(LocalDateTime.now());
    }

    @Override
    public void endRecordAt(LocalDateTime at) {
        createdRecord.setEnd(at);
    }

    @Override
    public void addActivity(Activity activity, float wage) {
        createdRecord.addActivity(activity, wage);
    }

    @Override
    public void setDescription(String description) {
        createdRecord.setDescription(description);
    }

    @Override
    public WorkRecord getRecord() {
        return createdRecord;
    }
}
