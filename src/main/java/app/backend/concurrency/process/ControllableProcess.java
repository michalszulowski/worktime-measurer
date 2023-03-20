package app.backend.concurrency.process;

public interface ControllableProcess {
    void start(); //TODO think about removing
    ExecutionController getExecutionController();
    String getName();
}
