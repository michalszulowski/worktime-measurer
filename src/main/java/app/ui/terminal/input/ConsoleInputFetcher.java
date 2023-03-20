package app.ui.terminal.input;

import concurrency.process.SimpleConcurrentProcess;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConsoleInputFetcher extends SimpleConcurrentProcess implements InputFetcher {
    private List<InputObserver> observers;
    private InputReader bufferedInputReader;

    public ConsoleInputFetcher() {
        this(System.in);
    }

    public ConsoleInputFetcher(InputStream inputStream) {
        super("INPUT_FETCHER");
        observers = new ArrayList<>();
        bufferedInputReader = new FromStreamBufferedReader(inputStream);
    }

    @Override
    protected void performMainLoopBody() {
        String line = bufferedInputReader.waitForAndRead();
        notifyObservers(line);
    }

    @Override
    public void addObserver(InputObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String line) {
        observers.forEach(
                observer -> observer.typed(line));
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        testSimpleObserver();
    }

    private static void testInterrupting() throws InterruptedException, IOException {
        ConsoleInputFetcher fetcher = new ConsoleInputFetcher();
        fetcher.addObserver(new CountingObserver());
        fetcher.start();
        Thread.sleep(500);
        fetcher.getExecutionController().kill();
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
