package app.record;

import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Map with values representing some abstract relative wage of activity
 */
@EqualsAndHashCode
public class ActivityMap {
    private Map<Activity, Float> wageMap;
    private float wagesSum;

    public ActivityMap() {
        wageMap = new HashMap<>();
        wagesSum = 0;
    }

    public Float put(Activity key, Float value) {
        wagesSum += value;
        return wageMap.put(key, value);
    }

    public Collection<Activity> getActivities() {
        return wageMap.keySet();
    }

    public float getWage(Activity activity) {
        return wageMap.get(activity);
    }

    /**
     * @return values in <0, 1> range
     */
    public float getRelativeShare(Activity key) {
        float wage = wageMap.get(key);
        return wage/wagesSum;
    }
}
