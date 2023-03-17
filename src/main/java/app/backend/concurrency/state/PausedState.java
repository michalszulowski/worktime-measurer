package app.backend.concurrency.state;

import app.backend.concurrency.AbstractControllableProcess;
import app.backend.concurrency.ConcurrentProcess;

public class PausedState extends StateWithOwner {
    private final int checkTimeInMs;

    public PausedState(ConcurrentProcess owner) {
        super(owner, "PAUSED");
        checkTimeInMs = 50;
    }

    @Override
    public void executeOnActivation() {
        while (isOwnerPaused()) {
            sleepFor(checkTimeInMs);
            owner.checkForPendingControlActions();
        }
    }

    private boolean isOwnerPaused() {
        return owner.getState() instanceof PausedState;
    }

    private void sleepFor(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            owner.getInterruptedExceptionHandler().handle(e);
        }
    }
}
