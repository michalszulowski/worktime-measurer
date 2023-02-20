package app.record;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Activity {
    private String description;
    //TODO think about categories

    public Activity(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
