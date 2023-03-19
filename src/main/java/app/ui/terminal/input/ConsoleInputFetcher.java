package app.ui.terminal.input;

import app.backend.concurrency.process.SimpleConcurrentProcess;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConsoleInputFetcher extends SimpleConcurrentProcess implements InputFetcher {
    private boolean running;
    private List<InputObserver> observers;
    private BufferedReader lineReader;
    private InputStream inputStream;

    public ConsoleInputFetcher() {
        this(System.in);
    }

    public ConsoleInputFetcher(InputStream inputStream) {
        super("INPUT_FETCHER");
        this.inputStream = inputStream;
        running = true;
        observers = new ArrayList<>();
        lineReader = new BufferedReader(new InputStreamReader(this.inputStream));
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

    public static void main(String[] args) throws InterruptedException, IOException {
        testInterrupting();
    }

    private static void testInterrupting() throws InterruptedException, IOException {
        ConsoleInputFetcher fetcher = new ConsoleInputFetcher();
        fetcher.addObserver(new CountingObserver());
        fetcher.start();
        Thread.sleep(500);
        fetcher.getExecutionController().kill();
        fetcher.inputStream.read();
        System.out.println("Closed stream");
    }

    private static void testSimpleObserver() {
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
