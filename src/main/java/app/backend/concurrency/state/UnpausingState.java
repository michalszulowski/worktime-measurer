package app.backend.concurrency.state;

import app.backend.concurrency.AbstractConcurrentProcess;

public class UnpausingState extends StateWithOwner {

    public UnpausingState(AbstractConcurrentProcess owner) {
        super(owner, "UNPAUSING");
    }

    @Override
    public void executeOnActivation() {
        owner.getExecutionController().run();
    }
}
