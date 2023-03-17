package app.backend.concurrency.state;

import app.backend.concurrency.process.ConcurrentProcess;

public class IdleState extends StateWithOwner {
    public IdleState(ConcurrentProcess owner) {
        super(owner, "IDLE");
    }

    @Override
    public void executeOnActivation() {}
}
