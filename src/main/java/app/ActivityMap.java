package app;

import java.util.HashMap;

//Map with values representing some abstract relative wage of activity
public class ActivityMap extends HashMap<Activity, Float> {
    private float wagesSum;

    public ActivityMap() {
        wagesSum = 0;
    }

    @Override
    public Float put(Activity key, Float value) {
        wagesSum += value;
        return super.put(key, value);
    }

    //returns values in <0, 1> range;
    public float getRelativeShare(Activity key) {
        float wage = get(key);
        return wage/wagesSum;
    }
}
