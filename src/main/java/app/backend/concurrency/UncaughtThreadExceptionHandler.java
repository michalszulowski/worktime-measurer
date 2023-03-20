package app.backend.concurrency;

import app.backend.concurrency.process.ControllableProcess;
import app.ui.terminal.output.OutStream;

import java.util.Random;

public class UncaughtThreadExceptionHandler implements ExceptionHandler<Throwable> {
    private final ControllableProcess process;
    private final OutStream outStream;

    public UncaughtThreadExceptionHandler(ControllableProcess process, OutStream outStream) {
        this.process = process;
        this.outStream = outStream;
    }

    @Override
    public void handle(Throwable exception) {
        String exceptionDescription = exception.getClass().getSimpleName();
        String message = String
                .format("Process %s killed by an uncaught exception.\n" +
                "Exception: %s", process.getName(), exceptionDescription);
        outStream.println(message);
    }

    /*
    public static Thread.UncaughtExceptionHandler asUncaughtExceptionHandler() {


        return new Thread.UncaughtExceptionHandler() {
            private

            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {}
        };


    }
     */

    private static class InnerUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {


        @Override
        public void uncaughtException(Thread thread, Throwable throwable) {
            //new UncaughtThreadExceptionHandler(thread).
        }
    }
}
