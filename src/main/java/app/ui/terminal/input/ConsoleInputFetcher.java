package app.ui.terminal.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

public class ConsoleInputFetcher extends ContinuousFetcher {
    private boolean running;
    private List<InputObserver> observers;
    private BufferedReader lineReader;

    public ConsoleInputFetcher() {
        running = true;
        observers = new ArrayList<>();
        lineReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        while (running) {
            String line = waitForLineAndRead();
            notifyObservers(line);
        }
    }

    @Override
    public void addObserver(InputObserver observer) {
        observers.add(observer);
    }

    private String waitForLineAndRead() {
        try {
            return lineReader.readLine();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void notifyObservers(String line) {
        observers.forEach(
                observer -> observer.typed(line));
    }

    public static void main(String[] args) throws IOException {
        ConsoleInputFetcher fetcher = new ConsoleInputFetcher();
        fetcher.addObserver(new CountingObserver());
        fetcher.run();
    }

    private static class CountingObserver implements InputObserver {
        private int count = 0;

        @Override
        public void typed(String text) {
            System.out.println(count +  ".: Typed: " + text);
            count++;
        }
    }
}
