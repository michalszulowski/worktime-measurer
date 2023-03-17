package app.backend.concurrency.state;

import app.backend.concurrency.process.ConcurrentProcess;

public class RunningState extends StateWithOwner {

    public RunningState(ConcurrentProcess owner) {
        super(owner, "RUNNING");
    }

    @Override
    public void executeOnActivation() {
        if (!owner.isRunning()) {
            owner.start();
        }
    }
}
