package app;

import java.time.LocalDateTime;

public interface RecordCreator {
    void startRecord();
    void startRecordAt(LocalDateTime at);
    void endRecord();
    void endRecordAt(LocalDateTime at);
    void addActivity(Activity activity, float wage);
    void setDescription(String description);
    WorkRecord getRecord();


}
