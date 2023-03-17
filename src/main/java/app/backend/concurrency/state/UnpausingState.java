package app.backend.concurrency.state;

import app.backend.concurrency.process.ConcurrentProcess;

public class UnpausingState extends StateWithOwner {

    public UnpausingState(ConcurrentProcess owner) {
        super(owner, "UNPAUSING");
    }

    @Override
    public void executeOnActivation() {
        owner.getExecutionController().run();
    }
}
