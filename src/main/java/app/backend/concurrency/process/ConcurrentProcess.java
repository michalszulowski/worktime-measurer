package app.backend.concurrency.process;

import app.backend.concurrency.ExceptionHandler;
import app.backend.concurrency.state.ProcessState;

/**
 * Represents runnable thread.
 */
public interface ConcurrentProcess extends ControllableProcess {
    ProcessState getState();
    void setNewState(ProcessState state);
    void notifyAboutChangedState();
    void checkForPendingControlActions();
    boolean isRunning();
    ExceptionHandler<InterruptedException> getInterruptedExceptionHandler();
}
