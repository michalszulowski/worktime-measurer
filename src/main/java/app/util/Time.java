package app.util;

import java.time.Duration;

public class Time {
    public static double getHours(Duration duration) {
        final double SECONDS_IN_HOUR = 60 * 60;
        return duration.toHours() / SECONDS_IN_HOUR;
    }
}
