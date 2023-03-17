package app.backend.concurrency;

import app.backend.concurrency.process.ConcurrentProcess;

public class KillOnInterruptHandler implements ExceptionHandler<InterruptedException> {
    private final ConcurrentProcess owner;

    public KillOnInterruptHandler(ConcurrentProcess owner) {
        this.owner = owner;
    }

    @Override
    public void handle(InterruptedException exception) {
        owner.getExecutionController().kill();
    }
}
