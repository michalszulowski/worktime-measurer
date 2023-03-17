package app.backend.concurrency.process;

import app.backend.concurrency.ExceptionHandler;
import app.backend.concurrency.KillOnInterruptHandler;

public abstract class SimpleConcurrentProcess extends AbstractConcurrentProcess {
    private final ExceptionHandler<InterruptedException> interruptedExceptionExceptionHandler;

    public SimpleConcurrentProcess(String name) {
        super(name);
        interruptedExceptionExceptionHandler = new KillOnInterruptHandler(this);
    }

    @Override
    public synchronized void notifyAboutChangedState() {
        // No notification supported, process simply changes if new state has been set.
    }

    @Override
    public ExceptionHandler<InterruptedException> getInterruptedExceptionHandler() {
        return interruptedExceptionExceptionHandler;
    }
}
