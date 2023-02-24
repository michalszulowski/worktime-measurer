package app.util;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {
    private final static double EPSILON = 0.01;
    private static Duration durationParam;
    private static double result;

    @Test
    public void testOneHour() {
        givenDuration(Duration.ofHours(1));
        whenConvertingToHours();
        thenResultShouldBe(1.0);
    }

    @Test
    public void testHalfAnHour() {
        givenDuration(Duration.ofMinutes(30));
        whenConvertingToHours();;
        thenResultShouldBe(0.5);
    }

    private void givenDuration(Duration duration) {
        durationParam = duration;
    }

    private void whenConvertingToHours() {
        result = Time.getHours(durationParam);
    }

    private void thenResultShouldBe(double expected) {
        assertTrue(isAlmostEqual(expected, result, EPSILON));
    }

    private boolean isAlmostEqual(double number, double to, double epsilon) {
        double difference = Math.abs(number - to);
        return difference < epsilon;
    }
}