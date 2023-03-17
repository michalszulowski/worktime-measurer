package app.backend.concurrency.state;

import app.backend.concurrency.AbstractControllableProcess;
import app.backend.concurrency.ConcurrentProcess;

public class UnpausingState extends StateWithOwner {

    public UnpausingState(ConcurrentProcess owner) {
        super(owner, "UNPAUSING");
    }

    @Override
    public void executeOnActivation() {
        owner.getExecutionController().run();
    }
}
