package app.ui.terminal.input;

import org.junit.jupiter.api.BeforeAll;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//TODO finish or maybe totally remove?
class ConsoleInputFetcherTest {
    private static ConsoleInputFetcher inputFetcher;
    private static SavingObserver inputObserver;



    private void givenInput(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        inputFetcher = new ConsoleInputFetcher(inputStream);
        inputObserver = new SavingObserver();
        inputFetcher.addObserver(inputObserver);
    }

    private void whenFetching() {
        //inputFetcher.
    }

    private void thenReadInputShouldBe(List<String> expectedReadInput) {

    }

    private static class SavingObserver implements InputObserver {
        private final List<String> receivedInput = new ArrayList<>();

        @Override
        public void typed(String text) {
            receivedInput.add(text);
        }

        public List<String> getInput() {
            return receivedInput;
        }
    }

    public static void main(String[] args) {
        String input = "aa\n bbb\bram";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        InputReader inputReader = new FromStreamBufferedReader(inputStream);
        String result = inputReader.waitForAndRead();
        System.out.println(result);
    }

}