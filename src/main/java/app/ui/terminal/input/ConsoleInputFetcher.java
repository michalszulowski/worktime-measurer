package app.ui.terminal.input;

import app.backend.concurrency.process.SimpleConcurrentProcess;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConsoleInputFetcher extends SimpleConcurrentProcess implements InputFetcher {
    private boolean running;
    private List<InputObserver> observers;
    private BufferedReader lineReader;

    public ConsoleInputFetcher() {
        this(System.in);
    }

    public ConsoleInputFetcher(InputStream inputStream) {
        super("INPUT_FETCHER");
        running = true;
        observers = new ArrayList<>();
        lineReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    protected void performMainLoopBody() {
        String line = waitForLineAndRead();
        notifyObservers(line);
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

    public static void main(String[] args) {
        ConsoleInputFetcher fetcher = new ConsoleInputFetcher();
        fetcher.addObserver(new CountingObserver());
        fetcher.start();
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
