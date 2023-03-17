package app.backend.concurrency;

public interface ControllableProcess {
    void start();
    ExecutionController getExecutionController();
}
