package app.ui.terminal;

import app.ui.terminal.output.SimpleProgressBar;
import app.ui.terminal.output.StringBuilderOutStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleProgressBarTest {
    private static StringBuilderOutStream outStream; //TODO think about some buffered class
    private static SimpleProgressBar progressBar;
    private static String output;

    @BeforeAll
    public static void setupVariables() {
        outStream = new StringBuilderOutStream();
    }

    @Test
    public void testBasicCaseBar() {
        givenBar(2.f, 3.f, 12);
        whenBarIsDisplayed();
        thenOutputShouldBe("[██████    ]\n");
    }

    @Test
    public void testEmptyBar() {
        givenBar(0.f, 3.f, 12);
        whenBarIsDisplayed();
        thenOutputShouldBe("[          ]\n");
    }

    @Test
    public void testFullBar() {
        givenBar(3.f, 3.f, 12);
        whenBarIsDisplayed();
        thenOutputShouldBe("[██████████]\n");
    }

    @Test
    public void testExceedingBar() {
        givenBar(5.f, 4.f, 12);
        whenBarIsDisplayed();
        thenOutputShouldBe("[████████]██\n");
    }

    private void givenBar(float numerator, float denominator, int consoleWidth) {
        progressBar = new SimpleProgressBar(outStream, consoleWidth, numerator, denominator);
    }

    private void whenBarIsDisplayed() {
        progressBar.print();
        output = outStream.getContent();
        outStream.clear();
    }

    private void thenOutputShouldBe(String expected) {
        assertEquals(expected, output);
    }
}