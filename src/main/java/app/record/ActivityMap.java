package app.record;

import java.util.HashMap;
import java.util.Map;

/**
 * Map with values representing some abstract relative wage of activity
 */
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

    /**
     * @return values in <0, 1> range
     */
    public float getRelativeShare(Activity key) {
        float wage = wageMap.get(key);
        return wage/wagesSum;
    }
}
