package app.backend.concurrency.process;

import app.ui.terminal.input.ConsoleInputFetcher;
import app.ui.terminal.input.ContinuousFetcher;
import app.ui.terminal.input.InputObserver;
import command.Command;
import command.factory.CommandFactory;
import command.factory.DictionaryCommandFactory;

import java.io.*;
import java.time.LocalTime;
import java.util.Scanner;

public class TimePrintingProcess extends SimpleConcurrentProcess {
    private final int WAIT_TIME = 1000;
    private int iteration;
    private PrintStream output;
    private String tempFilePath;

    public TimePrintingProcess() {
        super("TIME_PRINTER");
        iteration = 0;
        createTempFileAndInitOutput();
        spawnNewTerminalAndTailToFile(tempFilePath);
    }

    @Override
    protected void performMainLoopBody() {
        printCurrentTime();
        iteration++;
        waitBeforeNextIteration();
    }

    private void createTempFileAndInitOutput() {
        try {
            output = createTempFileAndGetOutStream();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private PrintStream createTempFileAndGetOutStream() throws IOException {
        tempFilePath = createTempFile();
        PrintStream filePrintStream = new PrintStream(new FileOutputStream(tempFilePath, true));
        filePrintStream.println("FILE CREATED");
        return filePrintStream;
    }

    private String createTempFile() throws IOException {
        Process process = Runtime.getRuntime().exec("mktemp");
        Scanner scanner = new Scanner(process.getInputStream()).useDelimiter("\\A");
        String tmpFileName = scanner.hasNext() ? scanner.next().strip() : "";
        System.out.println("Created temp file:");
        System.out.println(tmpFileName);
        return tmpFileName;
    }

    private void spawnNewTerminalAndTailToFile(String tempFilePath) {
        try {
            String command = String.format("gnome-terminal -- tail %s -f", tempFilePath);
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void printCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        output.println("It is " + iteration + " iteration. Time: " + currentTime);
    }
    private void waitBeforeNextIteration() {
        try {
            Thread.sleep(WAIT_TIME);
        } catch (InterruptedException ex) {
            getInterruptedExceptionHandler().handle(ex);
        }
    }
}
