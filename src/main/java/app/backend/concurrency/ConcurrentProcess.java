package app.backend.concurrency;

public interface ConcurrentProcess {
    void start();
    ExecutionController getExecutionController();
}
