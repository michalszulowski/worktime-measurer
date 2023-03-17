package app.backend.concurrency.process;

public interface ControllableProcess {
    void start();
    ExecutionController getExecutionController();
}
