package app.backend.concurrency;

import app.backend.concurrency.state.ProcessState;

/**
 * Represents runnable thread.
 */
public interface ConcurrentProcess {
    void run();
    ExecutionController getExecutionController();
    ProcessState getState();
}
