package app.backend.concurrency;

import app.backend.concurrency.process.ControllableProcess;
import app.ui.terminal.output.OutStream;

import java.util.Random;

public class UncaughtThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final ControllableProcess process;

    public UncaughtThreadExceptionHandler(ControllableProcess process) {
        this.process = process;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        String exceptionDescription = throwable.getClass().getSimpleName();
        String message = String
                .format("Thread %s in process %s killed by an uncaught exception.\n" +
                        "Exception: %s", thread, process.getName(), exceptionDescription);
        System.out.println(message);
    }
}
