package app.backend.concurrency.state;

import app.backend.concurrency.AbstractControllableProcess;

public class PausedState extends StateWithOwner {
    public PausedState(AbstractControllableProcess owner) {
        super(owner, "PAUSED");
    }

    @Override
    public void executeOnActivation() {
        while (isOwnerPaused()) {
            //wait
            Thread.currentThread();
            owner.checkForPendingControlActions();
        }
    }

    private boolean isOwnerPaused() {
        return owner.getState() instanceof PausedState;
    }
}
