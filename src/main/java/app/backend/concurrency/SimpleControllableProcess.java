package app.backend.concurrency;

public abstract class SimpleControllableProcess extends ConcurrentControllableProcess {
    private final ExceptionHandler<InterruptedException> interruptedExceptionExceptionHandler;

    public SimpleControllableProcess(String name) {
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
