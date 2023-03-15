package app.backend.concurrency.state;

import app.backend.concurrency.AbstractControllableProcess;

public class RunningState extends StateWithOwner {

    public RunningState(AbstractControllableProcess owner) {
        super(owner, "RUNNING");
    }

    @Override
    public void executeOnActivation() {
        if (!owner.isRunning()) {
            owner.start();
        }
    }
}
