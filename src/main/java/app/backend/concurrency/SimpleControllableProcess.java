package app.backend.concurrency;

public abstract class SimpleControllableProcess extends AbstractControllableProcess {
    @Override
    public synchronized void notifyAboutChangedState() {
        // No notification supported, process simply changes if new state has been set.
    }
}
