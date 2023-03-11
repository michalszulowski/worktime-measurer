package app.backend.concurrency.state;

import app.backend.concurrency.AbstractConcurrentProcess;

public class RunningState extends StateWithOwner {

    public RunningState(AbstractConcurrentProcess owner) {
        super(owner, "RUNNING");
    }

    @Override
    public void executeOnActivation() {
        if (!owner.isRunning()) {
            owner.run();
        }
    }
}
